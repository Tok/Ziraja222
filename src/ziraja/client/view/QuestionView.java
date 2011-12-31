package ziraja.client.view;

import ziraja.client.presenter.QuestionPresenter;
import ziraja.client.service.DoesItMeanServiceAsync;
import ziraja.client.service.PersistenceServiceAsync;
import ziraja.client.widgets.AnswerGrid;
import ziraja.client.widgets.FullQuestionGrid;
import ziraja.client.widgets.ReducedQuestionGrid;
import ziraja.client.widgets.TotalValuesGrid;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QuestionView extends Composite implements QuestionPresenter.Display {
	private DoesItMeanServiceAsync doesItMeanService;
	private final FlexTable contentTable = new FlexTable();
	private final Label questionLabel = new Label("Please enter your Question:");
	private final HorizontalPanel questionPanel = new HorizontalPanel();	
	private final TextBox questionBox = new TextBox();
	private final Button questionButton = new Button("Go");
	private final HorizontalPanel commentPanel = new HorizontalPanel();
	private final TextBox commentBox = new TextBox();
	private final Button submitButton = new Button("Submit");

	private FullQuestionGrid fullQuestionGrid;
	private ReducedQuestionGrid reducedQuestionGrid;
	private TotalValuesGrid totalValuesGrid;
	private AnswerGrid answerGrid;
	
	private final Label originalQuestionLabel = new Label();
	private final Label rawAnswerLabel = new Label();
	private final Label answerLabel = new Label();
	private final Label qualityLabel = new Label();
	private final Anchor anagramsLink = new Anchor();
	private final Label doesItMeanLabel = new Label();
	private final Label statusLabel = new Label();
	
	public QuestionView(HandlerManager eventBus, final PersistenceServiceAsync persistenceService, final DoesItMeanServiceAsync doesItMeanService) {
		this.doesItMeanService = doesItMeanService;
		
		fullQuestionGrid = new FullQuestionGrid(eventBus);
		reducedQuestionGrid = new ReducedQuestionGrid(eventBus);
		totalValuesGrid = new TotalValuesGrid(eventBus);
		answerGrid = new AnswerGrid(eventBus);
		
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("1010px");
		initWidget(contentTableDecorator);
		
		contentTable.setWidth("987px");
		
		int row = 0;
		questionLabel.setWidth("200px");
		contentTable.setWidget(row, 0, questionLabel);
		
		questionPanel.setSpacing(5);
		questionBox.setWidth("670px");
			questionPanel.add(questionBox);
		questionButton.setWidth("80px");
		questionPanel.add(questionButton);
		contentTable.setWidget(row++, 1, questionPanel);
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row, 0, "Full Question:");
		contentTable.setWidget(row++, 1, fullQuestionGrid);

		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row, 0, "Reduced Question:");
		contentTable.setWidget(row++, 1, reducedQuestionGrid);
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row, 0, "Total Values:");
		contentTable.setWidget(row++, 1, totalValuesGrid);
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row, 0, "Answer Grid:");
		contentTable.setWidget(row++, 1, answerGrid);
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row, 0, "Original Question:");
		contentTable.setWidget(row++, 1, originalQuestionLabel); 

		contentTable.setText(row, 0, "Raw Answer:");
		contentTable.setWidget(row++, 1, rawAnswerLabel); 

		contentTable.setText(row, 0, "Answer:");
		answerLabel.setStyleName("answer");
		contentTable.setWidget(row++, 1, answerLabel); 
		
		contentTable.setText(row, 0, "Answer Quality:");
		contentTable.setWidget(row++, 1, qualityLabel); 
		
		contentTable.setText(row, 0, "Anagrams:");
		contentTable.setWidget(row++, 1, anagramsLink); 

		contentTable.setText(row, 0, "Does it mean:");
		contentTable.setWidget(row++, 1, doesItMeanLabel);

		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));
		
		contentTable.setText(row, 0, "Comment and Submit Report:");
		commentPanel.setSpacing(5);
		commentBox.setWidth("670px");
		commentPanel.add(commentBox);
		submitButton.setWidth("80px");
		commentPanel.add(submitButton);
		contentTable.setWidget(row++, 1, commentPanel);
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row, 0, "Status:");
		contentTable.setWidget(row++, 1, statusLabel);
		
		contentTableDecorator.add(contentTable);
		
	}

	public void setQuestion(String token) {
		questionBox.setText(token);
		questionBox.selectAll();
		originalQuestionLabel.setText(token);
	}

	public void resetFocus() {
		questionBox.selectAll();
		questionBox.setFocus(true);		
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public TextBox getQuestionTextBox() {
		return questionBox;
	}
	
	@Override
	public Button getQuestionButton() {
		return questionButton;
	}

	@Override
	public FullQuestionGrid getFullQuestionGrid() {
		return fullQuestionGrid;
	}

	@Override
	public ReducedQuestionGrid getReducedQuestionGrid() {
		return reducedQuestionGrid;
	}

	@Override
	public TotalValuesGrid getTotalValuesGrid() {
		return totalValuesGrid;
	}	
	
	@Override
	public AnswerGrid getAnswerGrid() {
		return answerGrid;
	}
	
	@Override
	public TextBox getCommentTextBox() {
		return commentBox;
	}
	
	@Override
	public Button getSubmitButton() {
		return submitButton;
	}
	
	public void doUpdateLetter() {
		reducedQuestionGrid.setLetterValues(fullQuestionGrid.getLetterValues());
	}

	public void doUpdateTotal() {
		totalValuesGrid.setTotalValues(reducedQuestionGrid.getTotalValues());
	}

	public void doUpdateAnswer() {
		String answer = reducedQuestionGrid.toString();
		answerGrid.setAnswerValues(answer, reducedQuestionGrid.getTotalValues());
		rawAnswerLabel.setText(answerGrid.getAnswer());
	}

	public void doAnswerEvaluation() {
		String answer = answerGrid.getAnswer();
		answerLabel.setText(answer);
		String href = "http://wordsmith.org/anagram/anagram.cgi?anagram=" + answer;
		if (!answer.equals("")) {
			anagramsLink.setHref(href);
			anagramsLink.setText(href);
		} else {
			anagramsLink.setHref("");
			anagramsLink.setText("");			
		}
		int quality = answerGrid.getAnswerQuality();
		qualityLabel.setText(String.valueOf(quality));
		
		doesItMeanService.doesItMean(answer, new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				doesItMeanLabel.setText(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				doesItMeanLabel.setText("...");				
			}
		});
		statusLabel.setText("");
	}

	@Override
	public Label getOriginalQuestionLabel() {
		return originalQuestionLabel;
	}

	@Override
	public Label getRawAnswerLabel() {
		return rawAnswerLabel;
	}

	@Override
	public Label getAnswerLabel() {
		return answerLabel;
	}

	@Override
	public Label getDoesItMeanLabel() {
		return doesItMeanLabel;
	}

	@Override
	public Label getStatusLabel() {
		return statusLabel;
	}

	@Override
	public Label getQualityLabel() {
		return qualityLabel;
	}

}
