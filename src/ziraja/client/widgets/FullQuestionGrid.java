package ziraja.client.widgets;

import java.util.HashMap;
import com.google.gwt.event.shared.HandlerManager;
import ziraja.client.event.UpdateLetterEvent;
import ziraja.shared.Letter;

public class FullQuestionGrid extends AbstractQuestionGrid {
    private final HashMap<Letter, Integer> values = new HashMap<Letter, Integer>();
    private String question;

    public FullQuestionGrid(final HandlerManager eventBus) {
        super(eventBus);
    }

    public final void setQuestion(final String question) {
        initTable();
        for (int i = 1; i <= COLUMNS; i++) {
            getTable().setText(0, i - 1, String.valueOf(i));
        }
        final String preparedQuestion = prepareQuestion(question);
        this.question = preparedQuestion;
        values.clear();
        for (int i = 1; i <= preparedQuestion.length(); i++) {
            String letterString = preparedQuestion.substring(i - 1, i);
            Letter letter = Letter.valueOf(letterString);
            Integer temp = values.get(letter);
            if (temp == null) {
                temp = Integer.valueOf(0);
            }
            int column = (i % COLUMNS) - 1;
            int row = (i / COLUMNS) + 1;
            if (column == -1) {
                column = COLUMNS - 1;
                row--;
            }
            getTable().setText(row, column, letterString);
            getTable().getCellFormatter().addStyleName(row, column, "FlexTable-Cell");
            values.put(letter, Integer.valueOf(temp.intValue() + (column + 1)));
        }
        getEventBus().fireEvent(new UpdateLetterEvent());
    }

    private String prepareQuestion(final String question) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < question.length(); i++) {
            for (Letter letter : Letter.values()) {
                if (letter.name().equals(question.substring(i, i + 1).toUpperCase())) {
                    result.append(letter.name());
                }
            }
        }
        return result.toString();
    }

    public final int getLetterValue(final Letter letter) {
        return values.get(letter).intValue();
    }

    public final HashMap<Letter, Integer> getLetterValues() {
        return values;
    }

    public final void reInit() {
        super.initTable();
        values.clear();
    }

    public final String toString() {
        return question;
    }

}
