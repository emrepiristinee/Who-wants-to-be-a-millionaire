package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import enigma.core.Enigma;

public class Main {

	public static void main(String[] args) throws IOException {
		enigma.console.Console cn = Enigma.getConsole("Keyboard");
		boolean flag = true; // For game loop.
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String QuestionFile = "";
		String ParticipantsFile = "";
		int participantnum = 0, capacity = 0;
		Participant[] participants = null;
		System.out.println("	-MENU-		"); // Printing menu
		System.out.println("1.Load Questions\n" + "2.Load Participants\n3.Start Competition\n4.Show Statics\n5.Exit");
		LoadQuestion lq; // produces an object from LoadQuestion class.
		while (flag == true) // Main game loop
		{
			System.out.print("  Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Please enter file name to load: "); // Load Questions
				QuestionFile = sc.nextLine();
				File mydi = new File(QuestionFile);
				sc2 = new Scanner(mydi);
				while (sc2.hasNextLine()) { // to find the capacity dictionary.txt
					sc2.nextLine();
					capacity++;
				}
				sc2.close();
				break;
			case 2:
				System.out.print("Please enter file name to load: "); // Load Participants
				ParticipantsFile = sc.nextLine();
				String tempParticipantFile = ParticipantsFile;
				File file = new File(ParticipantsFile);

				try {
					Scanner scanDataFile = new Scanner(file);
					File tempDataFile = new File(tempParticipantFile);
					Scanner linenumber = new Scanner(tempDataFile);
					String fileData = "";
					int count = 0;

					while (linenumber.hasNextLine()) {
						linenumber.nextLine();
						participantnum++;
					}
					linenumber.close();
					String[] words = new String[4 * participantnum];
					String[] playersName = new String[participantnum];
					String[] playersBirthdate = new String[participantnum];
					String[] playersPhone = new String[participantnum];
					String[] playerAddress = new String[participantnum];

					while (scanDataFile.hasNextLine()) {
						fileData = scanDataFile.nextLine();
						words = fileData.split("#");
						playersName[count] = words[0];
						playersBirthdate[count] = words[1];
						playersPhone[count] = words[2];
						playerAddress[count] = words[3];
						count++;
					}

					participants = new Participant[participantnum];
					for (int i = 0; i < playersName.length; i++) {

						participants[i] = new Participant(playersName[i], playersBirthdate[i], playerAddress[i],
								playersPhone[i]);
					}

					scanDataFile.close();

				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
					System.exit(0);
				}
				break;

			case 3:
				if (QuestionFile.equals("") || ParticipantsFile.equals("")) {
					System.out.println("You are not enter questions file or participants file!");
					break;
				}
				lq = new LoadQuestion(capacity); // produces an object from LoadQuestion class.
				lq.LoadQuestions2(QuestionFile); // Question file entered by the participant
				WordCloud w1 = new WordCloud(QuestionFile); // produces an object from WordCloud class
				int level = 1; // questions and word cloud level
				String correctorwrong = ""; // Checking if the participant has been eliminated

				Random r = new Random();
				int participantid = (int) r.nextInt(participants.length - 1); // to choose random participant
				System.out.println("Constestans: " + participants[participantid].getName()); // print the screen
																								// participant

				File file2 = new File("answer_history.txt"); // New file.
				FileWriter fWriter = new FileWriter(file2, true); // Crates filewriter for writing the answer history.

				while (true) {
					w1.displayWordCloud(level); // brings current level 1
					System.out.print("\nEnter your selection: ");
					String userselection = sc.nextLine();

					correctorwrong = lq.searchwordcloud(userselection);
					if (correctorwrong == "true") {
						String text = lq.questionid + "," + participantid + ",correct\n"; // Line for answer history.
						fWriter.write(text); // Writes it to txt file.
						level++;
						if (level > 5) {
							System.out.println("\nCongratulations! You win the game.");
							System.out.println("You won $1.000.000\n");
							break;
						}
					} else if (correctorwrong == "Not found your word") {
						break;
					} else {
						String text = lq.questionid + "," + participantid + ",wrong\n"; // Line for answer history.
						fWriter.write(text); // Writes it to txt file.
						if (level <= 2)
							System.out.println("\nUnfortunately, you couldn't win any money.\n");
						else if (level > 2 && level < 4)
							System.out.println("\nCongratulations! You won $100.000\n");
						else if (level > 4)
							System.out.println("\nCongratulations! You won $500.000\n");
						break;
					}
				}
				fWriter.flush();
				fWriter.close(); // Close the file.

			case 4: // Statics
				if (QuestionFile.equals("") || ParticipantsFile.equals("")) {
					System.out.println("You are not enter questions file or participants file!");
					break;
				}
				lq = new LoadQuestion(capacity); // Produces an object from LoadQuestion class.
				lq.LoadQuestions2(QuestionFile);
				Question[] newarray = lq.getQuestionsarray();
				Statistic statisticFile = new Statistic("answer_history.txt", participants, newarray);
				statisticFile.variables();
				break;

			case 5: // End Game

				System.out.println("Game is over. Thank you for playing.");
				flag = false;
				break;
			default:
				System.out.println("Please enter corect choices.");

			}
		}
		sc.close();
		sc2.close();

	}
}