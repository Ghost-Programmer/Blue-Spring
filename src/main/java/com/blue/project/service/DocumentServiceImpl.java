package com.blue.project.service;

import com.blue.project.dao.user.DocumentRepository;
import com.blue.project.dto.StatusMessage;
import com.blue.project.models.users.Document;
import com.blue.project.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

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
}
