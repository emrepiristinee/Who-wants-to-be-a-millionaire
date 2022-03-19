package test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import enigma.core.Enigma;

public class LifeLine {
	boolean Double_Dip;
	boolean fifty_percent;
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)

	enigma.console.Console cn = Enigma.getConsole("Keyboard");

	public LifeLine(boolean doubledip, boolean fiftyfifty) {
		Double_Dip = doubledip;
		fifty_percent = fiftyfifty;
	}

	public boolean isDoubleDip() {
		return Double_Dip;
	}

	public void setDoubleDip(boolean doubleDip) {
		Double_Dip = doubleDip;
	}

	public boolean isFifty_percent() {
		return fifty_percent;
	}

	public void setFifty_percent(boolean fifty_percent) {
		this.fifty_percent = fifty_percent;
	}

	public void FiftyPercent(Question question) {
		String choice_question = "";
		Scanner sc = new Scanner(System.in);
		String[] choices = { "A", "B", "C", "D" };
		String deletedchoice = "B";
		while (deletedchoice.equalsIgnoreCase(question.getCorrect_choice()) == true) {
			int random = (int) (Math.random() * 3 + 1);
			deletedchoice = choices[random];
		}
		String deletedchoice2 = "A";
		while (deletedchoice.equalsIgnoreCase(question.getCorrect_choice()) == true
				|| deletedchoice2.equalsIgnoreCase(deletedchoice) == true) {
			int random = (int) (Math.random() * 3 + 1);
			deletedchoice2 = choices[random]; // a-b b-a a-c c-a a-d d-a b-c c-b b-d d-b
		}
		if ((deletedchoice.equalsIgnoreCase("A") && deletedchoice2.equalsIgnoreCase("B")) || // If Choices A and B are
																								// deleted.
				(deletedchoice.equalsIgnoreCase("B") && deletedchoice2.equalsIgnoreCase("A"))) {
			System.out.println(question.getQuestion());
			System.out.println();
			System.out.println();
			System.out.println("C)" + question.getChoiceC());
			System.out.println("D)" + question.getChoiceD());

		} else if ((deletedchoice.equalsIgnoreCase("A") && deletedchoice2.equalsIgnoreCase("C")) || // If Choices A and
																									// C are deleted.
				(deletedchoice.equalsIgnoreCase("C") && deletedchoice2.equalsIgnoreCase("A"))) {
			System.out.println(question.getQuestion());
			System.out.println();
			System.out.println("B)" + question.getChoiceB());
			System.out.println();
			System.out.println("D)" + question.getChoiceD());

		} else if ((deletedchoice.equalsIgnoreCase("A") && deletedchoice2.equalsIgnoreCase("D")) || // If Choices A and
																									// D are deleted.
				(deletedchoice.equalsIgnoreCase("D") && deletedchoice2.equalsIgnoreCase("A"))) {
			System.out.println(question.getQuestion());
			System.out.println();
			System.out.println("B)" + question.getChoiceB());
			System.out.println("C)" + question.getChoiceC());
			System.out.println();

		}

		else if ((deletedchoice.equalsIgnoreCase("B") && deletedchoice2.equalsIgnoreCase("C")) || // If Choices C and B
																									// are deleted.
				(deletedchoice.equalsIgnoreCase("C") && deletedchoice2.equalsIgnoreCase("B"))) {
			System.out.println(question.getQuestion());
			System.out.println("A)" + question.getChoiceA());
			System.out.println();
			System.out.println();
			System.out.println("D)" + question.getChoiceD());

		} else if ((deletedchoice.equalsIgnoreCase("B") && deletedchoice2.equalsIgnoreCase("D")) || // If Choices D and
																									// B are deleted.
				(deletedchoice.equalsIgnoreCase("D") && deletedchoice2.equalsIgnoreCase("B"))) {
			System.out.println(question.getQuestion());
			System.out.println("A)" + question.getChoiceA());
			System.out.println();
			System.out.println("C)" + question.getChoiceC());
			System.out.println();

		}

	}

	public String DoubleDip(Question question, int timer) {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		String word = "", yourchoice = "";
		int tempCursorX = cn.getTextWindow().getCursorX();
		int tempCursorY = cn.getTextWindow().getCursorY();
		System.out.println("Please enter your first choice:");
//		String choice = sc.nextLine();
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
		int remainingtime = timer;
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
					break;
				}
				keypr = 0;
			}
			keypr = 0;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}
		if (yourchoice.equalsIgnoreCase(question.getCorrect_choice())) {
			return yourchoice;
		} else {
			System.out.println("Wrong Answer,Please make your second choice!");
			yourchoice = "";
			return yourchoice;
		}

	}

}