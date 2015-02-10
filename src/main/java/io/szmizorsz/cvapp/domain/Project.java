package io.szmizorsz.cvapp.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;

import io.szmizorsz.cvapp.domain.util.CustomLocalDateSerializer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Project.
 */
@Entity
@Table(name = "T_PROJECT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Project implements Serializable {

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

    @Column(name = "client_en")
    private String clientEn;

    @Column(name = "client_hu")
    private String clientHu;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "start", nullable = false)
    private LocalDate start;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "end", nullable = false)
    private LocalDate end;

    @ManyToOne
    private Company company;

    @ManyToMany(fetch= FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Technology> technologys = new HashSet<>();

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

	public String getNameHu() {
		return nameHu;
	}

	public void setNameHu(String nameHu) {
		this.nameHu = nameHu;
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

	public String getClientEn() {
		return clientEn;
	}

	public void setClientEn(String clientEn) {
		this.clientEn = clientEn;
	}

	public String getClientHu() {
		return clientHu;
	}

	public void setClientHu(String clientHu) {
		this.clientHu = clientHu;
	}

	public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Technology> getTechnologys() {
        return technologys;
    }

    public void setTechnologys(Set<Technology> technologys) {
        this.technologys = technologys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", nameEn='" + nameEn + "'" +
                ", nameHu='" + nameHu + "'" +
                ", descriptionEn='" + descriptionEn + "'" +
                ", descriptionHu='" + descriptionHu + "'" +
                ", clientEn='" + clientEn + "'" +
                ", clientHu='" + clientHu + "'" +
                ", start='" + start + "'" +
                ", end='" + end + "'" +
                '}';
    }
}
