package com.pw.fsexplorer.view;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import com.pw.fsexplorer.bean.FileSystemItemBean;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.AbstractBackEndHierarchicalDataProvider;
import com.vaadin.flow.data.provider.hierarchy.HierarchicalQuery;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;

public class FileSystemExplorerContainer extends Div {

	public FileSystemExplorerContainer() {

	}

	private void createTreeGrid() {
		
		TreeGrid<File> treeGrid = new TreeGrid<>();
        treeGrid.setDataProvider(new FileSystemExplorerDataProvider(null));
 
        
        TemplateRenderer<File>.of(template)
        
        treeGrid.addColumn(file -> {
            String iconHtml;
            if (file.isDirectory()) {
                iconHtml = VaadinIcons.FOLDER_O.getHtml();
            } else {
                iconHtml = VaadinIcons.FILE_O.getHtml();
            }
            return iconHtml + " "
                    + Jsoup.clean(file.getName(), Whitelist.simpleText());
        }, new HtmlRenderer()).setHeader("Name").setId("file-name");
 
        
        new Teplat
        
        treeGrid.addColumn(file -> file.isDirectory() ? "--" : file.length() + " bytes").setHeader("Size").setId("file-size");

        LocalDateTimeRenderer<File> dateTimeRender = new LocalDateTimeRenderer<>(file -> LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()));
        treeGrid.addColumn(dateTimeRender).setHeader("Last Modified").setId("file-last-modified");
 
        treeGrid.setHierarchyColumn("file-name");		
		
		
//		
//		
//		TreeGrid<FileSystemItemBean> grid = new TreeGrid<>();
//		grid.addHierarchyColumn(FileSystemItemBean::getName).setHeader("Name");
//		grid.addColumn(FileSystemItemBean::getSize).setHeader("Size");
//		grid.addColumn(FileSystemItemBean::getModified).setHeader("Modified");
//
//		grid.setDataProvider(new AbstractBackEndHierarchicalDataProvider<FileSystemItemBean, Void>() {
//
//			private final int nodesPerLevel = 3;
//			private final int depth = 2;
//
//			@Override
//			public int getChildCount(HierarchicalQuery<FileSystemItemBean, Void> query) {
//				Optional<Integer> count = query.getParentOptional().flatMap(
//						parent -> Optional.of(Integer.valueOf((internalHasChildren(parent) ? nodesPerLevel : 0))));
//
//				return count.orElse(nodesPerLevel);
//			}
//
//			@Override
//			public boolean hasChildren(FileSystemItemBean item) {
//				return internalHasChildren(item);
//			}
//
//			private boolean internalHasChildren(FileSystemItemBean node) {
//				return node.getDepth() < depth;
//			}
//
//			@Override
//			protected Stream<FileSystemItemBean> fetchChildrenFromBackEnd(
//					HierarchicalQuery<FileSystemItemBean, Void> query) {
//				final int depth = query.getParentOptional().isPresent() ? query.getParent().getDepth() + 1 : 0;
//				final Optional<String> parentKey = query.getParentOptional()
//						.flatMap(parent -> Optional.of(parent.getId()));
//
//				List<FileSystemItemBean> list = new ArrayList<>();
//				int limit = Math.min(query.getLimit(), nodesPerLevel);
//				for (int i = 0; i < limit; i++) {
//					list.add(new FileSystemItemBean(parentKey.orElse(null), depth, i + query.getOffset()));
//				}
//				return list.stream();
//			}
//		});

	}

}
