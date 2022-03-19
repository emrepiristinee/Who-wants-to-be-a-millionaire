package test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import enigma.core.Enigma;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoadQuestion {
	private Question[] questionsarray;
	private int countforobj;
	private int level = 1;
	private Boolean fiftybool = true;
	private Boolean doublebool = true;
	public int questionid; // Question ID for answer history.
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)

	public LoadQuestion(int capacity) {
		questionsarray = new Question[capacity];
		countforobj = 0;
	}

	public Question[] getQuestionsarray() {
		return questionsarray;
	}

	public void setQuestionsarray(Question[] questionsarray) {
		this.questionsarray = questionsarray;
	}

	enigma.console.Console cn = Enigma.getConsole("Keyboard");

	public void LoadQuestions2(String questions_file) {
		Scanner scread;
		Scanner scread2;

		try {
			File mydi = new File("dictionary.txt");
			scread2 = new Scanner(mydi);
			int capacity = 0;
//			String QuestionsStr = "";
			while (scread2.hasNextLine()) {
				capacity++; // find capacity dictionary.txt
				scread2.nextLine();
			}
			scread2.close();
			scread = new Scanner(Paths.get("dictionary.txt"), StandardCharsets.UTF_8.name()); // read the dictionary
			String inputdictionary = scread.useDelimiter("\\A").next();
			scread.close();

			File file = new File(questions_file);
			Scanner sc = new Scanner(file); // Read given file.
			File filedict = new File("dictionary.txt");// Load dictionary file.
			Scanner scdict;
			scdict = new Scanner(filedict);
			int count = 0;
			String[] wordsinDict = new String[capacity];
			while (scdict.hasNextLine()) // Read dictionary line by line.
			{
				String inputdict = scdict.nextLine();
				wordsinDict[count] = inputdict;
				count++;

			}

			scdict.close();

			while (sc.hasNextLine()) {
				String input = sc.nextLine();
				String inputlower = input.toLowerCase(Locale.ENGLISH);
				inputlower = inputlower.replace(".", "");
				inputlower = inputlower.replace("?", "");
				inputlower = inputlower.replace("_", "");
				inputlower = inputlower.replace(",", "");
				inputlower = inputlower.replace("'", "");
				inputlower = inputlower.replace("\"", "");
				inputlower = inputlower.replace("  ", " ");
				String[] parts = inputlower.split("#"); // Splits string into parts by given pattern.
				for (int i = 0; i < parts.length; i++) {
					String[] words = parts[i].split(" ");
					for (int j = 0; j < words.length; j++) {
						if (words[j].matches("^[a-zA-Z]*$") != true || words[j].length() < 2) { // Takes alphabetic only
																								// and doesn't take
																								// single characters.
							continue;
						} else if (inputdictionary.contains(words[j]) != true) // Checks if the word is in
																				// dictionary(spell check).
						{
							String rightword = SpellCheck(words[j], wordsinDict);
							if (input.contains(words[j])) {
								input = input.replace(words[j], rightword);
							} else {
								String firstchar = words[j].substring(0, 1);
								String firstchar_replace = rightword.substring(0, 1);
								firstchar = firstchar.toUpperCase(Locale.ENGLISH);
								firstchar_replace = firstchar_replace.toUpperCase(Locale.ENGLISH);
								words[j] = firstchar + words[j].substring(1);
								rightword = firstchar_replace + rightword.substring(1);
								input = input.replace(words[j], rightword);
							}
						}
					}
				}

				String[] part = input.split("#");
				Question q = new Question(part[0], part[1], part[2], part[3], part[4], part[5], part[6], part[7]);
				questionsarray[countforobj] = q;
				countforobj++;
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			System.exit(0);
		}

	}

	LifeLine Lline = new LifeLine(true, true);
	MoneyPrize m = new MoneyPrize(0);

	public String searchwordcloud(String yourselection) {
		String word = "", yourchoice = "";
		Scanner sc = new Scanner(System.in);
		for (int j = 0; j < countforobj; j++) {
			if (questionsarray[j].getQuestion().toLowerCase(Locale.ENGLISH).contains(yourselection)) { // if the
																										// question has
																										// the word of
																										// the
																										// participant
				int tempCursorX = cn.getTextWindow().getCursorX(); // gets the cursor position
				int tempCursorY = cn.getTextWindow().getCursorY(); // gets the cursor position
				System.out.println(questionsarray[j].displayQuestion()); // write question and options on the screen
				cn.getTextWindow().setCursorPosition(50, tempCursorY - 4);
				System.out.println("Money: $" + m.getMoneyPrize());
				if (fiftybool == true) {
					cn.getTextWindow().setCursorPosition(50, tempCursorY - 2);
					System.out.println("50% (Press X)");
				}
				if (doublebool == true) {
					cn.getTextWindow().setCursorPosition(50, tempCursorY - 1);
					System.out.println("Double Dip (Press Y)");
				}

				cn.getTextWindow().setCursorPosition(tempCursorX, tempCursorY); // Move cursor old position
				level++;
				m.setMoneyPrize(level);
				questionid = j + 1; // Question ID taken.
				System.out.print("Enter your choice (E:Exit): \n");
				KeyListener klis = new KeyListener() {
					public void keyTyped(KeyEvent e) {
					}

					public void keyPressed(KeyEvent e) {
						if (keypr == 0) {
							keypr = 1;
							rkey = e.getKeyCode();
						}
					}

					public void keyReleased(KeyEvent e) {
					}
				};
				cn.getTextWindow().addKeyListener(klis);
				int remainingtime = 20;
				long t1 = System.currentTimeMillis();
				while (true) {
					long t2 = System.currentTimeMillis();
					if (t2 - t1 >= 1000) {
						t1 = t2;
						remainingtime--;
						if (remainingtime == 0) {
							System.out.println("Time is up. Game Over.");
							return word;
						} else {
							cn.getTextWindow().setCursorPosition(50, tempCursorY - 4);
							System.out.println("Remaining Time: " + remainingtime + " ");
							cn.getTextWindow().setCursorPosition(tempCursorX, tempCursorY); // Move cursor old position
						}

					}
					if (keypr == 1) {
						if (rkey == KeyEvent.VK_A) {
							cn.getTextWindow().output("A\t");
							yourchoice = "A";
							break;
						}
						if (rkey == KeyEvent.VK_B) {
							cn.getTextWindow().output("B\t");
							yourchoice = "B";
							break;
						}
						if (rkey == KeyEvent.VK_C) {
							cn.getTextWindow().output("C\t");
							yourchoice = "C";
							break;
						}
						if (rkey == KeyEvent.VK_D) {
							cn.getTextWindow().output("D\t");
							yourchoice = "D";
							break;
						}
						if (rkey == KeyEvent.VK_E) {
							cn.getTextWindow().output("E\t");
							yourchoice = "E";
							level--;
							m.setMoneyPrize(level);
							System.out.println("\nCongratulations! You won $" + m.getMoneyPrize());
							break;
						}
						if (rkey == KeyEvent.VK_X && fiftybool == true) {
							cn.getTextWindow().output("X\t");
							yourchoice = "X";
							fiftybool = false;
						}
						if (rkey == KeyEvent.VK_Y && doublebool == true) {
							cn.getTextWindow().output("Y\t");
							yourchoice = "Y";
							doublebool = false;
						}
						keypr = 0;
					}
					keypr = 0;
					try {
						Thread.sleep(20);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (yourchoice.equals("X")) {
						System.out.println("\nYou use joker.");
						Lline.FiftyPercent(questionsarray[j]);
						rkey = 0;
						yourchoice = "";
						System.out.print("Enter your choice (E:Exit): \n");
					} else if (yourchoice.equals("Y")) {
						System.out.println("\nYou use joker.");
						yourchoice = Lline.DoubleDip(questionsarray[j], remainingtime);
						rkey = 0;
						if (yourchoice != "")
							break;
					}
				}
				if (yourchoice.toUpperCase().equals("E")) {
					rkey = 0;
					break;
				}

				if (yourchoice.toUpperCase().equals(questionsarray[j].getCorrect_choice())) {
					System.out.println("\nCorrect Answer!\n");
					word = "true";
					rkey = 0;
					break;
				} else {
					System.out.println("\nWrong Answer, GAME OVER!");
					word = "false";
					rkey = 0;
					break;
				}

				// ----------------------------------------------------

			} else
				word = "Not found your word";
		}
		return word;
	}

	static String SpellCheck(String word, String[] dictionary) {
		// Spell checking starts here.
		for (int k = 0; k < dictionary.length; k++) // For loop for possible words.
		{
			if (dictionary[k].length() == word.length()) // Get in the statement if the possible word is same length as
															// the word.
			{
				int counter = 0;
				for (int f = 0; f < word.length(); f++)
					if (word.charAt(f) == dictionary[k].charAt(f))// Check the similarities with the wrong word.
						counter++;
				if (counter >= word.length() - 2) // If error is not bigger than 2.
				{
					return dictionary[k];
				}

				else // If the user says no, it will continue to search.
					continue;
			}

		}
		return word;

	}

}