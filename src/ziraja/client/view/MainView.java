package ziraja.client.view;

import ziraja.client.presenter.MainPresenter;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MainView extends Composite implements MainPresenter.Display {	
	private final FlexTable contentTable;
	
	public MainView() {
		final DecoratorPanel contentTableDecorator = new DecoratorPanel();
		contentTableDecorator.setWidth("100%");
		contentTableDecorator.setWidth("1010px");
		initWidget(contentTableDecorator);

		contentTable = new FlexTable();
		contentTable.setWidth("100%");
		
		//http://arithmea2000.appspot.com/#add/ZIRAJA
		int row = 0;
		Label titleLabel = new Label("Welcome to the Ziraja Numerology Web-Appilication.");
		titleLabel.setWidth("987px");
		contentTable.setWidget(row++, 0, titleLabel);
		contentTable.setText(row++, 0, "If you are not familiar with Ziraja Numerology, please watch those three videos on YouTube:");
		final HorizontalPanel linkPanel = new HorizontalPanel();
		linkPanel.setSpacing(10);
		linkPanel.add(new Anchor("Part 1", "http://www.youtube.com/watch?v=DFw9zANOJ40"));
		linkPanel.add(new Anchor("Part 2", "http://www.youtube.com/watch?v=uxH-Joj1kiI"));
		linkPanel.add(new Anchor("Part 3", "http://www.youtube.com/watch?v=LG80RCK7pEw"));		
		contentTable.setWidget(row++, 0, linkPanel);
		contentTable.setText(row++, 0, "Thanks to the Magic Society of the White Flame for releaseing this great information.");

		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row++, 0, "How to use this application:");
		contentTable.setText(row++, 0, "1. Go to the \"Question\" tab and enter your question as specific as possible.");
		contentTable.setText(row++, 0, "2. Click the \"Go\" button and see how your tables and the answer grid fill up.");
		contentTable.setText(row++, 0, "3. Go to the answer grid and try to find an answer, by selecting different letter combinations.");
		contentTable.setText(row++, 0, "   Just play around with the possibilities until something meaningful comes up.");
		contentTable.setText(row++, 0, "   You can choose different letters in each group, or omit the letters by selecting \"-\".");
		contentTable.setText(row++, 0, "4. If you cannot find an answer, click the \"Rearrange\" button to rearrange the letters.");
		contentTable.setText(row++, 0, "5. When you find a meaningful answer, please comment and submit your report.");
				
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));
		
		contentTable.setText(row++, 0, "The quality rating describes how good your answer fits the letters of the raw answer string.");
		contentTable.setText(row++, 0, "Here is how the quality of the answer is rated:");
		contentTable.setText(row++, 0, "- Every primary letter (red) you use adds 3 points to the rating.");
		contentTable.setText(row++, 0, "- Every secondary letter (black) you use adds 1 point.");
		contentTable.setText(row++, 0, "- Omitting a letter doesn't give you point, so longer answers will get a better rating.");
		contentTable.setText(row++, 0, "- Rearranging the letters will half the rating because it changes the original order.");
		contentTable.setText(row++, 0, "  But after using it once, it doesnt matter how many times you use it, because the anagrams stay the same.");
		contentTable.setText(row++, 0, "  You can also reset to the original order by clicking \"Reset\" or start with an empty answer by clicking \"Blank\".");
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));
		
		contentTable.setText(row++, 0, "It's often possible to answer a question with YES or NO, by rearranging and reselecting only those letters.");
		contentTable.setText(row++, 0, "Doing this is encouranged when no better answers can be found, but it gives a bad quality rating for a short answer.");
		contentTable.setText(row++, 0, "The method favors answering with possibilities that allow NO over YES, because the word has less letters.");
		
		contentTable.setWidget(row++, 0, new HTML("&nbsp;"));

		contentTable.setText(row++, 0, "Please don't submit any reports, before arranging the letters to a more or less meaningful answer.");
		contentTable.setText(row++, 0, "If the meaning of your answer is not obvious, please comment it in order to prevent deletion.");

		contentTableDecorator.add(contentTable);
	}
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public FlexTable getContentTable() {
		return contentTable;
	}
}
