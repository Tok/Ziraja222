package ziraja.client.view;

import ziraja.client.presenter.ReportsPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class ReportsView extends Composite implements ReportsPresenter.Display {	
	private final FlexTable contentTable;
	private final FlexTable reportList = new FlexTable();
	
	public ReportsView() {
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("1010px");
		initWidget(contentTableDecorator);

		contentTable = new FlexTable();
		contentTable.setWidth("100%");

		reportList.setWidth("987px");
		contentTable.setWidget(0, 0, reportList);

		contentTableDecorator.add(contentTable);
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public FlexTable getReportList() {
		return reportList;
	}
}
