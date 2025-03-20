package com.javamarchhex.main.controller;

import java.util.List;

import com.javamarchhex.main.model.Project;
import com.javamarchhex.main.services.ProjectService;

public class ProjectController {

	public void displayProjectRecord() {
		ProjectService projectService = new ProjectService();
		List<Project> list = projectService.fetchAllProjects();
		for(Project p : list)
		{
			System.out.println(p);
		}
	}

}
