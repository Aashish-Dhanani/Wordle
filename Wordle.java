import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

 class WordleApp {

	  public static String[] loadFile(String filename) {
		      String[] words = null;
			   try {
				 Scanner fileScanner = new Scanner(new File(filename));
				 int wordCount = 0;
				 while (fileScanner.hasNext()) {
					 fileScanner.next();
					 wordCount++;
				 }
				 fileScanner.close();
     		     words = new String[wordCount];
				 fileScanner = new Scanner(new File(filename));
			     for (int i = 0; i < wordCount; i++) {
					 words[i] = fileScanner.next();
				}
			} catch (FileNotFoundException e) {
			  System.out.println("File not found: " + filename);
				}
			 return words;
		   }

	  public static boolean wordInDictionary(String guess, String[] dictionary) { 
	  	return Arrays.asList(dictionary).contains(guess);	
	  }

	  public static  String createResponse(String guess, String secret) {
	  	
	 		String response = "";

   		 for (int i = 0; i < guess.length(); i++) {
       		 char guessChar = guess.charAt(i);
       		 int secretIndex = secret.indexOf(guessChar);
       		 if (secretIndex == -1) {
           		 response += "-";
       		 } else if (secretIndex == i) {
           		 response += Character.toUpperCase(guessChar);
       		 } else {
           		 response += Character.toLowerCase(guessChar);
       		 }
    }

    return response; 
	  
	  
	  }

	   public static void main(String[] args) {
	   
	   		String[] file1 = loadFile("words2000.txt");
			String[] file2 = loadFile("allWords.txt");
	   		

			int length1 = file1.length;
			int length2 = file2.length;
			int combinedLength = length1 + length2;

			String[] combinedFile = new String[combinedLength];
   		 	System.arraycopy(file1, 0, combinedFile, 0, length1);
   		 	System.arraycopy(file2, 0, combinedFile, length1, length2);


	   		Random rand = new Random();
			int randomIndex = rand.nextInt(combinedLength);
			String secretWord = combinedFile[randomIndex];

			Scanner input = new Scanner(System.in);
			int ctr = 0;
			String guess = "";

			//System.out.println(secretWord); //placeholder for testing purposes

			while(guess != secretWord) {
			
				System.out.println("Enter guess: ");
				 guess = input.nextLine();
				ctr++;

				if(guess.equals(secretWord)) {
					System.out.println("Correct in " + ctr + " guesses!");
					break;
				}
				 if(guess.equals("idk")) {
					System.out.println("The secret word is: " + secretWord);
					break;
				}
				if(wordInDictionary(guess, combinedFile)) {
					System.out.println(createResponse(guess, secretWord));
				}
				else  {
					System.out.println("Invalid guess. Try Again.");
				}
			
			}

	   
	   
	   }





}

