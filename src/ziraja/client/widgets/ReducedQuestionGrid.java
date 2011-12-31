package ziraja.client.widgets;

import java.util.HashMap;

import ziraja.client.event.UpdateAnswerEvent;
import ziraja.client.event.UpdateTotalEvent;
import ziraja.shared.Letter;

import com.google.gwt.event.shared.HandlerManager;

public class ReducedQuestionGrid extends AbstractQuestionGrid {
	private final HashMap<Letter, Integer> totalValues = new HashMap<Letter, Integer>();
	private String preparedQuestion;
	
	public ReducedQuestionGrid(HandlerManager eventBus) {
		super(eventBus);
	}
	
	public synchronized void setQuestion(final String question) {
		initTable();
		final String preparedQuestion = prepareQuestion(question);
		this.preparedQuestion = preparedQuestion;
		for (int i = 1; i <= preparedQuestion.length(); i++) {
			final String letter = preparedQuestion.substring(i-1,i);
			final int reducedValue = Letter.valueOf(letter).number;
			final String output = letter + " (" + reducedValue + ")";
			if (i <= 9) {
				//first row
				getTable().setText(1, i-1, output);
			} else if (i <= 18) {
				//second row
				getTable().setText(3, i-10, output);
			} else if (i <= 27) {
				//third row
				getTable().setText(5, i-19, output);
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

	public synchronized void setLetterValues(HashMap<Letter, Integer> letterValues) {
		totalValues.clear();
		for (int i = 1; i <= 27; i++) {
			String letterString;
			if (i <= 9) {
				//first row
				if (getTable().isCellPresent(1, i-1)) {
					String text = getTable().getText(1, i-1);
					if (text != null && text.length() > 0) {
						letterString = getTable().getText(1, i-1).substring(0, 1);
						if (!letterString.equals("\u00A0")) {
							getTable().setText(2, i-1, fillTotal(letterValues, letterString));
							getTable().getCellFormatter().addStyleName(2, i-1,"FlexTable-Cell");
						}
					}
				}
			} else if (i <= 18) {
				//second row
				if (getTable().isCellPresent(3, i-10)) {
					String text = getTable().getText(3, i-10);
					if (text != null && text.length() > 0) {
						letterString = getTable().getText(3, i-10).substring(0, 1);
						getTable().setText(4, i-10, fillTotal(letterValues, letterString));
						getTable().getCellFormatter().addStyleName(4, i-10,"FlexTable-Cell");
					}
				}
			} else if (i <= 27) {
				//third row
				if (getTable().isCellPresent(5, i-19)) {
					String text = getTable().getText(5, i-19);
					if (text != null && text.length() > 0) {
						letterString = getTable().getText(5, i-19).substring(0, 1);
						getTable().setText(6, i-19, fillTotal(letterValues, letterString));
						getTable().getCellFormatter().addStyleName(6, i-19,"FlexTable-Cell");
					}
				}
			}
		}
		
		getEventBus().fireEvent(new UpdateTotalEvent());
		getEventBus().fireEvent(new UpdateAnswerEvent());
	}
	
	private String fillTotal(HashMap<Letter, Integer> letterValues, String letterString) {
		Letter letter = Letter.valueOf(letterString);
		Integer value = letterValues.get(letter);
		int totalValue;
		if (letter != null) {
			totalValue = value + letter.number;
		} else {
			totalValue = 0;
		}
		while (totalValue > 26) {
			totalValue -= 26; 
		}
		totalValues.put(letter, Integer.valueOf(totalValue));
		return value.toString();
	}
	
	public HashMap<Letter, Integer> getTotalValues() {
		return totalValues;
	}
	
	public void reInit() {
		super.initTable();
		totalValues.clear();
	}

	public String toString() {
		return preparedQuestion;
	}
}
