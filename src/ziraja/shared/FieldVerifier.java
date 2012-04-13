package ziraja.shared;

public final class FieldVerifier {
    private static final int MAX_QUESTION_LENGTH = 7;

    private FieldVerifier() {
    }

    public static String validateQuestion(final String question) {
        if (question == null || question.equals("")) {
            return "No question asked.";
        }
        if (question.length() <= MAX_QUESTION_LENGTH) {
            return "Question must have at least 7 letters.";
        }
        if (!question.endsWith("?")) {
            return "Question must end with a question-mark.";
        }
        return "";
    }
}
