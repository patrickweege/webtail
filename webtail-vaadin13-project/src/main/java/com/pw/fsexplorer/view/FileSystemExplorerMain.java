package com.pw.fsexplorer.view;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route(value = "fsexplorer")
@PageTitle("FS Explorer Main")
@HtmlImport("frontend://bower_components/vaadin-lumo-styles/presets/compact.html")
@Theme(Lumo.class)
public class FileSystemExplorerMain extends VerticalLayout {

	private FileSystemExplorerContainer fsExplorer;
	
	public FileSystemExplorerMain() {
		this.fsExplorer = new FileSystemExplorerContainer();
		
		this.fsExplorer.setWidthFull();
		this.fsExplorer.setHeight("500px");
		
		this.add(this.fsExplorer);
	}
	
}
