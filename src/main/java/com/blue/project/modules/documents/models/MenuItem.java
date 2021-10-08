package com.blue.project.modules.documents.models;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "pages", schema = "documents", catalog = "documents")
@Entity
public class MenuItem extends AbstractTimestampEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "sort")
    private Long sort;

    @Column(name = "route")
    private String route;

    @Column(name = "url")
    private String url;

    @Column(name = "lock")
    private Boolean lock;

    @ManyToOne
    @JoinColumn(name ="menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name="sub_menu_id")
    private Menu subMenu;

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

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public MenuItem() {
    }
}
