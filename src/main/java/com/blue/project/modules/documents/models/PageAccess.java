package com.blue.project.modules.documents.models;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "page_access", schema = "documents", catalog = "documents")
@Entity
public class PageAccess extends AbstractTimestampEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "role_id")
    private Long roleId;

    public PageAccess(Long pageId, Long roleId) {
        this.pageId = pageId;
        this.roleId = roleId;
    }

    public PageAccess() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
