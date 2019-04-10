package com.example.vaadinserverpush;

import java.time.Instant;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;

@Push
@SuppressWarnings("serial")
@Route(value = "push")
public class VaadinserverpushUI extends UI {

	private QuoteGenerator qg;
	private VerticalLayout layout;
	private Label theTime;
	
	
	protected void init(VaadinRequest request) {
		qg = new QuoteGenerator();
		layout = new VerticalLayout();
		layout.setMargin(true);
		
		this.add(layout);
		
		theTime = new Label();
		theTime.setText("Its now : " + Instant.now());
		layout.add(theTime);
		
		new MyFirsthThread().start();
		new MySecondThread2().start(); 	
	}

	class MyFirsthThread extends Thread {

	    @Override
	    public void run() {
	        try {
	            while (true) {
	        		Thread.sleep(1000);
	        		VaadinserverpushUI.this.access(() -> {
	        			theTime.setText("Its now : " + Instant.now());
	        		});
	            }

	        	} catch (InterruptedException e) {
	        		e.printStackTrace();
	        	}
	    }
	}

	class MySecondThread2 extends Thread {
	    int count = 0;

	    @Override
	    public void run() {
	        try {
	            while (count < 4) {
	                Thread.sleep(10000);

					VaadinserverpushUI.this.access(() -> {
						layout.add(new Label(qg.getQuote()));
						count++;
					});
	            }

				VaadinserverpushUI.this.access(() -> {
					layout.add(new Label("No more messages for you !"));
				});
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}	
	
}

