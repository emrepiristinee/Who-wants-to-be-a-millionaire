package test;

public class MoneyPrize {
	private int moneyPrize;

	public MoneyPrize(int inputMoneyPrize) {
		moneyPrize = inputMoneyPrize;

	}

	public int getMoneyPrize() {
		return moneyPrize;
	}

	public void setMoneyPrize(int level) {
		if (level == 2)
			moneyPrize = 20000;
		else if (level == 3)
			moneyPrize = 100000;
		else if (level == 4)
			moneyPrize = 250000;
		else if (level == 5)
			moneyPrize = 500000;
		else
			moneyPrize = 1000000;
	}
}
