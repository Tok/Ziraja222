package ziraja.client.presenter;

import java.util.Date;
import ziraja.shared.FieldVerifier;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;

public abstract class AbstractZirajaPresenter extends AbstractTabPresenter implements Presenter {
    private boolean isRunning = false;
    private boolean isPaused = false;
    private Date sessionStart;
    private Date sessionEnd;

    public AbstractZirajaPresenter(final HandlerManager eventBus, final TabPanel tabPanel) {
        super(eventBus, tabPanel);
    }

    /**
     * Validates question in the TextBox and sets error message on
     * configStatusLabel.
     * @param questionTextBox
     * @param configStatusLabel
     */
    public final void validateInput(final TextBox questionTextBox, final HasText configStatusLabel) {
        String question = questionTextBox.getValue();
        String questionMessage = FieldVerifier.validateQuestion(question);
        if (!questionMessage.equals("")) {
            questionTextBox.setFocus(true);
            configStatusLabel.setText(questionMessage);
        }
    }

    public final boolean isRunning() {
        return isRunning;
    }

    public final void setRunning(final boolean isRunning) {
        this.isRunning = isRunning;
    }

    public final boolean isPaused() {
        return isPaused;
    }

    public final void setPaused(final boolean isPaused) {
        this.isPaused = isPaused;
    }

    public final Date getSessionStart() {
        return sessionStart;
    }

    public final void setSessionStart(final Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public final Date getSessionEnd() {
        return sessionEnd;
    }

    public final void setSessionEnd(final Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

}
