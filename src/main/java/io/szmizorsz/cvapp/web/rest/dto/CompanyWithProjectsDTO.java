package io.szmizorsz.cvapp.web.rest.dto;

import io.szmizorsz.cvapp.domain.Project;

import java.util.List;

public class CompanyWithProjectsDTO {

	private String nameEn;

	private String nameHu;

	private String descriptionEn;

	private String descriptionHu;

	private String periodEn;

	private String periodHu;

	private List<Project> projects;
    
    public CompanyWithProjectsDTO() {
    }

    public CompanyWithProjectsDTO(String nameEn, String nameHu, String descriptionEn, String descriptionHu, String periodEn, String periodHu, List<Project> projects) {
        this.nameEn = nameEn;
        this.nameHu = nameHu;
        this.descriptionEn = descriptionEn;
        this.descriptionHu = descriptionHu;
        this.periodEn = periodEn;
        this.periodHu = periodHu;
        this.projects = projects;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Company{" +
                ", nameEn='" + nameEn + "'" +
                ", nameHu='" + nameHu + "'" +
                ", descriptionEn='" + descriptionEn + "'" +
                ", descriptionHu='" + descriptionHu + "'" +
                ", periodEn='" + periodEn + "'" +
                ", periodHu='" + periodHu + "'" +                
                '}';
    }

}
