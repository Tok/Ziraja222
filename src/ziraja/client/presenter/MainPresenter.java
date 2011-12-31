package ziraja.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainPresenter extends AbstractZirajaPresenter implements Presenter {
	@SuppressWarnings("unused")
	private final Display display;

	public interface Display {
		FlexTable getContentTable();
		Widget asWidget();
	}
	
	public MainPresenter(final HandlerManager eventBus, final TabPanel tabPanel, final Display view) {
		super(eventBus, tabPanel);
		this.display = view;
	}

	public void bind() {
	}
		
	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(super.getTabPanel());
	}
}
