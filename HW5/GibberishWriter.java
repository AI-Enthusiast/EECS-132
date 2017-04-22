import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Cormac Dacker
 * @since 11/28/2016
 */
public class GibberishWriter {

    /**
     * Recalls an ArrayList of ContextData
     */
    private ArrayList<ContextData> cdArray;

    /**
     * Recalls the size of the context
     */
    private int contextSize;
    private Context<?> lastOccurance;

    /**
     * Creates an instance of GibberishWriter
     *
     * @param size the size of the context
     */
    public GibberishWriter(int size) {
        this.contextSize = size;
    }

    /**
     * A method to add a context data to a list of context data and return the added context data
     *
     * @param cont The context that will be added to Context Data
     * @param list The ContextData List we are searching through for the desired context
     * @return The ContextData added once it has been created
     */
    public static ContextData addContextData(Context cont, LinkedList<ContextData> list) {
        if (list.isEmpty()) {
            /* An instance of context data that stored the desired context */
            ContextData newContextData = new ContextData(cont);
            list.addToFront(newContextData);
        }
        /* A linked list iterator containing context data that stored the context data iterator*/
        LLIterator<ContextData> temp = list.iterator();
        /* A loop that runs when the LLIterator is not empty and adds the appropriate context data to the list */
        while (temp.hasNext()) {
            /* Stores the next node of the context data LLIterator */
            ContextData contextData = temp.next();
            /* If the desired context is in the next node, return that context data element */
            if (contextData.getContext().equals(cont)) {
                return contextData;
            }
        }
        /*
         * If the desired context is not already in the context data list,
         * this creates a new instance of context data with the data with the desired context
         */
        ContextData contextData = new ContextData(cont);
        list.addToEnd(contextData);
        return contextData;
    }

    /**
     * Getter for the variable cdArray
     *
     * @return cdArray representing an array of ContextData[s]
     */
    public ArrayList<ContextData> getCDArray() {
        return cdArray;
    }

    /**
     * Adds a text file that will be used to construct the gibberish output
     *
     * @param file the name of the file that contains the text data
     */
    public void addDataFile(String file) {
        /* LinkedList which will store ContextData from the cdArray field */
        LinkedList<ContextData> list = new LinkedList<ContextData>();
        if (getCDArray() != null)
            /* Adds the data from the GibberishWriter to the above list */
            for (int index = 0; index < getCDArray().size(); index++)
                list.addToEnd(getContextData(index));
        /* File that is read */
        File chosenFile = new File(file);
        try {
            /* Array of size contextSize */
            String[] stringArray = new String[getContextSize()];
            /* Scanner to read from the file */
            Scanner scanner = new Scanner(chosenFile);
            /* File loop to add words from the file to the String array */
            for (int index = 0; index < stringArray.length; index++)
                stringArray[index] = scanner.next();
            /* Context which is currently being used */
            Context currentContext = new Context(stringArray);
            /* New scanner to start from the beginning of the file again */
            Scanner scannerTwo = new Scanner(chosenFile);
            /* The goal of the loop is to go through the file from the beginning */
            while (scannerTwo.hasNext()) {
                /* Word returned by next from the scanner */
                String word = scannerTwo.next();
                /* ContextData returned by the addContextData method */
                ContextData data = addContextData(currentContext, list);
                data.addFollowingWord(word);
                /* The goal of the loop is to shift the array down one */
                for (int index = 0; index < stringArray.length - 1; index++)
                    stringArray[index] = stringArray[index + 1];
                stringArray[stringArray.length - 1] = word;
                /* Keep the currentContext current */
                currentContext = new Context(stringArray);
            }
            this.cdArray = list.toArrayList();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR: Invalid File Name");
        }
    }

    /**
     * Getter for the variable contextSize
     *
     * @return contextSize that represents the size of the context
     */
    public int getContextSize() {
        return contextSize;
    }

    /**
     * Gets the ContextData at the given location
     *
     * @param index the location of the ContextData in the array
     * @return ContextData found at the inputted location
     */
    public ContextData getContextData(int index) {
        return getCDArray().get(index);
    }

    /**
     * Getter for the field lastOccurance
     *
     * @return lastOccurance which represents the last occurace of the context
     */
    public Context<?> getLastOccurance() {
        return lastOccurance;
    }

    /**
     * Creates the new order of the gibberish output
     *
     * @return String that represents a singular word that is in context based off of the next one
     */
    public String next() {
        ArrayList<Context> arrayList = new ArrayList<>();
        /* The goal of the loop is to go down the arrayList ad add context at each index */
        for (int index = 0; index < arrayList.size(); index++) {
            arrayList.add(this.getContextData(index).getContext());
        }
        /* If this is the first time this has been called */
        if (getLastOccurance() == null) {
            this.lastOccurance = getContextData((int) Math.random() * ((getCDArray().size() - 1) + 1)).getContext();
        }
        getCDArray();
        String returnMe = "";
     //   int temp = Collections.binarySearch(arrayList, getLastOccurance());
        /* If the field temp is less than 0 */
      //  if (temp < 0) {
            this.lastOccurance = getContextData((int) Math.random() * ((arrayList.size() - 1) + 1)).getContext();
       //     temp = Collections.binarySearch(arrayList, getLastOccurance());
       // }
        String[] word = new String[getCDArray().size()];
        /* The goal of the loop it to add the appropiate word at the given locaton */
        for (int index = 0; index < word.length; index++) {
            word[index] = getLastOccurance().getWord(index + 1);
        }
        word[word.length - 1] = returnMe;
        this.lastOccurance = new Context(word);
       // ContextData almost = getContextData(temp);
      //  returnMe = almost.getFollowingWord(((int) (Math.random() * ((almost.numOccurences() - 1) + 1))) + 1);
      //  almost.addFollowingWord(returnMe);
        return returnMe;
    }

    /**
     * The main method of the Gibberish class
     *
     * @param args inputs that the name of the file, the size of the context, the number of words of the output
     */
    public static void main(String[] args) {
        /* If the there is the correct number of arguments */
        if (args.length == 3) {
            try {
            /* The first argument will be the file name */
                String file = args[0];
            /* The second argument will be a contextSize */
                int contextSize = parseInt(args[1]);
                GibberishWriter newGW = new GibberishWriter(contextSize);
                newGW.addDataFile(file);
            /* The last Argument will be the number of words */
                int numOfOutWords = parseInt(args[2]);
                StringBuilder builder = new StringBuilder();
                for (int index = 0; index < numOfOutWords; index++) {
                    builder.append(newGW.next());
                /* Forces a new line every 10 words */
                    if (index % 10 == 0) {
                        builder.append("\n");
                    }
                }
                System.out.println(builder.toString());
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(new JFrame(), "ERROR: File Name Not Found");
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(new JFrame(), "ERROR: Please Input An Integer");
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR: Input The Correct Number Of Arguments");
        }
    }

    /**
     * Class that represents the word & the number of times it is used
     */
    public static class WordData {

        /*Recalls the word being counted*/
        private String word;

        /*Recalls the number of times word has been counted*/
        private int count = 1;

        /**
         * Creates the data representing the word inputted
         *
         * @param word String that represents the word being counted
         */
        public WordData(String word) {
            this.word = word;
        }

        /**
         * Adds one to the count
         */
        public void incrementCount() {
            count++;
        }

        /**
         * Retrieves the word being counted
         *
         * @return variable word
         */
        public String getWord() {
            return word;
        }

        /**
         * Retrieves the count of the word
         *
         * @return int representing how many times the word was counted
         */
        public int getCount() {
            return count;
        }
    }

    /**
     * Gives the Context of the words within a String array
     *
     * @param <T> generic to ensure that all are of the same type
     */
    public static class Context<T> implements Comparable<T> {

        /**
         * Recalls the sequence of the words in the array
         */
        private String[] sequence;

        /**
         * Creates an instance of Context
         *
         * @param stringArray an array of strings that are words
         */
        public Context(String[] stringArray) {
            /* The goal of the loop is to manually copy the array */
            for (int index = 0; index < stringArray.length; index++) {
                this.sequence[index] = stringArray[index];
            }
        }

        /**
         * The number of words in the array
         *
         * @return int representing the number of words
         */
        public int numberOfWords() {
            return sequence.length;
        }

        /**
         * Creates a string that represents the inputted array
         *
         * @return String that is the array
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            /* The goal of the loop is to create a String each word in the array */
            for (int index = 0; index < numberOfWords(); index++) {
                builder.append(sequence[index] + " ");
            }
            return builder.toString();
        }

        /**
         * Getter for a word located in the array
         *
         * @param location the location of the word in the array
         * @return String that represents the word at the inputted location
         */
        public String getWord(int location) {
            return sequence[location]; //possibly create a catch NullPointerException if input is too big
        }

        /**
         * Checks to see is the input is the same String array
         *
         * @param possibleClone the potential duplicate of the current array
         * @return boolean that represents weather they are equal or not
         */
        @Override
        public boolean equals(Object possibleClone) {
            String[] compareMe = (String[]) possibleClone;
            /* If the the inputted object is of class Context and is of the same length */
            if (possibleClone instanceof Context && compareMe.length == numberOfWords()) {
                /*
                 * Loop with the goal of going through each word in the arrays and
                 * checks whether those strings are equal
                 */
                for (int index = 0; index < numberOfWords(); index++) {
                    /* If the two strings at the index are not equal */
                    if (!sameString(compareMe[index], sequence[index])) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }

        /**
         * Alphabetizes the array
         */
        public void alphabetize() {
            Arrays.sort(sequence);
        }

        /**
         * Compares two instances of a String array
         *
         * @param comparison input that is potentially a String array
         * @return int representing whether the words are the same
         */
        public int compareTo(T comparison) {
            /* If the input is an instance of Context */
            if (comparison instanceof Context) {
                String[] moreWords = (String[]) comparison;
                /* Goal of the loop is to compare the elements at every index in the String arrays */
                for (int index = 0; index < moreWords.length; index++) {
                    /* If the words at the location in the string are the same then return 1 */
                    if (sequence[index].compareTo(moreWords[index]) > 0) {
                        return 1;
                    }
                    /* If the words at the location int the String are not the same return -1 */
                    else if (sequence[index].compareTo(moreWords[index]) < 0) {
                        return -1;
                    }
                }
                return 0;
            }
            return 0;
        }

        /**
         * Checks to see if the inputted strings are the match (including prefix)
         *
         * @param lineOne String that is potentially equal to lineTwo
         * @param lineTwo String that is potentially equal to lineOne
         * @return boolean that represents weather the two string are equal
         */
        public boolean sameString(String lineOne, String lineTwo) {
            /* if the length of the Strings do not match, return false */
            if (lineOne.length() != lineTwo.length()) {
                return false;
            }
            /* The goal of the loop is to determine whether the two strings match at every char
             * for this it needs both the strings, and what char number it is at in the loop (checker)
             */
            for (int checker = 0; checker < lineOne.length(); checker++) {
                /* if the char it is on matches, it moves on to the next char if not then it returns false */
                if (lineOne.charAt(checker) != lineTwo.charAt(checker)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Class to store the data of context
     *
     * @param <T> Generic to ensure they are all of the same type
     */
    public static class ContextData<T> implements Comparable<T> {

        /**
         * Recalls the instance of context
         */
        private Context<T> context;

        /**
         * Recalls the number of times the word was used
         */
        private int timesUsed = 0;

        /**
         * A linked list of WordData[s]
         */
        private LinkedList<WordData> llWordData = null;

        /**
         * Creates an instace of Context Data
         *
         * @param initialContext Initial inputted context from context
         */
        public ContextData(Context<T> initialContext) {
            this.context = initialContext;
        }

        /**
         * Getter for the context
         *
         * @return context inputted in context
         */
        public Context<T> getContext() {
            return context;
        }

        /**
         * Getter for the llWordData
         *
         * @return llWordData which is a linked list of WordData[s]
         */
        public LinkedList<WordData> getLlWordData() {
            return llWordData;
        }

        /**
         * Getter for the number of times a word was used
         *
         * @return timesUsed representing how often the word occurs
         */
        public int numOccurences() {
            return timesUsed;
        }

        /**
         * Compares input to this
         *
         * @param o generic that is potentially an instance of ContextData
         * @return Int representing whether or not it is equal
         */
        @Override
        public int compareTo(@NotNull T o) {
            /* If o is an instance of ContextData */
            if (o instanceof ContextData) {
                Context newContext = (Context) o;
                /* The goal of the loop is to compare the strings of the contexts */
                for (int index = 0; index < newContext.numberOfWords(); index++) {
                    /* If the current contexts string is greater than that of the inputted one */
                    if (getContext().toString().compareTo(newContext.toString()) > 0) {
                        return 1;
                    /* If the current contexts string is lesser than that of the inputted one */
                    } else if (getContext().toString().compareTo(newContext.toString()) < 0) {
                        return -1;
                    }
                }
                return 0;
            }
            return 0;
        }

        /**
         * Adds the inputted word
         *
         * @param word that will be added to the LList
         */
        public void addFollowingWord(String word) {
            WordData newWord = new WordData(word);
            LLIterator.ClassyLLIterator<WordData> head = new LLIterator.ClassyLLIterator<WordData>(llWordData.getFront());
            /* The goal of the loop is to add the word where appropriate */
            for (int count = 0; count > llWordData.length(); count++) {
                /* If the front of the LinkedList is the same word */
                if (head.equals(word)) {
                    newWord.incrementCount();
                } else {
                    WordData wordData = new WordData(word);
                    head.addBefore(wordData);
                }
            }
        }

        /**
         * Gets the the following word at the location inputted
         *
         * @param location location that you wish to get the word that follows from
         * @return String that represents the following word from the inputted location
         */
        public String getFollowingWord(int location) {
            int length = 0;
            /* The goal of the loop is to calculate the length of the list */
            for (WordData data : getLlWordData()) {
                length += data.getCount();
            }
            /* If the location given cannot exist then */
            if (location < 0 || length < location) {
                throw new IndexOutOfBoundsException();
            } else {
                LLIterator<WordData> iterator = (LLIterator<WordData>) getLlWordData().iterator();
                WordData saved = null;
                int count = 0;
                /* The goal of the loop is to increment count */
                while ((iterator.hasNext() && count < location)) {
                    saved = iterator.next();
                    count += saved.getCount();
                }
                return saved.getWord();
            }
        }
    }
}
