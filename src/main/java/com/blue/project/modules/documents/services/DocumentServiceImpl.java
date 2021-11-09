package com.blue.project.modules.documents.services;

import com.blue.project.modules.documents.dao.DocumentRepository;
import com.blue.project.dto.SearchResults;
import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dto.DocumentSearch;
import com.blue.project.modules.documents.models.Document;
import com.blue.project.modules.users.models.SecurityRole;
import com.blue.project.modules.users.models.User;
import com.blue.project.modules.users.services.UserService;
import name.mymiller.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentRepository documentRepository;


    @Override
    public StatusMessage uploadDocument(MultipartFile file) {
        StatusMessage status = new StatusMessage();
        status.setOk(true);

        User user = this.userService.getCurrentUser();
        try {
            Document doc = new Document();
            doc.setUser(user);
            doc.setFileName(file.getOriginalFilename());
            doc.setContentType(file.getContentType());
            doc.setDocument(file.getBytes());
            doc.setSize(Integer.toUnsignedLong(file.getBytes().length));

            this.documentRepository.save(doc);
        } catch (IOException e) {
            status.setOk(false);
            status.setMessage(e.getMessage());
        }
        return status;
    }

    @Override
    public ResponseEntity<byte[]> downloadDocument(Long documentId) {
        User user = this.userService.getCurrentUser();

        Document doc = this.documentRepository.findFirstByIdAndUser(documentId, user);

        return this.downloadResponseEntity(doc.getDocument(), doc.getFileName());
    }

    @Override
    public ResponseEntity<byte[]> downloadDocumentByUuid(String uuid) {
        Document doc = this.documentRepository.findFirstByUuid(uuid);

        return this.downloadResponseEntity(doc.getDocument(), doc.getFileName());
    }

    @Override
    public DocumentSearch search(DocumentSearch docSearch) {
        SearchResults<Document> results;

        results = this.queryDocumentSearch(docSearch);

        docSearch.setResults(results.getResults());
        docSearch.setTotal(results.getTotalCount());
        docSearch.setContentTypes(this.getDocumentContentTypes(docSearch));
        return docSearch;
    }

    private SearchResults<Document> queryDocumentSearch(DocumentSearch docSearch) {
        SearchResults<Document> results = new SearchResults<>();
        String username = docSearch.getUsername();
        User user = this.userService.getCurrentUser();

        if (!this.userService.hasAuthority(user, SecurityRole.ROLE_DOCUMENT_MANAGER)) {
            username = user.getUsername();
        }

        if (StringUtils.isNotNullOrEmpty(username)
                || StringUtils.isNotNullOrEmpty(docSearch.getFileName())
                || StringUtils.isNotNullOrEmpty(docSearch.getContentType())
                || (docSearch.getSizeFilter() != null && docSearch.getSizeFilter() > 0)
                || docSearch.getDate() != null) {

            CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Document> criteriaQuery = criteriaBuilder.createQuery(Document.class);

            Root<Document> documentRoot = criteriaQuery.from(Document.class);
            Join<Document, User> userRoot = documentRoot.join("user", JoinType.INNER);
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotNullOrEmpty(username)) {
                predicates.add(criteriaBuilder.like(documentRoot.get("user").get("username"), "%" + docSearch.getUsername() + "%"));
            }

            if (StringUtils.isNotNullOrEmpty(docSearch.getFileName())) {
                predicates.add(criteriaBuilder.like(documentRoot.get("fileName"), "%" + docSearch.getFileName() + "%"));
            }

            if (StringUtils.isNotNullOrEmpty(docSearch.getContentType())) {
                predicates.add(criteriaBuilder.equal(documentRoot.get("contentType"), docSearch.getContentType()));
            }
            if (docSearch.getSizeFilter() != null && docSearch.getSizeFilter() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(documentRoot.get("size"), docSearch.getSizeFilter()));
            }

            if (docSearch.getDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(documentRoot.get("dateCreated"), docSearch.getDate()));
            }

            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            criteriaQuery.where(finalPredicate);

            if (StringUtils.isNotNullOrEmpty(docSearch.getSort())) {
                Path<Object> objectPath;
                if (docSearch.getSort().equals("user.username")) {
                    objectPath = userRoot.get("username");
                } else {
                    objectPath = documentRoot.get(docSearch.getSort());
                }
                if (docSearch.getAscending()) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(objectPath));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(objectPath));
                }
            }


            TypedQuery<Document> query = entityManager.createQuery(criteriaQuery);
            query.setFirstResult(docSearch.getPage() * docSearch.getSize());
            query.setMaxResults(docSearch.getSize());

            results.setResults(query.getResultList());

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(Document.class)));
            countQuery.where(finalPredicate);

            results.setTotalCount(entityManager.createQuery(countQuery).getSingleResult());

        } else {
            Pageable pageable;

            if (StringUtils.isNotNullOrEmpty(docSearch.getSort())) {
                if (docSearch.getAscending()) {
                    pageable = PageRequest.of(docSearch.getPage(), docSearch.getSize(), Sort.by(docSearch.getSort()).ascending());
                } else {
                    pageable = PageRequest.of(docSearch.getPage(), docSearch.getSize(), Sort.by(docSearch.getSort()).descending());
                }
            } else {
                pageable = PageRequest.of(docSearch.getPage(), docSearch.getSize());
            }
            Page<Document> docs = this.documentRepository.findAll(pageable);
            results.setTotalCount(docs.getTotalElements());
            results.setResults(docs.toList());
        }

        return results;
    }

    private List<String> getDocumentContentTypes(DocumentSearch docSearch) {
        String username = "%";
        String filename = "%";
        String contentType = "%";
        Long size = 0L;
        ZonedDateTime dateCreated = ZonedDateTime.now();

        if (StringUtils.isNotNullOrEmpty(docSearch.getUsername())) {
            username = "%" + docSearch.getUsername() + "%";
        }

        if (StringUtils.isNotNullOrEmpty(docSearch.getFileName())) {
            filename = "%" + docSearch.getFileName() + "%";
        }

        if (StringUtils.isNotNullOrEmpty(docSearch.getContentType())) {
            contentType = "%" + docSearch.getContentType() + "%";
        }

        if (docSearch.getSizeFilter() != null) {
            size = docSearch.getSizeFilter();
        }

        if (docSearch.getDate() != null) {
            dateCreated = docSearch.getDate();
        }

        return this.documentRepository.findAllContentTypesByDocSearch(username, filename, contentType, dateCreated, size);
    }

    private ResponseEntity<byte[]> downloadResponseEntity(byte[] body, String fileName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData(fileName, fileName);

        if (body != null) {
            headers.setContentLength(body.length);
        }

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    private ResponseEntity<byte[]> mediaTypeResponseEntity(byte[] body) {

        HttpHeaders headers = new HttpHeaders();

        if (body != null) {
            headers.setContentLength(body.length);
        }

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    public StatusMessage deleteDocument(Long id) {
        StatusMessage status = new StatusMessage();
        status.setOk(true);

        User user = this.userService.getCurrentUser();
        Optional<Document> optionalDocument = this.documentRepository.findById(id);
        if (optionalDocument.isPresent() && optionalDocument.get().getUser().getId().equals(user.getId())) {
            this.documentRepository.delete(optionalDocument.get());
        } else if (optionalDocument.isPresent() && this.userService.hasAuthority(user, SecurityRole.ROLE_DOCUMENT_MANAGER)) {
            this.documentRepository.delete(optionalDocument.get());
        } else if (optionalDocument.isPresent()) {
            status.setOk(false);
            status.setMessage("error.not_authorized");
        } else {
            status.setOk(false);
            status.setMessage("error.not_found");
        }

        return status;
    }

}
