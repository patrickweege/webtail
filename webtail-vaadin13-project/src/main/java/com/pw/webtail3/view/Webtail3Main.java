/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.pw.webtail3.view;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 */
@Push
@Route(value = "webtail3")
@PageTitle("Web-Tail View")
public class Webtail3Main extends VerticalLayout {

    private Tabs tabSheet;
    private TextField filePathTextField;
    private Button openButton;
    private Map<Tab, Component> tabMap;
    private Div tabsContent;

	public Webtail3Main() {
		this.tabMap = new HashMap<Tab, Component>();
        this.initView();
        this.createTextField();
        this.createTabSheet();
        
        this.doOpenFile("C:\\work\\eclipse-workspace-vaadin\\git\\webtail\\webtail-vaadin13-project\\99_testfile.txt");
    }

	private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void createTextField() {
    	this.filePathTextField = new TextField();
    	this.filePathTextField.setPlaceholder("Enter File-Path");
    	this.filePathTextField.setWidthFull();
    	
    	this.openButton = new Button("Open File");
    	this.openButton.setWidth("200px");
    	
    	this.openButton.addClickListener(this::openButtonClick);
    	
    	FormLayout top = new FormLayout();
    	top.addFormItem(filePathTextField, "File Path");
    	top.setResponsiveSteps(new ResponsiveStep("0", 1));
    	top.setWidthFull();
    	
    	this.add(new HorizontalLayout(top,openButton));
	}

	public void openButtonClick(ClickEvent<Button> event) {
		this.doOpenFile(filePathTextField.getValue());
	}
	
	public void doOpenFile(String filePath) {
		FilenameUtils.getName(filePath);
		
		final Span label = new Span(FilenameUtils.getName(filePath));
		//final Span label = new Span("Tab-" + tabSheet.getComponentCount());		
		final Icon closeIcon = new Icon(VaadinIcon.CLOSE_SMALL);
		
		Tab newTab = new Tab(label, closeIcon);
		closeIcon.addClickListener(e -> {
			this.tabSheet.remove(newTab);
		});
		
		Webtail3ContentContainer tailContainer = new Webtail3ContentContainer(filePath);
		tailContainer.add(tailContainer.getUuID());
		
		this.addTab(newTab, tailContainer);
		
	}
	

	public void addTab(Tab tab, Component content) {
		content.setVisible(false);
		this.tabMap.put(tab, content);
		this.tabsContent.add(content);
		
		this.tabSheet.add(tab);
		this.tabSheet.setSelectedTab(tab);
	}
	
	
    private void createTabSheet() {
    	this.tabSheet = new Tabs();
    	this.add(tabSheet);

    	this.tabsContent = new Div();
    	this.add(tabsContent);

    	this.tabSheet.addSelectedChangeListener(event -> {
    	    Tab selectedTab = event.getSource().getSelectedTab();
    	    this.setTabContent(selectedTab);
    	});
    	
    	Tab tab = new Tab(new Span("Haloooo"), new Icon(VaadinIcon.CLOSE_SMALL));
    	this.tabSheet.add(tab);
    }

    private void setTabContent(Tab selectedTab) {
	    Component selectedContent = this.tabMap.get(selectedTab);
	    //this.tabsContent.removeAll();
	    this.tabsContent.getChildren().forEach(c -> {
	    	c.setVisible(false);
	    });
	    
	    if(selectedContent != null) {
    	    //this.tabContent.add(tabConent);
	    	selectedContent.setVisible(true);
	    }
    }

}
