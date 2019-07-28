//hangman game with AI player
import java.util.*;

public class HangmanAI {
    public static void main(String[] args) {
        HangmanPlayer player = new HangmanPlayer();
        HMMethods hm = new HMMethods();
        String myPhrase = hm.randomPhrase();
        System.out.println(myPhrase);
        StringBuilder hiddenPhrase = hm.generateHiddenPhrase(myPhrase);
        System.out.println(hiddenPhrase);

        int chances = 8;
        boolean g = false;

        //ask whether user is ready for AI Player to play

        Scanner sc = new Scanner(System.in);
        boolean again = true;

        while (again) {
            int c = 0;
            int hit = hiddenPhrase.length();
            char guess = player.nextGuess();


            //Scanner scanner = new Scanner(System.in);
            System.out.print("Please guess a letter: ");
            //String guess = scanner.nextLine();
            System.out.println(guess);
            if (((guess > 'z') || (guess < 'a')) &&
                    ((guess > 'Z') || (guess < 'A')) &&
                    ((guess < '0') || (guess > '9'))) {
                System.out.println("Your guess is invalid! Please enter your guess again: ");
                continue;
            }
            g = hm.processGuess(myPhrase, hiddenPhrase, guess); //process guess
            System.out.println(g);
            if (!g){ // if(!process guess) guesses wrong
                //System.out.println(chances);
                if (chances == 1) { //one remaining chance
                    System.out.println("GAME OVER :(");
                    System.out.println("Do you want to continue playing again? (y/n)");
                    char input = sc.next().charAt(0);
                    if (input == 'y'){
                        again = true;
                        myPhrase = hm.randomPhrase();
                        hiddenPhrase = hm.generateHiddenPhrase(myPhrase);
                        hit = hiddenPhrase.length();
                        System.out.println(myPhrase);
                        System.out.println(hiddenPhrase);
                        chances = 8;

                    } else {
                        again = false;
                    }

                } else {
                    chances = chances - 1;
                    System.out.println("Your guess does not occur in the phrase & have " + chances + " number of chances left.");
                    continue;
                }

            }

            while (c < hiddenPhrase.length()) {
                if (hiddenPhrase.charAt(c) != '*') { //if a character in the hidden phrase is still an asterisk:
                    hit = hit - 1; //continue game (asterisks still need to be replaced)
                }
                c++;
            }
            System.out.println(hit + " " + "asterisk(s) still need to be guessed");

            if (hit == 0) { //if no remaining asterisks that have to be guessed exist, user wins
                System.out.println("YOU WON!! :-)");

                System.out.println("Are you ready for AI Player to play again? (y/n)");
                char input = sc.next().charAt(0);
                if (input == 'y'){
                    again = true;
                    myPhrase = hm.randomPhrase();
                    hiddenPhrase = hm.generateHiddenPhrase(myPhrase);
                    hit = hiddenPhrase.length();
                    System.out.println(myPhrase);
                    System.out.println(hiddenPhrase);
                    chances = 8;
                } else {
                    again = false;
                }


            }

        }



    }


    public String randomPhrase(){ //returns a single phrase randomly chosen from a list
        Random random = new Random();
        int x = random.nextInt(10); //any random phrase out of 10 phrases from the phraseList
        String phraseList[] = {"lemon tea", "USF rocks!", "ENJOY every sunset", "plane spotting", "I love music.", "Golden State", "CS112 is fun!", "san fran", "coffee enthusiast", "Tiff Tse"};
        //System.out.println(phraseList[x]);
        String anyPhrase = phraseList[x]; //assign the randomPhrase to a variable named anyPhrase (randomly chosen phrase)

        return anyPhrase;

    }

    public StringBuilder generateHiddenPhrase(String anyPhrase){ //returns the initial hidden phrase
        StringBuilder hiddenPhrase = new StringBuilder(anyPhrase); //turn the anyPhrase into a mutable string (hidden phrase)

        int i = 0;
        //char a = '*';
        while (i < anyPhrase.length()) { //loop through the anyPhrase
            if (anyPhrase.charAt(i) != ' ' && // //if the character in the anyPhrase is not a space or punctuation:
                    anyPhrase.charAt(i) != '!' &&
                    anyPhrase.charAt(i) != '.' &&
                    anyPhrase.charAt(i) != '?' &&
                    anyPhrase.charAt(i) != ',') {
                hiddenPhrase.setCharAt(i, '*'); //replace all letters with asterisks
            } else {
                hiddenPhrase.setCharAt(i, anyPhrase.charAt(i)); //else keep the existing character
            }
            i++; //move on to next character

        }
        StringBuilder hidden = new StringBuilder(hiddenPhrase);
        return hidden;

    }


    public boolean processGuess(String anyPhrase, StringBuilder hiddenPhrase, char guess){ //returns whether a letter matches & modifies the partially hidden phrase

        int c = 0; //counter variable
        int hit = 0; //variable to check if there are any asterisks that still need to be guessed
        while (c < hiddenPhrase.length()) { //loop through hidden phrase
            //System.out.println(c);
            //System.out.println(guess.charAt(0));
            //System.out.println(sb.charAt(c));
            if (guess == anyPhrase.charAt(c)) { //if letter that the user guessed exists in the random phrase:
                //System.out.println("enter");
                hit = 1;
                hiddenPhrase.setCharAt(c, guess); //replace the asterisks with the user-guessed letter at the applicable position
            }
            c++;

        }
        System.out.println(hiddenPhrase);
        //return hiddenPhrase;
        if(hit > 0){
            return true;
        }
        else{
            return false;
        }
    }


}
