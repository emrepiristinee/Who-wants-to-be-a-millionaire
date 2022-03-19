package test;

public class Question {
	private String category;
	private String question;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String correct_choice;
	private String dificult;

	public Question(String inputcategory, String inputquestion, String inputchoiceA, String inputchoiceB,
			String inputchoiceC, String inputchoiceD, String inputcorrectchoice, String inputdificult) {
		category = inputcategory;
		question = inputquestion;
		choiceA = inputchoiceA;
		choiceA = inputchoiceA;
		choiceB = inputchoiceB;
		choiceC = inputchoiceC;
		choiceD = inputchoiceD;
		correct_choice = inputcorrectchoice;
		dificult = inputdificult;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestions(String question) {
		this.question = question;
	}

	public String getChoiceA() {
		return choiceA;
	}

	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}

	public String getChoiceB() {
		return choiceB;
	}

	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}

	public String getChoiceC() {
		return choiceC;
	}

	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}

	public String getChoiceD() {
		return choiceD;
	}

	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}

	public String getCorrect_choice() {
		return correct_choice;
	}

	public void setCorrect_choice(String correct_choice) {
		this.correct_choice = correct_choice;
	}

	public String getDificult() {
		return dificult;
	}

	public void setDificult(String part) {
		this.dificult = part;
	}

	public String displayQuestion() {
		return question + "\nA) " + choiceA + "\nB) " + choiceB + "\nC) " + choiceC + "\nD) " + choiceD;
	}
}