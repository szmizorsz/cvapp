package io.szmizorsz.cvapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Company.
 */
@Entity
@Table(name = "T_COMPANY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "name_en")
    private String nameEn;

	@Column(name = "name_hu")
    private String nameHu;

    @Column(name = "description_en")
    private String descriptionEn;

	@Column(name = "description_hu")
    private String descriptionHu;

	@Column(name = "period_en")
    private String periodEn;

	@Column(name = "period_hu")
    private String periodHu;

	@OneToMany(mappedBy = "company")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionHu() {
		return descriptionHu;
	}

	public void setDescriptionHu(String descriptionHu) {
		this.descriptionHu = descriptionHu;
	}
    
    public String getNameHu() {
		return nameHu;
	}

	public void setNameHu(String nameHu) {
		this.nameHu = nameHu;
	}
	
	public String getPeriodEn() {
		return periodEn;
	}

	public void setPeriodEn(String periodEn) {
		this.periodEn = periodEn;
	}

	public String getPeriodHu() {
		return periodHu;
	}

	public void setPeriodHu(String periodHu) {
		this.periodHu = periodHu;
	}

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        if (id != null ? !id.equals(company.id) : company.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nameEn='" + nameEn + "'" +
                ", nameHu='" + nameHu + "'" +
                ", descriptionEn='" + descriptionEn + "'" +
                ", descriptionHu='" + descriptionHu + "'" +
                ", periodEn='" + periodEn + "'" +
                ", periodHu='" + periodHu + "'" +
                '}';
    }
}
