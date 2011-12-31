package ziraja.client.presenter;

import java.util.Date;

import ziraja.shared.FieldVerifier;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;

public abstract class AbstractZirajaPresenter extends AbstractTabPresenter implements Presenter {
	boolean isRunning = false;
	boolean isPaused = false;
	Date sessionStart;
	Date sessionEnd;
	
	public AbstractZirajaPresenter(final HandlerManager eventBus, final TabPanel tabPanel) {
		super(eventBus, tabPanel);
	}

	/**
	 * Validates question in the TextBox and sets error message on configStatusLabel.
	 * @param questionTextBox
	 * @param configStatusLabel
	 */
	public void validateInput(TextBox questionTextBox, HasText configStatusLabel) {
		String question = questionTextBox.getValue();
		String questionMessage = FieldVerifier.validateQuestion(question);
		
		if (!questionMessage.equals("")) {
			questionTextBox.setFocus(true);
			configStatusLabel.setText(questionMessage);			
		}
	}
	
}
