package com.pw.webtail3.view;


import java.util.UUID;

import com.pw.webtail3.backend.Webtail3Broadcaster;
import com.pw.webtail3.backend.Webtail3Service;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.shared.Registration;


public class Webtail3ContentContainer extends VerticalLayout {
	
	private Registration eventRegistration;
	private final String uuID;
	
	private final String filePath;

	private HorizontalLayout topInfoContainer;
	private Div fileContentContainer;
	
	public Webtail3ContentContainer(String filePath) {
		this.uuID = UUID.randomUUID().toString();
		this.filePath = filePath;
		this.setSizeUndefined();
		this.addClassName("scrollable");

		this.createTopInfoContainer();
		this.createFileContentContainer();
		this.createLoadingIcon();
	}

	private void createLoadingIcon() {
		Image i = new Image("icons/spinner.gif", "hi");
		i.setWidth("30px");
		i.setHeight("30px");
		this.add(i);
	}

	private void createFileContentContainer() {
		this.fileContentContainer = new Div();
		this.fileContentContainer.addClassName("scrollable");
		this.fileContentContainer.setWidthFull();
		
		this.add(this.fileContentContainer);
	}

	private void createTopInfoContainer() {
		this.topInfoContainer = new HorizontalLayout();
		this.topInfoContainer.setWidthFull();

		TextField textField = new TextField();
		textField.setValue(filePath);
		textField.setReadOnly(true);
		textField.setWidthFull();
		
		FormLayout fl = new FormLayout();
		fl.setWidthFull();
		
		fl.addFormItem(new Label(filePath), new Label("File Path:"));
		fl.setResponsiveSteps(new ResponsiveStep("0", 1));
		
		this.topInfoContainer.add(fl);
		//this.topInfoContainer.add(new TextField());
		
		this.add(topInfoContainer);
	}

	public String getUuID() {
		return uuID;
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
			ui.access(() -> {
				this.fileContentContainer.getElement().appendChild(pre);	
			});
			System.out.println(newMessage);
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
