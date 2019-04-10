package com.pw.webtail2.view;

import java.util.List;

import com.pw.webtail.backend.WebtailService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;

public class TailContentContainer extends Div {

	private final String filePath;

	public TailContentContainer(String filePath) {
		this.filePath = filePath;
		this.setSizeUndefined();
		this.addClassName("scrollable");

		this.doReadFile();
	}

	private void doReadFile() {
		List<String> lines = WebtailService.getInstance().getFileContent(this.filePath);

		lines.forEach(line -> {
			Element pre = ElementFactory.createPreformatted(line);
			pre.getStyle().set("margin", "0px 0px 0px 0px");
			this.getElement().appendChild(pre);
		});

	}

}
