package com.blue.project.modules.documents.models;

import com.blue.project.models.AbstractTimestampEntity;
import com.blue.project.modules.documents.dto.MenuDto;
import com.blue.project.modules.users.models.SecurityRole;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "pages", schema = "documents", catalog = "documents")
@Entity
public class Page extends AbstractTimestampEntity implements Serializable  {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "menu_id")
    private Long menuId;

    @Transient
    private List<SecurityRole> roles;

    @Transient
    private MenuDto menu;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "page")
    private String page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecurityRole> roles) {
        this.roles = roles;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public MenuDto getMenu() {
        return menu;
    }

    public void setMenu(MenuDto menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(id, page.id) && Objects.equals(uuid, page.uuid) && Objects.equals(name, page.name) && Objects.equals(roles, page.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("id=").append(id);
        sb.append(", uuid='").append(uuid).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
