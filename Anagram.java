import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "java";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		String str1new = "";
		String str2new = "";
		for (char c : str1.toCharArray()) {
			if (Character.isLetter(c)) {str1new += c;}
		}
		for (char c : str2.toCharArray()) {
			if (Character.isLetter(c)) {str2new += c;}
		}
		if (str1new.length() != str2new.length()) {return false;}

		for (char c : str1new.toCharArray()) {
			if (str2new.indexOf(c) != -1) { 
				if (str2new.indexOf(c) == 0) {str2new = str2new.substring(1);}
				else {str2new = str2new.substring(0, str2new.indexOf(c)) + str2new.substring(str2new.indexOf(c)+1);}
			}
			else break;
		}
		if (str2new.equals("")) { return true;}
		else {return false;}
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		str = str.toLowerCase();
		String newString = "";
		for (char c : str.toCharArray()) {
			if (Character.isLetter(c) || (c == ' ')) {newString += c;}
		}
		return newString;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		str = preProcess(str);
		String randomString = "";
		Random r = new Random();
		int randomIndex = 0;
		while (str.length() > 0) {
			randomIndex = r.nextInt(str.length());
			randomString += str.charAt(randomIndex);
			str = str.substring(0, randomIndex) + str.substring(randomIndex+1);
		}
		return randomString;
	}
}
