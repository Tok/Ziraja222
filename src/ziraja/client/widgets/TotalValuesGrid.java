package ziraja.client.widgets;

import java.util.HashMap;

import ziraja.shared.Letter;

import com.google.gwt.event.shared.HandlerManager;

public class TotalValuesGrid extends AbstractQuestionGrid {
	
	public TotalValuesGrid(HandlerManager eventBus) {
		super(eventBus);
	}
	
	public synchronized void setQuestion(final String question) {
		initTable();
		final String preparedQuestion = prepareQuestion(question);
		for (int i = 1; i <= preparedQuestion.length(); i++) {
			final String letter = preparedQuestion.substring(i-1,i);
			if (i <= 9) {
				//first row
				getTable().setText(1, i-1, letter);
			} else if (i <= 18) {
				//second row
				getTable().setText(3, i-10, letter);
			} else if (i <= 27) {
				//third row
				getTable().setText(5, i-19, letter);
			}
		}
	}
	
	private String prepareQuestion(String question) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < question.length(); i++) {			
			for (Letter letter : Letter.values()) {
				if (letter.name().equals(question.substring(i,i+1).toUpperCase()) 
						&& !result.toString().contains(letter.name())) {
					result.append(letter.name());
				}
			}
		}
		return result.toString();
	}

	public void setTotalValues(HashMap<Letter, Integer> totalValues) {
		for (int i = 1; i <= 27; i++) {
			if (i <= 9) {
				//first row
				if (getTable().isCellPresent(1, i-1)) {
					final String letter = getTable().getText(1, i-1);
					if (letter != null && letter.length() > 0) {
						if (!letter.toString().equals("\u00A0")) {
							Integer value = totalValues.get(Letter.valueOf(letter));
							getTable().setText(2, i-1, value.toString());
							getTable().getCellFormatter().addStyleName(2, i-1,"FlexTable-Cell");
						}
					}
				}
			} else if (i <= 18) {
				//second row
				if (getTable().isCellPresent(3, i-10)) {
					final String letter = getTable().getText(3, i-10);
					if (letter != null && letter.length() > 0) {
						Integer value = totalValues.get(Letter.valueOf(letter));
						getTable().setText(4, i-10, value.toString());
						getTable().getCellFormatter().addStyleName(4, i-10,"FlexTable-Cell");
					}
				}
			} else if (i <= 27) {
				//third row
				if (getTable().isCellPresent(5, i-19)) {
					final String letter = getTable().getText(5, i-19);
					if (letter != null && letter.length() > 0) {
						Integer value = totalValues.get(Letter.valueOf(letter));
						getTable().setText(6, i-19, value.toString());
						getTable().getCellFormatter().addStyleName(6, i-19,"FlexTable-Cell");
					}
				}
			}
		}
	}

	public void reInit() {
		super.initTable();
	}
	
}
