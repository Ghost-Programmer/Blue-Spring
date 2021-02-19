package com.blue.project.models.users;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "documents", schema = "user", catalog = "user")
@Entity
public class Document extends AbstractTimestampEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "document")
    byte[] document;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document1 = (Document) o;
        return Objects.equals(id, document1.id) && Objects.equals(user, document1.user) && Objects.equals(fileName, document1.fileName) && Objects.equals(contentType, document1.contentType) && Arrays.equals(document, document1.document);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, user, fileName, contentType);
        result = 31 * result + Arrays.hashCode(document);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Document{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", contentType='").append(contentType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
