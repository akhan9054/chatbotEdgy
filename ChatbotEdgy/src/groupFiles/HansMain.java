package groupFiles;

import java.util.Scanner;

public class HansMain { // I LIKE what I see
	static int loveLevel = 3;
	static int frustrationLevel = 0;
	static Scanner input;
	static String user;
	static boolean inLoop;
	static String[] basicSayings = {"Say something","Hi","Talk to me","Say something interesting"};
	static String[] basicResponses = {"Yep..","mhm","*awkwardly whistles*","good looks"};
	static String response;
	static MichaelDate date;
	static HansQuestions questions;
	static ShohebSpecial special;
	//static int lineCount;

	public static void main(String[] args) {
		createTopics();
		promptName();
		talkForever();	
	}
	
	private static void promptName() {
		print("Hey man. I am a board covered with semiconductors and other electronic components ready to be your love interest. What is your name?");
		user = input.nextLine();
		print("Cool beans. I will call you "+user+" until you terminate me.");
	}

	public static void talkForever(){
		inLoop = true;
		while(inLoop){
			print(basicSayings[(int)(Math.random()*basicSayings.length)]+" "+user+"!");//
			response = getInput();
			if(date.isTriggered(response)){
				inLoop = false;
				date.talk();
			}
			else if(questions.isTriggered(response)){
				inLoop = false;
				questions.talk();
			}
			else if(special.isTriggered(response)){
				inLoop = false;
				special.talk();
			}
			else{
				print(basicResponses[(int)(Math.random()*basicResponses.length)]);
			}
		}
	}

	public static int findKeyword(String searchString, String key, int startIndex) {
		String phrase = searchString.trim();
		phrase = phrase.toLowerCase();
		key = key.toLowerCase();
		int psn = phrase.indexOf(key);
		while(psn >= 0){
			String before = " ";
			String after = " ";
			if(psn + key.length() < phrase.length()){
				after = phrase.substring(psn+key.length(),psn+key.length() +1).toLowerCase();
			}
			if(psn>0){
				before = phrase.substring(psn-1,psn).toLowerCase();
			}
			if(before.compareTo("a")<0 && after.compareTo("a") < 0){
				if(noNegations(phrase,psn)){
					return -1;
				}
				return psn;
			}
			psn = phrase.indexOf(key,psn+1);
		}
		
		return -1;
	}

	private static boolean noNegations(String phrase, int index) {
		if(index-3>= 0 && phrase.substring(index-3,index).equals("no "))
			return true;
		else if(index-4>= 0 && phrase.substring(index-4,index).equals("not "))
			return true;
		else if(index-6>= 0 && phrase.substring(index-6,index).equals("never "))
			return true;	
		else if(index-4>= 0 && phrase.substring(index-4,index).equals("n't "))
			return true;
		else return false;
	}

	public static void promptInput() {
		print(user+", try inputting a string!");
		String userInput = input.nextLine();
		print("You typed: "+userInput);
		
	}

	public static void createTopics() {
		input = new Scanner(System.in);
		date = new MichaelDate();
		questions = new HansQuestions();
		special = new ShohebSpecial();
		//initialize group classes
		
	}
	public static void print(String s){
		String printString = "";
		int cutOff = 35;
		while(s.length() > 0){
			String currentLine = "";
			String nextWord = "";
			while(currentLine.length() + nextWord.length() <= cutOff && s.length() > 0){
				currentLine += nextWord;
				s = s.substring(nextWord.length());
				int endOfWord = s.indexOf(" ");
				if(endOfWord == -1)
					endOfWord = s.length()-1;
				nextWord = s.substring(0,endOfWord+1);
			}
			printString += currentLine + "\n"; 
			
		}
		System.out.println(printString);
	}
	public static String getInput(){
		return input.nextLine();
	}
}
