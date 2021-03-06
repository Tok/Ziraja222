package ziraja.client.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import ziraja.client.event.DoAnswerEvaluationEvent;
import ziraja.shared.Letter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AnswerGrid extends AbstractQuestionGrid {
    private final LinkedHashMap<String, RadioButton> radioButtons = new LinkedHashMap<String, RadioButton>();

    private String newLetterString;
    private final Button resetButton = new Button("Reset");
    private final Button blankOutButton = new Button("Blank");
    private final Button rearrangeButton = new Button("Rearrange");
    private boolean isShuffled = false;

    public AnswerGrid(final HandlerManager eventBus) {
        super(eventBus);
        resetButton.setTitle("Reset order and selections to restore original quality rating.");
        blankOutButton.setTitle("Set all selections to blank.");
        rearrangeButton.setTitle("Randomly rearrange grid (will affect quality rating).");
        bind();
    }

    private void bind() {
        resetButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                doReset();
            }
        });
        blankOutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                doBlank();
            }
        });
        rearrangeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                doRearrange();
            }
        });
    }

    public final void reInit() {
        super.initTable();
    }

    private void makeGrid(final String letterString, final boolean doReInit) {
        radioButtons.clear();
        for (int i = 1; i <= letterString.length(); i++) {
            final String letter = letterString.substring(i - 1, i);
            if (i <= COLUMNS) {
                // first row
                VerticalPanel panel = fillCheckBoxPanel(Letter.valueOf(letter), i - 1, doReInit);
                getTable().setWidget(1, i - 1, panel);
                getTable().getCellFormatter().addStyleName(1, i - 1, "FlexTable-Cell");
            } else if (i <= COLUMNS * 2) {
                // second row
                VerticalPanel panel = fillCheckBoxPanel(Letter.valueOf(letter), i - 1, doReInit);
                getTable().setWidget(2, i - (COLUMNS + 1), panel);
                getTable().getCellFormatter().addStyleName(2, i - (COLUMNS + 1), "FlexTable-Cell");
            } else if (i <= COLUMNS * 3) {
                // third row
                VerticalPanel panel = fillCheckBoxPanel(Letter.valueOf(letter), i - 1, doReInit);
                getTable().setWidget(3, i - ((COLUMNS * 2) + 1), panel);
                getTable().getCellFormatter().addStyleName(3, i - ((COLUMNS * 2) + 1), "FlexTable-Cell");
            }
        }
        final HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(resetButton);
        buttonPanel.add(blankOutButton);
        buttonPanel.add(rearrangeButton);
        getTable().setWidget(4, 0, buttonPanel);
        getTable().getFlexCellFormatter().setColSpan(4, 0, COLUMNS);

        getEventBus().fireEvent(new DoAnswerEvaluationEvent());
    }

    public final void setAnswerValues(final String answerString, final HashMap<Letter, Integer> totalValues) {
        initTable();
        newLetterString = "";
        for (char c : answerString.toCharArray()) {
            Letter letter = Letter.valueOf(String.valueOf(c));
            Integer totalValue = totalValues.get(letter);
            if (totalValue != null) {
                for (Letter newLetter : Letter.values()) {
                    if (newLetter.getNumber() == totalValue) {
                        newLetterString = newLetterString + newLetter.name();
                        break;
                    }
                }
            }
        }
        makeGrid(newLetterString, true);
    }

    private void createCheckBoxes(final Letter letter, final int i) {
        int index = 1;
        for (char c : letter.getGroup().toCharArray()) {
            boolean hasValue = false;
            boolean value = false;
            RadioButton oldButton = radioButtons.get(String.valueOf(i) + ":" + index);
            if (oldButton != null) {
                hasValue = true;
                value = oldButton.getValue();
            }
            RadioButton radioButton = new RadioButton(String.valueOf(i), String.valueOf(c));
            if (hasValue && value) {
                radioButton.setValue(value);
            }
            radioButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(final ValueChangeEvent<Boolean> event) {
                    getEventBus().fireEvent(new DoAnswerEvaluationEvent());
                }
            });
            if (String.valueOf(c).equals(letter.name())) {
                radioButton.setValue(true);
                radioButton.setStyleName("red");
            } else {
                radioButton.setStyleName("black");
            }
            radioButtons.put(String.valueOf(i) + ":" + index, radioButton);
            index++;
        }
        boolean hasValue = false;
        boolean value = false;
        RadioButton oldButton = radioButtons.get(String.valueOf(i) + 0);
        if (oldButton != null) {
            hasValue = true;
            value = oldButton.getValue();
        }
        RadioButton blankButton = new RadioButton(String.valueOf(i), "-");
        if (hasValue && value) {
            blankButton.setValue(value);
        }
        blankButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(final ValueChangeEvent<Boolean> event) {
                getEventBus().fireEvent(new DoAnswerEvaluationEvent());
            }
        });
        radioButtons.put(String.valueOf(i) + ":" + 0, blankButton);
    }

    private VerticalPanel fillCheckBoxPanel(final Letter letter, final int i, final boolean reinit) {
        createCheckBoxes(letter, i);
        VerticalPanel panel = new VerticalPanel();
        HorizontalPanel top = new HorizontalPanel();
        HorizontalPanel bottom = new HorizontalPanel();
        int index = 1;
        for (@SuppressWarnings("unused")
        char c : letter.getGroup().toCharArray()) {
            RadioButton radioButton = radioButtons.get(String.valueOf(i) + ":" + index);
            index++;
            if (radioButton.getStyleName().equals("red")) {
                top.add(radioButton);
            } else {
                bottom.add(radioButton);
            }
        }
        RadioButton blankButton = radioButtons.get(String.valueOf(i) + ":" + 0);
        top.add(blankButton);
        panel.add(top);
        panel.add(bottom);
        return panel;
    }

    private void doReset() {
        final Set<String> keys = radioButtons.keySet();
        for (String key : keys) {
            RadioButton radioButton = radioButtons.get(key);
            if (radioButton.getStyleName().equals("red")) {
                radioButton.setValue(true);
            }
        }
        isShuffled = false;

        makeGrid(newLetterString, true);
    }

    private void doBlank() {
        final Set<String> keys = radioButtons.keySet();
        for (String key : keys) {
            RadioButton radioButton = radioButtons.get(key);
            String text = radioButton.getText();
            if (text.equals("-")) {
                radioButton.setValue(true);
            }
        }
        getEventBus().fireEvent(new DoAnswerEvaluationEvent());
    }

    private void doRearrange() {
        final StringBuilder result = new StringBuilder();
        final List<String> list = new ArrayList<String>();
        for (char c : newLetterString.toCharArray()) {
            list.add(String.valueOf(c));
        }
        shuffle(list);
        for (String letter : list) {
            result.append(letter);
        }
        isShuffled = true;
        makeGrid(result.toString(), false);
    }

    private void shuffle(final List<String> characters) {
        for (int i = characters.size(); i > 1; i--) {
            swap(characters, i - 1, Random.nextInt(i));
        }
    }

    private void swap(final List<String> list, final int i, final int ii) {
        String s = list.get(i);
        list.set(i, list.get(ii));
        list.set(ii, s);
    }

    public final String getAnswer() {
        final StringBuilder result = new StringBuilder();
        final Set<String> keys = radioButtons.keySet();
        for (String key : keys) {
            RadioButton radioButton = radioButtons.get(key);
            if (radioButton.getValue() && !radioButton.getText().equals("-")) {
                result.append(radioButton.getText());
            }
        }
        return result.toString();
    }

    public final int getAnswerQuality() {
        int result = 0;
        final Set<String> keys = radioButtons.keySet();
        for (String key : keys) {
            RadioButton radioButton = radioButtons.get(key);
            if (radioButton.getValue()) {
                if (radioButton.getStyleName().equals("red")) {
                    result += 3;
                }
                if (radioButton.getStyleName().equals("black") && !radioButton.getText().equals("-")) {
                    result += 1;
                }
            }
        }
        if (isShuffled) {
            result = result / 2;
        }
        return result;
    }
}
