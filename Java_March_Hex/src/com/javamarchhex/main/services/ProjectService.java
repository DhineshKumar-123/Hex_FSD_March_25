package com.javamarchhex.main.services;

import java.util.List;

import com.javamarchhex.main.model.Project;
import com.javamarchhex.main.repository.ProjectRepository;

public class ProjectService 
{
ProjectRepository projectRepository = new ProjectRepository();
	
	public List<Project> fetchAllProjects() {
		return projectRepository.fetchAllProjects();
	}

}
