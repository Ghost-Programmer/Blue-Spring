package com.blue.project.service;

import com.blue.project.dao.user.DocumentRepository;
import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.documents.DocumentSearch;
import com.blue.project.models.documents.Document;
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

import javax.print.Doc;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

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
            doc.setSize(Integer.toUnsignedLong(file.getBytes().length));

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
    public ResponseEntity<byte[]> downloadDocumentByUuid(String uuid) {
        Document doc = this.documentRepository.findFirstByUuid(uuid);

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

        if(StringUtils.isNotNullOrEmpty(username) || StringUtils.isNotNullOrEmpty(docSearch.getFileName())) {
            ExampleMatcher customExampleMatcher = ExampleMatcher.matching();

            if(StringUtils.isNotNullOrEmpty(username)) {
                customExampleMatcher = customExampleMatcher.withMatcher("user.username", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
            }

            if(StringUtils.isNotNullOrEmpty(docSearch.getFileName())) {
                customExampleMatcher = customExampleMatcher.withMatcher("fileName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
            }


            Example<Document> example = Example.of(Document.from(username,docSearch.getFileName(), null, null),customExampleMatcher);

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

    public StatusMessage deleteDocument(Long id) {
        StatusMessage status = new StatusMessage();
        status.setOk(true);

        User user  = this.userService.getCurrentUser();
        Optional<Document> optionalDocument = this.documentRepository.findById(id);
        if(optionalDocument.isPresent() && optionalDocument.get().getUser().getId().equals(user.getId())) {
            this.documentRepository.delete(optionalDocument.get());
        } else if(optionalDocument.isPresent() && this.userService.hasAuthority(user,SecurityRole.ROLE_DOCUMENT_MANAGER)) {
            this.documentRepository.delete(optionalDocument.get());
        } else if(optionalDocument.isPresent()){
            status.setOk(false);
            status.setMessage("error.not_authorized");
        } else {
            status.setOk(false);
            status.setMessage("error.not_found");
        }

        return status;
    }
}
