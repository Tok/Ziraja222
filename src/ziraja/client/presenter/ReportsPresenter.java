package ziraja.client.presenter;

import java.util.ArrayList;
import ziraja.client.service.PersistenceServiceAsync;
import ziraja.shared.data.Report;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class ReportsPresenter extends AbstractZirajaPresenter implements Presenter {
    private final PersistenceServiceAsync persistenceService;
    private final Display display;

    public interface Display {
        FlexTable getReportList();
        Widget asWidget();
    }

    public ReportsPresenter(final HandlerManager eventBus, final TabPanel tabPanel, final Display view, final PersistenceServiceAsync persistenceService) {
        super(eventBus, tabPanel);
        this.display = view;
        this.persistenceService = persistenceService;
    }

    public final void bind() {
    }

    public final void go(final HasWidgets container) {
        bind();
        prepareTable();
        container.clear();
        container.add(super.getTabPanel());
    }

    private void prepareTable() {
        display.getReportList().removeAllRows();
        display.getReportList().setText(0, 0, "Loading...");
        persistenceService.getReports(new AsyncCallback<ArrayList<Report>>() {
            @Override
            public void onSuccess(final ArrayList<Report> result) {
                display.getReportList().removeAllRows();
                display.getReportList().setText(0, 0, "Question");
                display.getReportList().setText(0, 1, "Quality");
                display.getReportList().setText(0, 2, "Answer");
                display.getReportList().setText(0, 3, "Does it mean");
                display.getReportList().setText(0, 4, "Comment");
                display.getReportList().setText(0, 5, "Raw Answer");
                display.getReportList().setText(0, 6, "Timestamp");
                int row = 1;
                for (final Report report : result) {
                    String question = report.getQuestion();
                    Hyperlink link = new Hyperlink(question, "question/" + question);
                    display.getReportList().setWidget(row, 0, link);
                    display.getReportList().getCellFormatter().setAlignment(row, 1, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
                    display.getReportList().setText(row, 1, String.valueOf(report.getQuality()));
                    final Anchor anchor = new Anchor(report.getAnswer(), "http://wordsmith.org/anagram/anagram.cgi?anagram=" + report.getAnswer());
                    display.getReportList().setWidget(row, 2, anchor);
                    display.getReportList().setText(row, 3, report.getDoesItMean());
                    display.getReportList().setText(row, 4, report.getComment());
                    display.getReportList().setText(row, 5, report.getRawAnswer());
                    display.getReportList().getCellFormatter().setAlignment(row, 6, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
                    display.getReportList().setText(row, 6, DateTimeFormat.getFormat("yyyy.MM.dd HH:mm:ss").format(report.getTimeStamp()));
                    row++;
                }
            }
            @Override
            public void onFailure(final Throwable caught) {
                display.getReportList().removeAllRows();
                display.getReportList().setText(0, 0, "Fail: " + caught.getMessage());
            }
        });
    }

}
