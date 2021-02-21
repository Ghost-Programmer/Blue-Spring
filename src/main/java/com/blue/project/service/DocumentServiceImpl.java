package com.blue.project.service;

import com.blue.project.dao.user.DocumentRepository;
import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.users.DocumentSearch;
import com.blue.project.models.users.Document;
import com.blue.project.models.users.SecurityRole;
import com.blue.project.models.users.User;
import name.mymiller.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService{

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

            this.documentRepository.save(doc);
        } catch(IOException e) {
            status.setOk(false);
            status.setMessage(e.getMessage());
        }
        return status;
    }

    @Override
    public ResponseEntity<byte[]> downloadDocument(Long documentId) {
        User user = this.userService.getCurrentUser();

        Document doc = this.documentRepository.findFirstByIdAndUser(documentId,user);

        return this.downloadResponseEntity(doc.getDocument(),doc.getFileName());
    }

    @Override
    public DocumentSearch search(DocumentSearch docSearch) {
        Pageable pageable;
        Page<Document> results;
        String username = docSearch.getUsername();
        User user = this.userService.getCurrentUser();

        if(!this.userService.hasAuthority(user,SecurityRole.ROLE_DOCUMENT_MANAGER)) {
            username = user.getUsername();
        }

        if(StringUtils.isNotNullOrEmpty(docSearch.getSort())) {
            if(docSearch.getAscending()) {
                pageable = PageRequest.of(docSearch.getPage(), docSearch.getSize(), Sort.by(docSearch.getSort()).ascending());
            } else {
                pageable = PageRequest.of(docSearch.getPage(), docSearch.getSize(), Sort.by(docSearch.getSort()).descending());
            }
        } else {
            pageable = PageRequest.of(docSearch.getPage(),docSearch.getSize());
        }

        if(StringUtils.isNotNullOrEmpty(docSearch.getUsername())) {
            ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                    .withMatcher("username", match -> match.transform(source -> java.util.Optional.ofNullable(((Document) (source.get())).getUser())).ignoreCase())
                    .withMatcher("filename", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("contentType", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("date", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());


            Example<Document> example = Example.of(Document.from(username,docSearch.getFileName(), docSearch.getContentType(), docSearch.getDate()),customExampleMatcher);

            results = this.documentRepository.findAll(example, pageable);
        } else {
            results = this.documentRepository.findAll(pageable);
        }
        docSearch.setResults(results.toList());
        docSearch.setTotal(results.getTotalElements());

        return docSearch;
    }

    private ResponseEntity<byte[]> downloadResponseEntity(byte[] body, String fileName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData(fileName, fileName);

        if (body != null) {
            headers.setContentLength(body.length);
        }

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity <>(body, headers, HttpStatus.OK);
    }

    private ResponseEntity<byte[]> mediaTypeResponseEntity(byte[] body) {

        HttpHeaders headers = new HttpHeaders();

        if (body != null) {
            headers.setContentLength(body.length);
        }

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity <>(body, headers, HttpStatus.OK);
    }
}
