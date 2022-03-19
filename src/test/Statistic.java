package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Statistic {

	private String statisticFile;
	private Participant[] participantStr;
	private Question[] statQuestion;
	private Participant[] participantStrplayer;
	private String categoryFile;

	public Statistic(String input_statisticFile, Participant[] participants, Question[] input_statques) {
		statisticFile = input_statisticFile;
		participantStr = participants;
		statQuestion = input_statques;
		participantStrplayer = participants;
	}

	public Statistic(String input_CategoryFile) {
		categoryFile = input_CategoryFile;
	}

	public String getCategoryFile() {
		return categoryFile;
	}

	public void setCategoryFile(String input_categoryFile) {
		this.categoryFile = input_categoryFile;
	}

	public Question[] getQuestionStat() {
		return statQuestion;
	}

	public void setQuestionstat(Question[] input_Questions) {
		this.statQuestion = input_Questions;
	}

	public String getStatisticFile() {
		return statisticFile;
	}

	public Participant[] getParticipants() {
		return participantStr;
	}

	public void setParticipants(Participant[] input_participants) {
		this.participantStr = input_participants;
	}

	public void setStatisticFile(String Statistic_file) {
		this.statisticFile = Statistic_file;
	}

	public void Categories() {

		try {
			File questionsFile = new File(categoryFile);
			Scanner scanner = new Scanner(questionsFile);

			File tempquestionsFile = new File(categoryFile);
			Scanner sc = new Scanner(tempquestionsFile);
			int categoryLine = 0;

			while (sc.hasNextLine()) {
				sc.nextLine();
				categoryLine++;
			}

			String[] allQuestion = new String[categoryLine * 8];
			String[] categoryStr = new String[categoryLine];
			int count = 0;

			while (scanner.hasNextLine()) {
				allQuestion = scanner.nextLine().split("#");
				categoryStr[count] = allQuestion[0];
				count++;
			}

			String categry = " ";
			int countCategory = 0;
			String result;

			System.out.println("Category         The number of questions ");
			System.out.println("--------         -----------------------");

			for (int i = 0; i < categoryStr.length; i++) {
				if (!(categoryStr[i].equals(" ")))
					categry = categoryStr[i];
				for (int k = i; k < categoryStr.length; k++) {
					if (categry.equals(categoryStr[k]) && !(categry.equals(" "))) {
						countCategory++;
						categoryStr[k] = " ";
					}
				}
				if (countCategory != 0 && !(categry.equals(" "))) {
					int n = 30;
					int length = categry.length();
					n = n - length;
					result = String.format("%" + n + "d", countCategory);
					System.out.printf(categry + result);
					System.out.println();

				}
				countCategory = 0;
				categry = " ";
			}
			scanner.close();
			sc.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void variables() throws IOException {
		DecimalFormat df = new DecimalFormat("#.##");

		String statisticDATAString = " ";
		String[] statistic;
		int[] questionIndex;
		int[] participantIndex;
		int[] tempPlayerParticipants;
		int[] tempAgePlayer;
		String[] answer;
		String[] tempAnswer;
		int count = 0;

		File tempVariablesFile = new File(statisticFile);
		Scanner tempScanner = new Scanner(tempVariablesFile);
		int lines = 0;
		while (tempScanner.hasNextLine()) {
			tempScanner.nextLine();
			lines++;
		}
		tempScanner.close();

		statistic = new String[lines];
		questionIndex = new int[lines];
		participantIndex = new int[lines];
		tempPlayerParticipants = new int[lines];
		tempAgePlayer = new int[lines];
		answer = new String[lines];
		tempAnswer = new String[lines];
		FileReader fReader = new FileReader(tempVariablesFile);
		String lineStr;
		BufferedReader br = new BufferedReader(fReader);

		while ((lineStr = br.readLine()) != null) {

			statisticDATAString = lineStr;
			statistic = statisticDATAString.split(",");
			questionIndex[count] = Integer.parseInt(statistic[0]);
			participantIndex[count] = Integer.parseInt(statistic[1]);
			tempPlayerParticipants[count] = Integer.parseInt(statistic[1]);
			tempAgePlayer[count] = Integer.parseInt(statistic[1]);
			answer[count] = statistic[2];
			tempAnswer[count] = statistic[2];
			count++;

		}

		br.close();

		int[] tempQuestion = questionIndex;
		int[] tempParticipants = participantIndex;

		for (int i = 0; i < tempParticipants.length; i++) {
			for (int k = i + 1; k < tempParticipants.length; k++) {
				if (tempParticipants[i] == tempParticipants[k] && tempParticipants[k] != -1)
					tempParticipants[k] = -1;
			}
		}

		String maxCityString = " ";
		int maxCity = 0;
		int tempMaxCity = 0;
		String mostSCity = " ";
		for (int i = 0; i < tempParticipants.length; i++) {

			if (tempParticipants[i] != -1 && tempParticipants[i] != -5) {
				maxCityString = participantStr[tempParticipants[i]].getCityString();
			}
			if (maxCityString != " ") {
				for (int k = 0; k < tempParticipants.length; k++) {
					if (tempParticipants[k] != -5 && tempParticipants[k] != -1
							&& maxCityString.equals(participantStr[tempParticipants[k]].getCityString())) {
						tempParticipants[k] = -5;
						tempMaxCity++;

					}
				}
				if (tempMaxCity > maxCity) {
					maxCity = tempMaxCity;
					mostSCity = maxCityString;
				}
			}
			tempMaxCity = 0;
		}

		int holdPlayer = 0;
		int bestPlayer = 0;
		int countPlayer = 0;
		int MaxCountPlayer = 0;
		for (int i = 0; i < tempPlayerParticipants.length; i++) {
			if (tempPlayerParticipants[i] != -4 && tempPlayerParticipants[i] != -1 && tempPlayerParticipants[i] != -5)
				holdPlayer = tempPlayerParticipants[i];
			for (int k = i; k < tempPlayerParticipants.length; k++) {
				if (tempPlayerParticipants[i] != -1 && tempPlayerParticipants[i] != -1
						&& holdPlayer == tempPlayerParticipants[k] && answer[k].equals("correct")
						&& tempPlayerParticipants[k] != -4) {
					tempPlayerParticipants[k] = -4;
					countPlayer++;
				}
			}
			if (countPlayer > MaxCountPlayer) {
				MaxCountPlayer = countPlayer;
				bestPlayer = holdPlayer;
			}
			countPlayer = 0;
		}

		int maxCorrect = 0;
		int tempCorrect = 0;
		int holdCQuIndex = 0;
		int holdWQuIndex = 0;
		int tempWrong = 0;
		int maxWrong = 0;
		int MostCques = 0;
		int MostWques = 0;

		for (int i = 0; i < answer.length; i++) {
			if (answer[i].equals("correct") && tempQuestion[i] != -1) {
				holdCQuIndex = tempQuestion[i];
				for (int k = i; k < answer.length; k++) {
					if (holdCQuIndex == tempQuestion[k]) {
						tempQuestion[k] = -1;
						tempCorrect++;
					}
				}
				if (tempCorrect > maxCorrect) {
					maxCorrect = tempCorrect;
					MostCques = holdCQuIndex;
				}
				tempCorrect = 0;
			} else if (answer[i].equals("wrong") && tempQuestion[i] != -1) {
				holdWQuIndex = tempQuestion[i];
				for (int l = i; l < answer.length; l++) {
					if (holdWQuIndex == tempQuestion[l]) {
						tempQuestion[l] = -1;
						tempWrong++;
					}
				}
				if (tempWrong > maxWrong) {
					maxWrong = tempWrong;
					MostWques = holdWQuIndex;
				}
				tempWrong = 0;
			}
		}

		double sumCUnder30 = 0;
		double sumCBetw30and50 = 0;
		double sumCUpper50 = 0;
		double sumWUnder30 = 0;
		double sumWBetw30and50 = 0;
		double sumWUpper50 = 0;
		int holdIndex = 0;

		for (int i = 0; i < tempAgePlayer.length; i++) {
			for (int k = 0; k < tempAgePlayer.length; k++) {
				holdIndex = tempAgePlayer[k];
				if (tempAgePlayer[k] != -3 && participantStrplayer[holdIndex].Age() <= 30
						&& tempAnswer[k].equals("correct")) {
					tempAgePlayer[k] = -3;
					sumCUnder30++;
				} else if (tempAgePlayer[k] != -3 && participantStrplayer[holdIndex].Age() <= 30
						&& tempAnswer[k].equals("wrong")) {
					tempAgePlayer[k] = -3;
					sumWUnder30++;
				} else if (tempAgePlayer[k] != -3 && participantStrplayer[holdIndex].Age() > 30
						&& participantStr[holdIndex].Age() <= 50 && tempAnswer[k].equals("correct")) {
					tempAgePlayer[k] = -3;
					sumCBetw30and50++;
				} else if (tempAgePlayer[k] != -3 && participantStrplayer[holdIndex].Age() > 30
						&& participantStr[holdIndex].Age() <= 50 && tempAnswer[k].equals("wrong")) {
					tempAgePlayer[k] = -3;
					sumWBetw30and50++;
				} else if (tempAgePlayer[k] != -3 && participantStr[holdIndex].Age() > 50
						&& tempAnswer[k].equals("correct")) {
					tempAgePlayer[k] = -3;
					sumCUpper50++;
				} else if (tempAgePlayer[k] != -3 && participantStr[holdIndex].Age() > 50
						&& tempAnswer[k].equals("wrong")) {
					tempAgePlayer[k] = -3;
					sumWUpper50++;
				}
				holdIndex = 0;
			}
		}

		double totalUnder30 = sumCUnder30 + sumWUnder30;
		double totalBetw30and50 = sumCBetw30and50 + sumWBetw30and50;
		double totalUpper50 = sumCUpper50 + sumWUpper50;

		double aveUnder30 = sumCUnder30 / totalUnder30;
		double aveBetw30and50 = sumCBetw30and50 / totalBetw30and50;
		double aveUpper50 = sumCUpper50 / totalUpper50;

		if (totalUnder30 == 0)
			aveUnder30 = 0;
		if (totalBetw30and50 == 0)
			aveBetw30and50 = 0;
		if (totalUpper50 == 0)
			aveUpper50 = 0;

		System.out.println("\n•	The most successful contestant : " + participantStrplayer[bestPlayer].getName());
		System.out
				.println("•	The category with the most correctly answered : " + statQuestion[MostCques].getCategory());
		System.out.println("•	The category with the most badly answered : " + statQuestion[MostWques].getCategory());
		System.out.println("•	Age <= 30  " + df.format(aveUnder30) + "  30 < Age <= 50  " + df.format(aveBetw30and50)
				+ "  Age > 50  " + df.format(aveUpper50));
		System.out.println("•	The city with the highest number of participants : " + mostSCity);

	}

}