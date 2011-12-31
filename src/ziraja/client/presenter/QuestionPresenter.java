package ziraja.client.presenter;

import java.util.Date;

import ziraja.client.service.PersistenceServiceAsync;
import ziraja.client.widgets.AnswerGrid;
import ziraja.client.widgets.FullQuestionGrid;
import ziraja.client.widgets.ReducedQuestionGrid;
import ziraja.client.widgets.TotalValuesGrid;
import ziraja.shared.data.Report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QuestionPresenter extends AbstractTabPresenter {
	private PersistenceServiceAsync persistenceService;
	private HandlerRegistration registration = null;
	private boolean processing = false;

	public interface Display {
		TextBox getQuestionTextBox();
		Button getQuestionButton();
		FullQuestionGrid getFullQuestionGrid();
		ReducedQuestionGrid getReducedQuestionGrid();
		TotalValuesGrid getTotalValuesGrid();
		AnswerGrid getAnswerGrid();
		Widget asWidget();
		Label getOriginalQuestionLabel();
		Label getQualityLabel();
		Label getRawAnswerLabel();
		Label getAnswerLabel();		
		Button getSubmitButton();
		TextBox getCommentTextBox();
		Label getDoesItMeanLabel();
		Label getStatusLabel();
	}
	
	private final Display display;

	public QuestionPresenter(final HandlerManager eventBus, final TabPanel tabPanel, final Display view, final PersistenceServiceAsync persistenceService) {
		super(eventBus, tabPanel);
		this.persistenceService = persistenceService;
		this.display = view;
	}

	public void bind() {
		this.display.getQuestionButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("question/" + display.getQuestionTextBox().getValue());
			}
		});
		this.display.getQuestionTextBox().addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					History.newItem("question/" + display.getQuestionTextBox().getValue());
				}
			}
		});

		if (registration == null) {
		registration = this.display.getSubmitButton().addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if (!processing) {
					display.getStatusLabel().setText("Submitting report.");
					processing = true;
					display.getSubmitButton().setEnabled(false);

					Report report = new Report();
					report.setQuestion(display.getOriginalQuestionLabel().getText());
					report.setQuality(Integer.valueOf(display.getQualityLabel().getText()));
					report.setRawAnswer(display.getRawAnswerLabel().getText());
					report.setAnswer(display.getAnswerLabel().getText());
					report.setDoesItMean(display.getDoesItMeanLabel().getText());
					report.setComment(display.getCommentTextBox().getText());
					report.setTimeStamp(new Date());

					persistenceService.saveReport(report, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							display.getStatusLabel().setText("Report submitted.");
							display.getSubmitButton().setEnabled(true);
							processing = false;
						}
						@Override
						public void onFailure(Throwable caught) {
							display.getStatusLabel().setText("Fail submitting Report.");
							display.getSubmitButton().setEnabled(true);
							processing = false;
						}
					});
				}
			}
		});
		}
	}
		
	public void fillTables() {
		String question = display.getQuestionTextBox().getValue();
		display.getFullQuestionGrid().reInit();
		display.getReducedQuestionGrid().reInit();
		display.getTotalValuesGrid().reInit();
		display.getAnswerGrid().reInit();
		
		display.getTotalValuesGrid().setQuestion(question);
		display.getReducedQuestionGrid().setQuestion(question);
		display.getFullQuestionGrid().setQuestion(question);
	}
	
	public void go(final HasWidgets container) {
		container.clear();
		container.add(super.getTabPanel());
		
		bind();
	}
		
}
