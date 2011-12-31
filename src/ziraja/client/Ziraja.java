package ziraja.client;

import ziraja.client.event.CancelledEvent;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Ziraja implements EntryPoint {
	@SuppressWarnings("unused")
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	private final HandlerManager eventBus = new HandlerManager(null);
	private final AppController appViewer = new AppController(eventBus);
	
	public void onModuleLoad() {
		Image titleImage = new Image("/images/Title.png");
		titleImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CancelledEvent());
			}
		});
		RootPanel.get("title").add(titleImage);
		
		appViewer.go(RootPanel.get("content"));
	}
	
}
