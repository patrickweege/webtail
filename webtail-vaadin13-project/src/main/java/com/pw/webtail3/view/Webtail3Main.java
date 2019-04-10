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

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
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

    private Tabs tabs;
    private TextField filePathTextField;
    private Button openButton;
    private Map<Tab,Div> tabMap;
    private Div pages;

	public Webtail3Main() {
		this.tabMap = new HashMap<Tab, Div>();
        this.initView();
        this.addInputFile();
        this.addTabs();
    }

	private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addInputFile() {
    	this.filePathTextField = new TextField("File Path");
    	this.filePathTextField.setPlaceholder("Enter File-Path");
    	this.filePathTextField.setWidth("400px");
    	
    	this.openButton = new Button("Open File");
    	
    	this.openButton.addClickListener(this::doOpenFile);
    	
    	HorizontalLayout top = new HorizontalLayout();
    	top.add(filePathTextField, openButton);
    	top.setAlignItems(Alignment.BASELINE);
    	//Div div = new Div(filePathTextField, openButton);
    	//div.
    	
    	//this.add(div);
    	this.add(top);
	}

	public void doOpenFile(ClickEvent<Button> event) {
		Tab newTab = new Tab("Tab-" + tabs.getComponentCount());
		this.tabMap.put(newTab, new Webtail3ContentContainer(filePathTextField.getValue()));
		
		this.tabs.add(newTab);
		this.tabs.setSelectedTab(newTab);
		
	}

    private void addTabs() {
    	this.tabs = new Tabs();
    	this.add(tabs);

    	this.tabs.addSelectedChangeListener(event -> {
    	    Tab selectedTab = event.getSource().getSelectedTab();
    	    Div page = this.tabMap.get(selectedTab);
    	    this.pages.removeAll();
    	    this.pages.add(page);
    	});
    	
    	this.pages = new Div();
    	this.add(pages);
    	
    }



}
