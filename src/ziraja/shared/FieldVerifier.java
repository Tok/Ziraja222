package ziraja.shared;

public class FieldVerifier {

	public static String validateQuestion(String question) {
		if (question == null || question.equals("")) {
			return "No question asked.";
		}
		if (question.length() <= 7) {
			return "Question must have at least 7 letters.";
		}
		if (!question.endsWith("?")) {
			return "Question must end with a question-mark.";
		}		
		return "";
	}

}
