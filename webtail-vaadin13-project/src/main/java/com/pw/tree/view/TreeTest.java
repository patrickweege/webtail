package com.pw.tree.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "treetest")
@PageTitle("FS Explorer Main")
public class TreeTest extends VerticalLayout {
	
	Random random = new Random();
	
	
	public TreeTest() {
		TreeGrid<Project> treeGrid = new TreeGrid<>();
        treeGrid.setItems(generateProjectsForYears(2010, 2016), Project::getSubProjects);
 
        treeGrid.addColumn(Project::getName).setHeader("Project Name").setId("name-column");
        treeGrid.addColumn(Project::getHoursDone).setHeader("Hours Done");
        treeGrid.addColumn(Project::getLastModified).setHeader("Last Modified");
 
        this.add(treeGrid);
        
//        treeGrid.addCollapseListener(event -> {
//            Notification.show(
//                    "Project '" + event.getCollapsedItem().getName() + "' collapsed.",
//                    Type.TRAY_NOTIFICATION);
//        });
//        treeGrid.addExpandListener(event -> {
//            Notification.show(
//                    "Project '" + event.getExpandedItem().getName()+ "' expanded.",
//                    Type.TRAY_NOTIFICATION);
//        });
	}
	
	
	
	private List<Project> generateProjectsForYears(int startYear, int endYear) {
        List<Project> projects = new ArrayList<>();
 
        for (int year = startYear; year <= endYear; year++) {
            Project yearProject = new Project("Year " + year);
 
            for (int i = 1; i < 2 + random.nextInt(5); i++) {
                Project customerProject = new Project("Customer Project " + i);
                customerProject.setSubProjects(Arrays.asList(
                        new LeafProject("Implementation", random.nextInt(100), year),
                        new LeafProject("Planning", random.nextInt(10), year),
                        new LeafProject("Prototyping", random.nextInt(20), year)));
                yearProject.addSubProject(customerProject);
            }
            projects.add(yearProject);
        }
        return projects;
    }	
	
	public class Project {
		 
        private List<Project> subProjects = new ArrayList<>();
        private String name;
 
        public Project(String name) {
            this.name = name;
        }
 
        public String getName() {
            return name;
        }
 
        public List<Project> getSubProjects() {
            return subProjects;
        }
 
        public void setSubProjects(List<Project> subProjects) {
            this.subProjects = subProjects;
        }
 
        public void addSubProject(Project subProject) {
            subProjects.add(subProject);
        }
 
        public int getHoursDone() {
            return getSubProjects().stream()
                    .map(project -> project.getHoursDone())
                    .reduce(0, Integer::sum);
        }
 
        public Date getLastModified() {
            return getSubProjects().stream()
                    .map(project -> project.getLastModified())
                    .max(Date::compareTo).orElse(null);
        }
    }
	
	class LeafProject extends Project {
		 
        private int hoursDone;
        private Date lastModified;
 
        public LeafProject(String name, int hoursDone, int year) {
            super(name);
            this.hoursDone = hoursDone;
            lastModified = new Date(year - 1900, random.nextInt(12), random.nextInt(10));
        }
 
        @Override
        public int getHoursDone() {
            return hoursDone;
        }
 
        @Override
        public Date getLastModified() {
            return lastModified;
        }
    }
	
}
