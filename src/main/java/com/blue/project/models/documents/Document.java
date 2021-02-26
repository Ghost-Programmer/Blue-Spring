package com.blue.project.models.documents;

import com.blue.project.models.AbstractTimestampEntity;
import com.blue.project.models.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import name.mymiller.lang.UnitOfMemory;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Table(name = "documents", schema = "documents", catalog = "documents")
@Entity
public class Document extends AbstractTimestampEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @ManyToOne
    private User user;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private Long size;

    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "document")
    private byte[] document;

    @Transient
    private Boolean lock;

    @Transient
    private String sizeString;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public String getSizeString() {
        return sizeString;
    }

    public void setSizeString(String sizeString) {
        this.sizeString = sizeString;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @PrePersist
    public void prePersist() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @PostLoad
    public void postLoad() {
        this.lock = false;

        this.sizeString = UnitOfMemory.measurement(this.size);
    }

    public static Document from(String username, String fileName, String contentType, ZonedDateTime date) {
        Document doc = new Document();

        doc.setUser(User.from(username));
        doc.setContentType(contentType);
        doc.setFileName(fileName);

        doc.setUuid(null);
        doc.setDocument(null);
        doc.setId(null);
        doc.setDateCreated(date);
        if(username == null || username.isEmpty()) {
            doc.setUser(null);
        } else {

        }
        return doc;
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
