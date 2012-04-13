package ziraja.client.widgets;

import java.util.HashMap;
import ziraja.client.event.UpdateAnswerEvent;
import ziraja.client.event.UpdateTotalEvent;
import ziraja.shared.Letter;
import com.google.gwt.event.shared.HandlerManager;

public class ReducedQuestionGrid extends AbstractQuestionGrid {
    private static final int LETTERS_IN_ALPHABET = 26;

    private final HashMap<Letter, Integer> totalValues = new HashMap<Letter, Integer>();
    private String preparedQuestion;

    public ReducedQuestionGrid(final HandlerManager eventBus) {
        super(eventBus);
    }

    public final synchronized void setQuestion(final String question) {
        initTable();
        final String preparedQuestion = prepareQuestion(question);
        this.preparedQuestion = preparedQuestion;
        for (int i = 1; i <= preparedQuestion.length(); i++) {
            final String letter = preparedQuestion.substring(i - 1, i);
            final int reducedValue = Letter.valueOf(letter).getNumber();
            final String output = letter + " (" + reducedValue + ")";
            if (i <= COLUMNS) {
                // first row
                getTable().setText(1, i - 1, output);
            } else if (i <= COLUMNS * 2) {
                // second row
                getTable().setText(3, i - (COLUMNS + 1), output);
            } else if (i <= COLUMNS * 3) {
                // third row
                getTable().setText(5, i - ((COLUMNS * 2) + 1), output);
            }
        }
    }

    private String prepareQuestion(final String question) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < question.length(); i++) {
            for (Letter letter : Letter.values()) {
                if (letter.name().equals(question.substring(i, i + 1).toUpperCase()) && !result.toString().contains(letter.name())) {
                    result.append(letter.name());
                }
            }
        }
        return result.toString();
    }

    public final synchronized void setLetterValues(final HashMap<Letter, Integer> letterValues) {
        totalValues.clear();
        for (int i = 1; i <= COLUMNS * 3; i++) {
            String letterString;
            if (i <= COLUMNS) {
                // first row
                if (getTable().isCellPresent(1, i - 1)) {
                    String text = getTable().getText(1, i - 1);
                    if (text != null && text.length() > 0) {
                        letterString = getTable().getText(1, i - 1).substring(0, 1);
                        if (!letterString.equals("\u00A0")) {
                            getTable().setText(2, i - 1, fillTotal(letterValues, letterString));
                            getTable().getCellFormatter().addStyleName(2, i - 1, "FlexTable-Cell");
                        }
                    }
                }
            } else if (i <= COLUMNS * 2) {
                // second row
                if (getTable().isCellPresent(3, i - (COLUMNS + 1))) {
                    String text = getTable().getText(3, i - (COLUMNS + 1));
                    if (text != null && text.length() > 0) {
                        letterString = getTable().getText(3, i - (COLUMNS + 1)).substring(0, 1);
                        getTable().setText(4, i - (COLUMNS + 1), fillTotal(letterValues, letterString));
                        getTable().getCellFormatter().addStyleName(4, i - (COLUMNS + 1), "FlexTable-Cell");
                    }
                }
            } else if (i <= COLUMNS * 3) {
                // third row
                if (getTable().isCellPresent(5, i - ((COLUMNS * 2) + 1))) {
                    String text = getTable().getText(5, i - ((COLUMNS * 2) + 1));
                    if (text != null && text.length() > 0) {
                        letterString = getTable().getText(5, i - ((COLUMNS * 2) + 1)).substring(0, 1);
                        getTable().setText(6, i - ((COLUMNS * 2) + 1), fillTotal(letterValues, letterString));
                        getTable().getCellFormatter().addStyleName(6, i - ((COLUMNS * 2) + 1), "FlexTable-Cell");
                    }
                }
            }
        }

        getEventBus().fireEvent(new UpdateTotalEvent());
        getEventBus().fireEvent(new UpdateAnswerEvent());
    }

    private String fillTotal(final HashMap<Letter, Integer> letterValues, final String letterString) {
        Letter letter = Letter.valueOf(letterString);
        Integer value = letterValues.get(letter);
        int totalValue;
        if (letter != null) {
            totalValue = value + letter.getNumber();
        } else {
            totalValue = 0;
        }
        while (totalValue > LETTERS_IN_ALPHABET) {
            totalValue -= LETTERS_IN_ALPHABET;
        }
        totalValues.put(letter, Integer.valueOf(totalValue));
        return value.toString();
    }

    public final HashMap<Letter, Integer> getTotalValues() {
        return totalValues;
    }

    public final void reInit() {
        super.initTable();
        totalValues.clear();
    }

    public final String toString() {
        return preparedQuestion;
    }
}
