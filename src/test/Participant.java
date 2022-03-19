package test;

public class Participant {
	private String name;
	String birthdate;
	private String address;
	private String phone;
	private String questionID;
	private String contestantID;
	private String Isansweredcorrectly;
	private String[] cityStr;
	private String city;

	public Participant(String inputName, String inputBirthdate, String inputAddress, String inputPhone) {

		name = inputName;
		birthdate = inputBirthdate;
		address = inputAddress;
		phone = inputPhone;
	}

	public Participant(String QuestionID, String ContestantID, String IsAnsweredCorrectly) {

		questionID = QuestionID;
		contestantID = ContestantID;
		Isansweredcorrectly = IsAnsweredCorrectly;
	}

	public String getName() {
		return name;
	}

	public String getBirthdate() {

		return birthdate;
	}

	public String getAddress() {
		address = address.replace(";", " ");
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public void setName(String inputName) {
		name = inputName;
	}

	public void setBirthdate(String inputBirthdate) {
		birthdate = inputBirthdate;
	}

	public void setAddress(String inputAddress) {
		address = inputAddress;
	}

	public void setPhone(String inputPhone) {
		phone = inputPhone;
	}

	public String getCityString() {
		address = address.replace(";", " ");
		cityStr = address.split(" ");
		city = cityStr[4];
		return city;
	}

	public int Age() {
		return 2022 - (Integer.parseInt(birthdate.substring(birthdate.length() - 4)));
	}

	public String City() {
		String[] addressCity = address.split(" ");
		return addressCity[addressCity.length - 2];
	}
}