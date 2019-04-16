package com.pw.fsexplorer.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Push
@Route(value = "fsexplorer")
@PageTitle("FS Explorer Main")
public class FileSystemExplorerMain extends VerticalLayout {

	private FileSystemExplorerContainer fsExplorer;
	
	public FileSystemExplorerMain() {
		this.fsExplorer = new FileSystemExplorerContainer();
		this.add(this.fsExplorer);
	}
	
}
