package com.blue.project.modules.documents.models;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Table(name = "menu", schema = "documents", catalog = "documents")
@Entity
public class Menu extends AbstractTimestampEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "delete_lock")
    private Boolean lock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }
}
