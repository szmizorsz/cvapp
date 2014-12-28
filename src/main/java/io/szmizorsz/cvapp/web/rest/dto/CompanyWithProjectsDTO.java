package io.szmizorsz.cvapp.web.rest.dto;

import io.szmizorsz.cvapp.domain.Project;

import java.util.List;

public class CompanyWithProjectsDTO {

	private String name;

    private String description;

    private List<Project> projects;
    
    public CompanyWithProjectsDTO() {
    }

    public CompanyWithProjectsDTO(String name, String description, List<Project> projects) {
        this.name = name;
        this.description = description;
        this.projects = projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                '}';
    }

}
