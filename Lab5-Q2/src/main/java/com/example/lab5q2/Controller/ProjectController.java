package com.example.lab5q2.Controller;

import com.example.lab5q2.Model.Project;
import com.example.lab5q2.ResponseAPI.ResponseAPI;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/projectTrackerSystem")
public class ProjectController {

    // 1. Create list of projects
    ArrayList<Project> projects = new ArrayList<>();

    // 2. Create a new project
    @PostMapping("/createProject")
    public ResponseAPI createProject(@RequestBody Project project) {
        projects.add(project);
        return new ResponseAPI("New Project Created.");
    }

    // 3. Display all project
    @GetMapping("/getProjects")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    // 4. Update a project
    @PutMapping("/updateProject/{index}")
    public ResponseAPI updateProject(@PathVariable int index, @RequestBody Project project) {
        projects.set(index, project);
        return new ResponseAPI("Project Updated.");
    }

    // 5. Delete a project
    @DeleteMapping("/deleteProject/{index}")
    public ResponseAPI deleteProject(@PathVariable int index) {
        projects.remove(index);
        return new ResponseAPI("Project Deleted.");
    }

    // 6. Change the project status as done or not done
    @PutMapping("/changeStatus/{status}")
    public ResponseAPI changeStatus(@PathVariable String status) {
        boolean allSet = true;
        if (projects.isEmpty()) {
           return new ResponseAPI("There Are No Projects in the System.");
        }
        for (Project project : projects) {
            if (!project.getStatus().equalsIgnoreCase(status)) {
                project.setStatus(status);
                allSet = false;
            }
        }
        if(allSet){
            return new ResponseAPI("All Projects Status Already " + status + ".");
        }else {
            return new ResponseAPI("All Projects Status Changed to " + status + ".");
        }
    }

    // 7. Search for a project by given title
    @GetMapping("/searchProject")
    public ResponseAPI searchProject(@RequestParam String title) {
        for (Project project : projects) {
            if (title.equalsIgnoreCase(project.getTitle())) {
                return new ResponseAPI("Project Found.");
            }
        }
        return new ResponseAPI("No Project Found.");
    }

    // 8. Display All project for one company by companyName
    @GetMapping("/getCompanyProjects")
    public ArrayList<Project> getCompanyProjects(@RequestParam String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (companyName.equalsIgnoreCase(project.getCompanyName())) {
                companyProjects.add(project);
            }
        }
        return companyProjects;
    }
}