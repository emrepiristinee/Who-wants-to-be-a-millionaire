package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordCloud {
	private String user_file;
	private Boolean printinfo = true;

	public WordCloud(String input_user_file) {
		user_file = input_user_file;
	}

	public String getUser_file() {
		return user_file;
	}

	public void setUser_file(String user_file) {
		this.user_file = user_file;
	}

	public void displayWordCloud(int level) {
		try {
			File myQuestions = new File(user_file);
			Scanner myQuestionReader = new Scanner(myQuestions);
			String line1 = "", line2 = "", line3 = "", line4 = "", line5 = ""; // for questions of the same level
			String wordcloud1 = "", wordcloud2 = "", wordcloud3 = "", wordcloud4 = "", wordcloud5 = "",
					QuestionStr = "";
			String newwordcloud1 = "", newwordcloud2 = "", newwordcloud3 = "", newwordcloud4 = "", newwordcloud5 = "";

			int level1_counter = 0, level2_counter = 0, level3_counter = 0, level4_counter = 0, level5_counter = 0; // question
																													// level
																													// counter

			while (myQuestionReader.hasNextLine()) { // separates questions by level
				QuestionStr = myQuestionReader.nextLine();
				if (QuestionStr.endsWith("1") == true)
					line1 = line1 + QuestionStr + " ";
				else if (QuestionStr.endsWith("2") == true)
					line2 = line2 + QuestionStr + " ";
				else if (QuestionStr.endsWith("3") == true)
					line3 = line3 + QuestionStr + " ";
				else if (QuestionStr.endsWith("4") == true)
					line4 = line4 + QuestionStr + " ";
				else if (QuestionStr.endsWith("5") == true)
					line5 = line5 + QuestionStr + " ";
			}

			String[] words1 = line1.split("#");
			String[] words2 = line2.split("#");
			String[] words3 = line3.split("#");
			String[] words4 = line4.split("#");
			String[] words5 = line5.split("#");

			// takes only questions from whole sentence
			for (int i = 0; i < words1.length; i++) {
				if (i % 7 == 1) {
					wordcloud1 = wordcloud1 + words1[i] + " ";
					level1_counter++;
				}
			}
			for (int i = 0; i < words2.length; i++) {
				if (i % 7 == 1) {
					wordcloud2 = wordcloud2 + words2[i] + " ";
					level2_counter++;
				}
			}
			for (int i = 0; i < words3.length; i++) {
				if (i % 7 == 1) {
					wordcloud3 = wordcloud3 + words3[i] + " ";
					level3_counter++;
				}
			}
			for (int i = 0; i < words4.length; i++) {
				if (i % 7 == 1) {
					wordcloud4 = wordcloud4 + words4[i] + " ";
					level4_counter++;
				}
			}
			for (int i = 0; i < words5.length; i++) {
				if (i % 7 == 1) {
					wordcloud5 = wordcloud5 + words5[i] + " ";
					level5_counter++;
				}
			}

			wordcloud1 = wordcloud1.replace("I", "i");
			wordcloud1 = wordcloud1.toLowerCase();
			wordcloud1 = wordcloud1.replace("_", "");
			wordcloud1 = wordcloud1.replace("  ", " ");
			wordcloud1 = wordcloud1.replace(".", "");
			wordcloud1 = wordcloud1.replace("!", "");
			wordcloud1 = wordcloud1.replace("?", "");
			wordcloud1 = wordcloud1.replace(",", "");
			wordcloud1 = wordcloud1.replace("\"", "");

			wordcloud2 = wordcloud2.replace("I", "i");
			wordcloud2 = wordcloud2.toLowerCase();
			wordcloud2 = wordcloud2.replace("_", "");
			wordcloud2 = wordcloud2.replace("  ", " ");
			wordcloud2 = wordcloud2.replace(".", "");
			wordcloud2 = wordcloud2.replace("!", "");
			wordcloud2 = wordcloud2.replace("?", "");
			wordcloud2 = wordcloud2.replace(",", "");
			wordcloud2 = wordcloud2.replace("\"", "");

			wordcloud3 = wordcloud3.replace("I", "i");
			wordcloud3 = wordcloud3.toLowerCase();
			wordcloud3 = wordcloud3.replace("_", "");
			wordcloud3 = wordcloud3.replace("  ", " ");
			wordcloud3 = wordcloud3.replace(".", "");
			wordcloud3 = wordcloud3.replace("!", "");
			wordcloud3 = wordcloud3.replace("?", "");
			wordcloud3 = wordcloud3.replace(",", "");
			wordcloud3 = wordcloud3.replace("\"", "");

			wordcloud4 = wordcloud4.replace("I", "i");
			wordcloud4 = wordcloud4.toLowerCase();
			wordcloud4 = wordcloud4.replace("_", "");
			wordcloud4 = wordcloud4.replace("  ", " ");
			wordcloud4 = wordcloud4.replace(".", "");
			wordcloud4 = wordcloud4.replace("!", "");
			wordcloud4 = wordcloud4.replace("?", "");
			wordcloud4 = wordcloud4.replace(",", "");
			wordcloud4 = wordcloud4.replace("\"", "");

			wordcloud5 = wordcloud5.replace("I", "i");
			wordcloud5 = wordcloud5.toLowerCase();
			wordcloud5 = wordcloud5.replace("_", "");
			wordcloud5 = wordcloud5.replace("  ", " ");
			wordcloud5 = wordcloud5.replace(".", "");
			wordcloud5 = wordcloud5.replace("!", "");
			wordcloud5 = wordcloud5.replace("?", "");
			wordcloud5 = wordcloud5.replace(",", "");
			wordcloud5 = wordcloud5.replace("\"", "");

			// stop words part
			File MyStopWords = new File("stop_words.txt");
			Scanner myStopWordsReader = new Scanner(MyStopWords);
			while (myStopWordsReader.hasNextLine()) {
				String StopWordsStr = myStopWordsReader.nextLine();
				String StopWords = " " + StopWordsStr + " "; // left and right of the deleted word must be empty

				// finds and deletes stop words
				if (wordcloud1.contains(StopWords) || wordcloud2.contains(StopWords) || wordcloud3.contains(StopWords)
						|| wordcloud4.contains(StopWords) || wordcloud5.contains(StopWords)) {
					wordcloud1 = wordcloud1.replace(StopWords, " ");
					wordcloud2 = wordcloud2.replace(StopWords, " ");
					wordcloud3 = wordcloud3.replace(StopWords, " ");
					wordcloud4 = wordcloud4.replace(StopWords, " ");
					wordcloud5 = wordcloud5.replace(StopWords, " ");
				}
			}

			String[] arraywordcloud1 = wordcloud1.split(" ");
			String[] arraywordcloud2 = wordcloud2.split(" ");
			String[] arraywordcloud3 = wordcloud3.split(" ");
			String[] arraywordcloud4 = wordcloud4.split(" ");
			String[] arraywordcloud5 = wordcloud5.split(" ");

			// delete if words not in dictionary.txt
			File MyDictWords = new File("dictionary.txt");
			Scanner myDictWordsReader = new Scanner(MyDictWords);
			while (myDictWordsReader.hasNextLine()) {
				String DictWordsStr = myDictWordsReader.nextLine();

				for (int i = 0; i < arraywordcloud1.length; i++) {
					if (arraywordcloud1[i].equals(DictWordsStr)) {
						newwordcloud1 = newwordcloud1 + arraywordcloud1[i] + " ";
					}
				}
				for (int i = 0; i < arraywordcloud2.length; i++) {
					if (arraywordcloud2[i].equals(DictWordsStr)) {
						newwordcloud2 = newwordcloud2 + arraywordcloud2[i] + " ";
					}
				}
				for (int i = 0; i < arraywordcloud3.length; i++) {
					if (arraywordcloud3[i].equals(DictWordsStr)) {
						newwordcloud3 = newwordcloud3 + arraywordcloud3[i] + " ";
					}
				}
				for (int i = 0; i < arraywordcloud4.length; i++) {
					if (arraywordcloud4[i].equals(DictWordsStr)) {
						newwordcloud4 = newwordcloud4 + arraywordcloud4[i] + " ";
					}
				}
				for (int i = 0; i < arraywordcloud5.length; i++) {
					if (arraywordcloud5[i].equals(DictWordsStr)) {
						newwordcloud5 = newwordcloud5 + arraywordcloud5[i] + " ";
					}
				}
			}

			if (printinfo) {
				Statistic stat = new Statistic(user_file);
				stat.Categories(); // Write category number on the screen
				System.out.println("\nDifficulty level\tThe number of questions");
				System.out.println("\t1\t\t\t  " + level1_counter + "\n\t2\t\t\t  " + level2_counter + "\n\t3\t\t\t  "
						+ level3_counter + "\n\t4\t\t\t  " + level4_counter + "\n\t5\t\t\t  " + level5_counter);
				System.out.println();
				printinfo = false;
			}

			switch (level) {
			case 1:
				System.out.println("Word Cloud of Level 1 = " + newwordcloud1);
				break;
			case 2:
				System.out.println("Word Cloud of Level 2 = " + newwordcloud2);
				break;
			case 3:
				System.out.println("Word Cloud of Level 3 = " + newwordcloud3);
				break;
			case 4:
				System.out.println("Word Cloud of Level 4 = " + newwordcloud4);
				break;
			case 5:
				System.out.println("Word Cloud of Level 5 = " + newwordcloud5);
				break;
			}

			myQuestionReader.close();
			myStopWordsReader.close();
			myDictWordsReader.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			System.exit(0);
		}

	}

}