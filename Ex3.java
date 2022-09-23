
import java.util.Arrays;

public class Ex3 {
	//Rules marked by {}
	//Function that summaries a sentence into a list of words avoiding {space , .}
	public static String[] sentence(String s) {
		
	    int wordCounter = 0;
	    boolean word = false;
	    //Inner-function that calculates the size of word's array.
	    for (int i = 0; i < s.length(); i++) {
	        if (Character.isLetterOrDigit(s.charAt(i)) && i != s.length() - 1) {
	            word = true;
	        }
	        else if (!Character.isLetterOrDigit(s.charAt(i)) && word) {
	            wordCounter++;
	            word = false;
	        } 
	        else if (Character.isLetterOrDigit(s.charAt(i)) && i == s.length() - 1) {
	            wordCounter++;
	        }
	    }
	    //Building an array based on the amount of words counted by the "Inner-Function"
		String[] wordArr = new String[wordCounter];
	    int wordAt = 0;
	    String wordStr = "";
	    
	    //Putting words inside the designated array 
	    for(int i = 0; i < s.length(); i++) {
	    	
	    	if(s.charAt(i) != ' ' && s.charAt(i) != '.' && s.charAt(i) != ',') {
	    		if(wordArr[wordAt] == null) {
		    		wordArr[wordAt] = "";
		       	}
	        	wordStr += s.charAt(i);
	    	}
	    	else {
	    		if(wordStr != "") {
	    			wordArr[wordAt] = wordStr;
	    			wordAt++;
	    			wordStr = "";
	    		}
	        }
	    }
	    //Takes the final word that isn't included inside the for loop after being verified as a word
	    if(wordStr != "") {	
		    wordArr[wordAt] = wordStr;
	    }
		return wordArr;
	}
	//Function based on an array given by SENTENCE FUNC rules, {alphabetically organized without any repeated words}
	public static String[] dictionary(String s) {
		

		String[] sortArr = sentence(s.toLowerCase());
	    String temp; 
	    int emptyCounter = 0;
	    int inside = 0;
	    //Alphabetic order for loop
	    for(int i = 0; i<sortArr.length-1; i++) {                                                      
	    	for(int j = 0; j<sortArr.length-1; j++) {                                      
	    		if(sortArr[j].compareTo(sortArr[j+1]) > 0) {                                
	    			temp = sortArr[j];                    
	                sortArr[j] = sortArr[j+1];              
	                sortArr[j+1] = temp;   
	            }                                       
	        }
	    }
	    //Replacing a word that's shown more than once by an empty string ("") and summing the amount of repeated words
	    for (int i = 0; i < sortArr.length; i++) {
	        for (int j = i+1; j < sortArr.length; j++) {
	            if (sortArr[i].equals(sortArr[j])) {
	                if (i != j) {
	                    sortArr[i] = "";
	                    emptyCounter++;
	                }
	            }
	        }
	    }
	    //Putting the words inside a new array size that's not including an empty string (""), array based only on words {a-z}
	    String[] finArr = new String[sortArr.length-emptyCounter];
	    for (int i = 0; i < sortArr.length; i++) {
	    	if(sortArr[i] != "") {
	    		finArr[inside] = sortArr[i];
	    		inside++;
	    	}
	    }
		return finArr;
	}
	//Function that determines either a string is contained inside another string or not based on DICTIONARY FUNC rules
	public static boolean sub(String s, String t) {
		
		String[] sArr = dictionary(s);
		String[] tArr = dictionary(t);
		int length = 0;
		
		for (int i = 0; i < sArr.length; i++) {
			if (tArr[length].equals(sArr[i])) {
				length++;
			} 
			else {
				length = 0;
			}
			if (length == tArr.length) {
				return true;
			}	
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(sentence(" .,Hello .. World ,.")));
		System.out.println(Arrays.toString(dictionary("The..quick brown,fox.jumps over ..  the lAZy dog")));
		System.out.println(sub(".The quick,brown.fox.dog cat", ",Fox brown.dog,cat"));
	}
}
