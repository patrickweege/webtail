package com.pw.fsexplorer.view;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.SortOrderProvider;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;

public class FileSystemExplorerContainer extends Div {

	private TreeGrid<File> treeGrid;
	private TextField textBaseName;
	private Button btnOpener;
	
	public FileSystemExplorerContainer() {
		this.createOpener();
		this.createTreeGrid();
	}

	
	
	private void createOpener() {
		this.textBaseName = new TextField();
		this.textBaseName.setWidth("300px");
		
		this.btnOpener = new Button("Goto Folder");
		
		this.btnOpener.addClickListener(evt -> {
			treeGrid.setDataProvider(new FileSystemExplorerDataProvider(new File(textBaseName.getValue())));
		});

		this.add(textBaseName, btnOpener);
		
	}


	private void createTreeGrid() {
		
		this.treeGrid = new TreeGrid<>();
		this.treeGrid.setDataProvider(new FileSystemExplorerDataProvider(new File(".")));

        
//        ComponentRenderer<Component, File> nameRenderer =  new ComponentRenderer<Component, File>(file -> {
//            if (file.isFile()) {
//                return new Icon(VaadinIcon.FILE);
//            } else {
//            	Icon i;
//            	if(treeGrid.isExpanded(file)) {
//                    i = new Icon(VaadinIcon.FOLDER_OPEN);
//            	} else {
//            		i = new Icon(VaadinIcon.FOLDER);
//            	}
//                i.addClickListener(evt -> {
//                	if(treeGrid.isExpanded(file)) {
//                    	treeGrid.collapse(file);
//                	} else {
//                    	treeGrid.expand(file);
//                	}
//                });
//                return i;
//            }
//        });
        
//        treeGrid.addColumn(nameRenderer).setHeader("Name");
        Column<File> fNameColumn = treeGrid.addHierarchyColumn(file -> file.getName()).setHeader("Name");
        fNameColumn.setId("file-name");
        fNameColumn.setFlexGrow(4);
        fNameColumn.setResizable(true);
        fNameColumn.setSortable(true);
        
		LocalDateTimeRenderer<File> dateTimeRender = new LocalDateTimeRenderer<>(
				file -> LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()),
				"dd-MM-yyyy HH:mm:ss");
        Column<File> dateColumn = treeGrid.addColumn(dateTimeRender).setHeader("Modified");
        dateColumn.setId("file-date");
        dateColumn.setFlexGrow(1);
        dateColumn.setResizable(true);
        dateColumn.setSortable(true);
        
        Column<File> typeColumn = treeGrid.addColumn(file -> file.isDirectory() ? "Folder" : "File").setHeader("Type");
        typeColumn.setId("file-type");
        typeColumn.setFlexGrow(1);
        typeColumn.setResizable(true);
        typeColumn.setWidth("50px");
        typeColumn.setSortable(true);
        
        Column<File> sizeColumn = treeGrid.addColumn(file -> file.isDirectory() ? "--" : file.length() + " bytes").setHeader("Size");
        sizeColumn.setId("file-size");
        sizeColumn.setFlexGrow(1);
        sizeColumn.setResizable(true);
        sizeColumn.setSortable(true);
        
        this.add(treeGrid);
        
	}

}
