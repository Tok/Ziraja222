package ziraja.client.widgets;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

public class AbstractQuestionGrid extends Composite {
	private HandlerManager eventBus;
	private final FlexTable table = new FlexTable();
	
	public AbstractQuestionGrid(HandlerManager eventBus) {
		this.eventBus = eventBus;
		initWidget(table);
		
		table.addStyleName("FlexTable");
		table.setWidth("780px");
		
		initTable();
	}
		
	public void initTable() {
		table.removeAllRows();
		table.setWidget(1, 0, new HTML("&nbsp;"));
	}

	public FlexTable getTable() {
		return table;
	}

	public HandlerManager getEventBus() {
		return eventBus;
	}
}
