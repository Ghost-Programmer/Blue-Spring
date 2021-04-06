package com.blue.project.modules.meta.models;

import com.blue.project.models.AbstractEntity;
import com.blue.project.models.AbstractTimestampEntity;
import com.blue.project.modules.maintenance.models.AuditTable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "country", schema = "meta", catalog = "meta")
@Entity
public class Country extends AbstractTimestampEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "code_short")
    private String codeShort;

    @Column(name = "name")
    private String name;

    @Column(name = "hemisphere")
    private String hemisphere;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "state_required")
    private Boolean requireState;

    @OneToMany
    @JoinColumn(name = "country_id", nullable = false)
    private List<State> states;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeShort() {
        return codeShort;
    }

    public void setCodeShort(String codeShort) {
        this.codeShort = codeShort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getRequireState() {
        return requireState;
    }

    public void setRequireState(Boolean requireState) {
        this.requireState = requireState;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(code, country.code) && Objects.equals(codeShort, country.codeShort) && Objects.equals(name, country.name) && Objects.equals(hemisphere, country.hemisphere) && Objects.equals(sort, country.sort) && Objects.equals(requireState, country.requireState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, codeShort, name, hemisphere, sort, requireState);
    }
}
