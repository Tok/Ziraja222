package ziraja.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractTabPresenter implements Presenter {
	private TabPanel tabPanel;	
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	public AbstractTabPresenter(final HandlerManager eventBus, final TabPanel tabPanel) {
		this.tabPanel = tabPanel;
		this.eventBus = eventBus;
	}
	
	public Widget getTabPanel() {
		return tabPanel;
	}

}
