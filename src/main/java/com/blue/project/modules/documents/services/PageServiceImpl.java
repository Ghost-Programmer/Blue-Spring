package com.blue.project.modules.documents.services;

import com.blue.project.dto.SearchResults;
import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dao.PageAccessRepository;
import com.blue.project.modules.documents.dao.PagesRepository;
import com.blue.project.modules.documents.dto.PageDto;
import com.blue.project.modules.documents.dto.PageSearch;
import com.blue.project.modules.documents.models.Page;
import com.blue.project.modules.documents.models.PageAccess;
import com.blue.project.modules.users.dao.SecurityRoleRepository;
import com.blue.project.modules.users.models.SecurityRole;
import com.blue.project.modules.users.services.UserService;
import name.mymiller.utils.ListUtils;
import name.mymiller.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PageServiceImpl implements PageService{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private PageAccessRepository pageAccessRepository;

    @Autowired
    private SecurityRoleRepository securityRoleRepository;

    @Autowired
    private UserService userService;

    public Page findByUuid(String uuid) {
        Page page = this.pagesRepository.findPageByUuid(uuid);
        if(page != null) {
            page.setRoles(this.getSecurityRolesForPageAccess(page.getId()));
        }

        if(page != null && ListUtils.isEmpty(page.getRoles())) {
            return page;
        } else if(page != null && this.userService.currentUserHasAuthority(ListUtils.safe(page.getRoles()).stream().map(SecurityRole::getAuthority).collect(Collectors.toList()))) {
            return page;
        } else if(page != null && this.userService.currentUserHasAuthority(List.of(SecurityRole.ROLE_PAGE_EDITOR))) {
            return page;
        }

        return null;
    }

    private List<PageAccess> getPageAccess(Long pageId) {
       return this.pageAccessRepository.findAllByPageId(pageId);
    }

    private List<SecurityRole> getSecurityRoles(List<PageAccess> pageAccesses) {
        List<Long> ids = ListUtils.safe(pageAccesses).stream().map(PageAccess::getRoleId).collect(Collectors.toList());

        if(ListUtils.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }
        return this.securityRoleRepository.findAllById(ids);
    }

    private List<SecurityRole> getSecurityRolesForPageAccess(Long pageId) {
        return this.getSecurityRoles(this.getPageAccess(pageId));
    }

    public Page updatePage(Page page) {

        List<Long> list = ListUtils.safe(page.getRoles()).stream().map(SecurityRole::getId).collect(Collectors.toList());

        List<PageAccess> deleteList = this.pageAccessRepository.findAllByPageId(page.getId());

        List<Long> saveList = ListUtils.safe(deleteList).stream().filter(item -> {
            return list.contains(item.getRoleId());
        }).map(PageAccess::getRoleId).collect(Collectors.toList());

        deleteList = ListUtils.safe(deleteList).stream().filter(item -> {
            return !list.contains(item.getRoleId());
        }).collect(Collectors.toList());

        if(ListUtils.notEmpty(deleteList)) {
            this.pageAccessRepository.deleteAll(deleteList);
        }

        ListUtils.safe(page.getRoles()).stream().filter(item -> {
            return !saveList.contains(item.getId());
        }).forEach( role -> {
            this.pageAccessRepository.save(new PageAccess(page.getId(),role.getId()));
        });

       return this.pagesRepository.save(page);
    }

    public StatusMessage deletePage(Long id) {
        List<PageAccess> allByPageId = this.pageAccessRepository.findAllByPageId(id);
        if(ListUtils.notEmpty(allByPageId)) {
            this.pageAccessRepository.deleteAll(allByPageId);
        }

        this.pagesRepository.deleteById(id);
        return new StatusMessage().setOk(true);
    }

    public Page createPage(Page page) {
        page.setUuid(UUID.randomUUID().toString());
        page.setId(null);

        List<SecurityRole> roles = page.getRoles();
        page.setRoles(null);

        page = this.pagesRepository.save(page);

        final Long pageId = page.getId();

        if(ListUtils.notEmpty(roles)) {
            roles.forEach(role -> {
                this.pageAccessRepository.save(new PageAccess(pageId, role.getId()));
            });
        }

        return this.pagesRepository.findPageByUuid(page.getUuid());
    }

    @Override
    public PageSearch search(PageSearch pageSearch) {
        SearchResults<Page> results;

        results = this.queryPageSearch(pageSearch);

        pageSearch.setResults(results.getResults());
        pageSearch.setTotal(results.getTotalCount());

        return pageSearch;
    }

    public List<PageDto> getAllPages() {
        return ListUtils.safe(this.pagesRepository.findAll()).stream().map(PageDto::new).collect(Collectors.toList());
    }

    private SearchResults<Page> queryPageSearch(PageSearch pageSearch) {
        SearchResults<Page> results = new SearchResults<>();

        if (StringUtils.isNotNullOrEmpty(pageSearch.getName())
                || StringUtils.isNotNullOrEmpty(pageSearch.getUuid())) {

            CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Page> criteriaQuery = criteriaBuilder.createQuery(Page.class);

            Root<Page> documentRoot = criteriaQuery.from(Page.class);
            List<Predicate> predicates = new ArrayList<>();


            if (StringUtils.isNotNullOrEmpty(pageSearch.getName())) {
                predicates.add(criteriaBuilder.like(documentRoot.get("name"), "%" + pageSearch.getName() + "%"));
            }

            if (StringUtils.isNotNullOrEmpty(pageSearch.getUuid())) {
                predicates.add(criteriaBuilder.equal(documentRoot.get("uuid"), pageSearch.getUuid()));
            }

            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            criteriaQuery.where(finalPredicate);

            if (StringUtils.isNotNullOrEmpty(pageSearch.getSort())) {
                Path<Object> objectPath = documentRoot.get(pageSearch.getSort());
                if (pageSearch.getAscending()) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(objectPath));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(objectPath));
                }
            }


            TypedQuery<Page> query = entityManager.createQuery(criteriaQuery);
            query.setFirstResult(pageSearch.getPage() * pageSearch.getSize());
            query.setMaxResults(pageSearch.getSize());

            results.setResults(query.getResultList());

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(Page.class)));
            countQuery.where(finalPredicate);

            results.setTotalCount(entityManager.createQuery(countQuery).getSingleResult());

        } else {
            Pageable pageable;

            if (StringUtils.isNotNullOrEmpty(pageSearch.getSort())) {
                if (pageSearch.getAscending()) {
                    pageable = PageRequest.of(pageSearch.getPage(), pageSearch.getSize(), Sort.by(pageSearch.getSort()).ascending());
                } else {
                    pageable = PageRequest.of(pageSearch.getPage(), pageSearch.getSize(), Sort.by(pageSearch.getSort()).descending());
                }
            } else {
                pageable = PageRequest.of(pageSearch.getPage(), pageSearch.getSize());
            }
            org.springframework.data.domain.Page<Page> docs = this.pagesRepository.findAll(pageable);
            results.setTotalCount(docs.getTotalElements());
            results.setResults(docs.toList());
        }

        return results;
    }
}
