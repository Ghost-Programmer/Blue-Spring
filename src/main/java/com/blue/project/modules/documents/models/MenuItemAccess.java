package com.blue.project.modules.documents.models;

import com.blue.project.models.AbstractTimestampEntity;
import com.blue.project.modules.users.models.SecurityRole;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "menu_item_access", schema = "documents", catalog = "documents")
@Entity
public class MenuItemAccess extends AbstractTimestampEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_item_id")
    private Long menuItemId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private SecurityRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public SecurityRole getRole() {
        return role;
    }

    public void setRole(SecurityRole role) {
        this.role = role;
    }
}
