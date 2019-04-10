package com.pw.webtail3.view;

import java.util.UUID;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.shared.Registration;


public class Webtail3ContentContainer extends Div {
	
	private Registration eventRegistration;
	private final String uuID;
	
	private final String filePath;

	public Webtail3ContentContainer(String filePath) {
		this.uuID = UUID.randomUUID().toString();
		this.filePath = filePath;
		this.setSizeUndefined();
		this.addClassName("scrollable");
	}

	private void doStartReadFile() {
		Webtail3Service.getInstance().doStartReadFile(this.uuID, this.filePath);

//		lines.forEach(line -> {
//			Element pre = ElementFactory.createPreformatted(line);
//			pre.getStyle().set("margin", "0px 0px 0px 0px");
//			this.getElement().appendChild(pre);
//		});
	}
	
	private void doStopReadFile() {
		Webtail3Service.getInstance().doStopReadFile(this.uuID);
	}
	
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		UI ui = attachEvent.getUI();
		eventRegistration = Webtail3Broadcaster.register(this.uuID, newMessage -> {
			Element pre = ElementFactory.createPreformatted(newMessage);
			pre.getStyle().set("margin", "0px 0px 0px 0px");
			ui.access(() -> this.getElement().appendChild(pre));
		});
		
		this.doStartReadFile();
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		eventRegistration.remove();
		eventRegistration = null;
		this.doStopReadFile();
	}
	

}
