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
package com.pw.webtail.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pw.webtail.backend.WebtailService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 */
@Route(value = "webtail")
@PageTitle("Web-Tail View")
public class WebtailMain extends VerticalLayout {

    private Tabs tabs;
    private TextField filePath;
    private Button openButton;
    private Map<Tab,Div> tabMap;
    private Div pages;

	public WebtailMain() {
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
    	this.filePath = new TextField("File Path");
    	this.filePath.setPlaceholder("Enter File-Path");
    	this.openButton = new Button("Open File");
    	
    	this.openButton.addClickListener(this::doOpenFile);
    	
    	Div div = new Div(filePath, openButton);
    	
    	this.add(div);
	}

	public void doOpenFile(ClickEvent<Button> event) {
		List<String> lines = WebtailService.getInstance().getFileContent(filePath.getValue());
		
		Div page = new Div();
		page.setSizeUndefined();
		page.addClassName("scrollable");
		
		//new Pa
		
//		Button btn = new Button("Refresh");
//		btn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
//			
//			@Override
//			public void onComponentEvent(ClickEvent<Button> event) {
//				Element pre = ElementFactory.createPreformatted("Hello World" + page.getChildren().count());
//				pre.getStyle().set("margin", "0px 0px 0px 0px");
//				page.getElement().appendChild(pre);
//				//page.add(pre);
//			}
//		});
//		page.add(btn);
		
		lines.forEach(line -> {
			Element pre = ElementFactory.createPreformatted(line);
			pre.getStyle().set("margin", "0px 0px 0px 0px");
			page.getElement().appendChild(pre);
		});
		
		
		
		
		Tab newTab = new Tab("Tab-" + tabs.getComponentCount());
		this.tabMap.put(newTab, page);
		
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
