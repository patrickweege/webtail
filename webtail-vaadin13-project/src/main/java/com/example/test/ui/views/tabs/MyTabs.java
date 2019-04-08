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
package com.example.test.ui.views.tabs;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Displays the list of available categories, with a search filter as well as
 * buttons to add a new category or edit existing ones.
 */
@Route(value = "tabs")
@PageTitle("My Tabs Test")
public class MyTabs extends VerticalLayout {

    public MyTabs() {
        initView();

        this.addTabs();
    }

    private void initView() {
        addClassName("categories-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addTabs() {
    	Tab tab1 = new Tab("Tab one");
    	Tab tab2 = new Tab("Tab two");
    	Tabs tabs = new Tabs(tab1, tab2);
    	
    	this.add(tabs);

    	Div page1 = new Div();
    	page1.add("<pre>teste page1</>");

    	Div page2 = new Div();
    	page2.add("<pre>teste page2</>");
    	page2.setVisible(false);
    	
    	Div pages = new Div(page1, page2);
    	this.add(pages);
    	
    	Map<Tab, Div> tabsToPages = new HashMap<>();
    	tabsToPages.put(tab1, page1);
    	tabsToPages.put(tab2, page2);
    	
    	tabs.addSelectedChangeListener(event -> {
    	    Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
    	    
    	    tabsToPages.forEach((k, v) -> {
    	    	v.setVisible(v == selectedPage);
    	    });
    	});
    	
    	
    }



}
