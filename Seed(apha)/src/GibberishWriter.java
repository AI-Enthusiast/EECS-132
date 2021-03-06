import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;


/**
 * writes gibberish in the style of the input text
 *
 * @author Shanti Polara
 */
public class GibberishWriter implements Iterator {
    //holds an ArrayList of the contextData of the GibberishWriter
    private ArrayList<GibberishWriter.ContextData> contextDataHolder = null;
    //holds the size of the context's the GibberishWriter will parse
    private int contextSize;
    //holds the last context Data of the GibberishWriter
    private ContextData lastContextHolder = null;
    //holds the number of words in the output
    private ContextData currentContext;


    /**
     * initilizes the GibberishWriter and initilizes it's context size
     *
     * @param contextSize the size of the context
     */
    public GibberishWriter(int contextSize) {
        this.contextSize = contextSize;

    }

    /**
     * returns the context size
     *
     * @return the context size
     */
    public int getContextSize() {
        return contextSize;
    }

    /**
     * returns the context data at a given input
     *
     * @param index int the index at which the contexData is returned from
     * @return the contextData at that is at the given index of the ArrayList of context data
     */
    public GibberishWriter.ContextData getContextData(int index) {
        return getALContextData().get(index);
    }

    /**
     * returns the ArrayList of ContextData
     *
     * @return the ArrayList of ContextData
     */
    public ArrayList<ContextData> getALContextData() {
        return contextDataHolder;
    }

    public void setContextDataHolder(ArrayList<ContextData> contextDataHolder) {
        this.contextDataHolder = contextDataHolder;
    }

    /**
     * sets the ArrayList of ContextData
     *
     * @param newArray the new ArrayList of ContextData
     */
    public void setALContextData(ArrayList<ContextData> newArray) {
        contextDataHolder = newArray;
    }

    /**
     * retrives the last context of the ArrayList
     *
     * @return the last context of the Arraylist
     */
    public ContextData getLastContextHolder() {
        return lastContextHolder;
    }

    /**
     * sets the last context of the ArrayList
     *
     * @param lastContextHolder the context which is the last context of the ArrayList
     */
    public void setLastContextHolder(ContextData lastContextHolder) {
        this.lastContextHolder = lastContextHolder;
    }

    /**
     * reads a data file and parses it into context's and thier following words to create an ArrayList of ContextData
     *
     * @param fileName the file that is being read
     * @throws FileNotFoundException when the input is not the name of a file that is able to be found
     */
    public void addDataFile(String fileName) throws FileNotFoundException {
        LinkedList<ContextData> list = new LinkedList<ContextData>();
        //creates a new scanner for the imported file
        Scanner scan = new Scanner(new File(fileName));
        //creates a string array the size of the context size of the GibberishWriter
        String[] contextHolder = new String[getContextSize()];
        Context currentContext;
        ContextData currentContextData;
        String followingWord;
        //intilizes the string array with the first contextSize words in the file
        for (int i = 0; i < contextSize && scan.hasNext(); i++)
            contextHolder[i] = scan.next().toLowerCase();
        //creates a new context with the initilized array of strings
        currentContext = new Context(contextHolder);
        followingWord = scan.next();
        //creates a new context data with the current context
        currentContextData = addContextData(currentContext, list);
        currentContextData.addFollowingWord(followingWord);
        //reads through the file until it gets to the end
        while (scan.hasNext()) {
            //shifts the context one to the right
            contextHolder = shift(contextHolder, followingWord);
            followingWord = scan.next().toLowerCase();
            if (scan.hasNext()) {
                currentContext = new Context(contextHolder);
                currentContextData = addContextData(currentContext, list);
                currentContextData.addFollowingWord(followingWord);
            }
        }
        //creates an ArrayList from the LinkedList of ContextData, and sets it as the GibberishWirter's ArrayList feild
        setALContextData(list.toArrayList());
    }



    /**
     * shifts the contexts of a string array holding the current context one to the right
     *
     * @param stringArray   the array of string being shifted
     * @param followingWord the word that is added to the end of the new context
     * @return the string array which is the new context
     */
    private String[] shift(String[] stringArray, String followingWord) {
        int length = getContextSize();
        String[] newStringArray = new String[length];
        //iterates through the array of string but stops one before the last space
        for (int i = 0; i < length - 1; i++)
            //shifts the elements of the old string array one to the right
            newStringArray[i] = stringArray[i + 1];
        //adds the next word to the end of the array
        newStringArray[length - 1] = followingWord;
        return newStringArray;
    }

    /**
     * checks if GibberishWriter has an ArrayList
     *
     * @return true if the ArrayList of context data is not null
     */
    public boolean hasNext() {
        return getALContextData() != null;
    }

    /**
     * increments to the next contextData by shifting the last context down one
     *
     * @return the following word of the current context
     */
    public String next() {
        //holds the new last context that will be created by shifting the old context over one
        Context lastContext;
        int index;
        //creates a new random generator
        Random generator = new Random();
        //checks that a last context exists
        if (getLastContextHolder() != null) {
            //looks up the context from the array list of ContextData and returns the index location
            index = Collections.binarySearch(getALContextData(), getLastContextHolder());
        } else {
            //generates a random index to choose a context from
            index = generator.nextInt(findLength());
        }
        //saves the last context data at the given index to the context data holder
        if (index < 0) {
            index = generator.nextInt(findLength());
        }
        setLastContextHolder(getContextData(index));
        //finds a random index to retrive a following word from
        int save = generator.nextInt(getLastContextHolder().numOccurrences());
        if (save == 0)
            save = 1;
        //retrives a following word from the given index
        String followingWord = getLastContextHolder().getFollowingWord(save);
        //shifts the last context over one by adding the following word to the end, and saves it to the new context
        lastContext = new Context(shift(getLastContextHolder().getContext().getContext(), followingWord));
        //sets the last context holder to hold the newly created context
        setLastContextHolder(new ContextData(lastContext));
        return followingWord;
    }

    /**
     * finds the length of the ArrayList
     *
     * @return the length of the ArrayList
     */
    private int findLength() {
        //creates a new iterator for the ArrayList of context data
        Iterator<ContextData> it = getALContextData().iterator();
        int count = 0;
        //iterates through the entire ArrayList (while hasNext is true)
        while (it.hasNext()) {
            count++;
            it.next();
        }
        return count;
    }


    /**
     * A class that keeps track of how many times a word apperars after a given context
     *
     * @author Shanti Polara
     */
    public static class WordData {
        //keeps track of the word being searched for
        private String word;
        //keeps track of the number of times the word occurs after a given context
        private int count;

        /**
         * initilizes the word being looked for, and initilizes the count to 1
         *
         * @param word the word being looked for
         */
        public WordData(String word) {
            this.word = word;
            count = 1;
        }

        /**
         * increments the word count by 1
         */
        public void incrementCount() {
            count++;
        }

        /**
         * returns the word being looked for
         *
         * @return the word being looked for
         */
        public String getWord() {
            return word;
        }

        /**
         * returns the word count
         *
         * @return the word count
         */
        public int getCount() {
            return count;
        }

        /**
         * determines if the input equals the WordData calling the method
         *
         * @return true if the two objects are equal
         */
        @Override
        public boolean equals(Object input) {
            boolean indicator = false;
            //checks if the input is an instance of WordData
            if (input instanceof GibberishWriter.WordData) {
                GibberishWriter.WordData compare = (GibberishWriter.WordData) input;
                //sets indicator to true if the objects contain the same strings
                if (getWord().equals(compare.getWord())) {
                    indicator = true;
                }
            }
            return indicator;
        }

    }


    /**
     * holds a sequence of one or more words
     *
     * @author Shanti Polara
     */
    public static class Context implements Comparable<GibberishWriter.Context> {

        //holds an array of a sequence of one or more words
        private String[] context;

        /**
         * initilizes the object's context
         *
         * @param newContext holds the object's context
         */
        public Context(String[] newContext) {
            context = new String[newContext.length];
            int i = 0;
            //iterates through the elements of the newContext string array and uses them to initlize the context array
            for (String word : newContext) {
                context[i] = newContext[i];
                i++;
            }
        }

        /**
         * returns the number of words in the context
         *
         * @return the number of words in the context
         */
        public int length() {
            return context.length;
        }

        /**
         * returns a string representation of the context
         *
         * @return string representaion of the context
         */
        public String toString() {
            StringBuilder builder = new StringBuilder();
            //iterates through the words stored in the context array and appends them to the string builder
            for (String word : context) {
                builder.append(word);
                //appends a space to the string builder if it is not the last word in the sequence
                if (!context[context.length - 1].equals(word))
                    builder.append(" ");
            }
            return builder.toString();
        }

        /**
         * returns the word at the given index in the context array
         *
         * @param index the index at which the word is retrived
         * @return String which is the word at the given index
         */
        public String getWord(int index) {
            return context[index];
        }

        /**
         * returns the array of strings that makes up the context
         *
         * @return the array of strings that makes up the context
         */
        public String[] getContext() {
            return context;
        }


        /**
         * returns true if two Context obejects are equal
         *
         * @param input the object being compared to the Context object calling the method
         * @return true if the Context object's string arrays compare the same strings
         */
        public boolean equals(Object input) {
            boolean indicator = true;
            //checks if the input is an instance of class Context
            if (input instanceof GibberishWriter.Context) {
                //typecasts the input to Context
                GibberishWriter.Context compare = (GibberishWriter.Context) input;
                //checks if the input and the calling object have arrays of the same length
                if (compare.length() == length()) {
                    //iterates through the Context's arrays and checks if each string in thier arrays are equal
                    for (int i = 0; i < length(); i++) {
                        //checks if the string i for each Context object are equal, if not, sets indicator to false
                        if (!this.getWord(i).equals(compare.getWord(i)))
                            indicator = false;
                    }
                }
                //sets indicator to false if the array lengths of the two Context objects are not equal
                else {
                    indicator = false;
                }
            }
            //sets indicator to false if the input is not an instance of Context
            else {
                indicator = false;
            }
            return indicator;
        }


        /**
         * compares two context objects alphabeticly
         *
         * @param compare the Context object being compared to the calling object
         * @return -1 if calling object is less than input,  1 if calling object is bigger than input, and 0 if they are equal
         */
        @Override
        public int compareTo(GibberishWriter.Context compare) {
            int indicator;
            //sets indicator to 0 if the Context objects are equal
            if (this.equals(compare))
                indicator = 0;
            else {
                int length = 0;
        /*if the input Context objects's length is less than the length of the object calling the method,
         *it's length is set as the length*/
                if (this.length() > compare.length())
                    length = compare.length();
        /*if the input Context objects's length is greater than the length of the object calling the method,
         *the object calling the method's length is set as the length*/
                else
                    length = length();
                int i = 0;
        /* iterates through the index while the strings at that index are equal
         * or it gets to the end of the smallest array*/
                while (i < length - 1 && getWord(i).compareToIgnoreCase(compare.getWord(i)) == 0)
                    i++;
                //if this's string at the index is less than the object being compared's string, sets indicator to -1
                if (getWord(i).compareToIgnoreCase(compare.getWord(i)) < 0)
                    indicator = -1;
                    //if this's string at the index is greater than the object being compared's string, sets indicator to 1
                else if (getWord(i).compareToIgnoreCase(compare.getWord(i)) > 0)
                    indicator = 1;
                    //if the strings at the index are equal
                else {
                    //if the object calling the method had the smaller length, sets indicator to -1
                    if (length == this.length())
                        indicator = -1;
                        //if the input object had the smaller length sets indicator to 1
                    else
                        indicator = 1;
                }
            }
            return indicator;
        }


    }

    /**
     * stores information on Contexts which appear in a text
     *
     * @author Shanti Polara
     */
    public static class ContextData implements Comparable<GibberishWriter.ContextData> {

        //stores the context
        private GibberishWriter.Context context;
        //stores the number of times a context appears in the text (not at the end)
        private int occurrences;
        //stores information on the words that immediatly follow the contex
        private LinkedList<GibberishWriter.WordData> wordDataHolder;

        /**
         * initilizes the context, sets occurances to 0, and creates an empty LinkedList of WordData
         *
         * @param context the context which initilizes the ContextData's Context
         */
        public ContextData(GibberishWriter.Context context) {
            this.context = context;
            occurrences = 0;
            wordDataHolder = new LinkedList<GibberishWriter.WordData>();
        }

        /**
         * returns the object's context
         *
         * @return the object's context
         */
        public GibberishWriter.Context getContext() {
            return context;
        }

        /**
         * returns the number of times the context occurs
         *
         * @return the number of times the context occurs
         */
        public int numOccurrences() {
            return occurrences;
        }

        /**
         * increments the number of times the context occurs by one
         */
        public void incrementNumOccurrences() {
            occurrences++;
        }

        /**
         * retrives the ContextData's array of WordData
         *
         * @return the ContextData's array of WordData
         */
        public LinkedList<GibberishWriter.WordData> getWordDataHolder() {
            return wordDataHolder;
        }

        /**
         * orders the ContextData's based on thier contents
         *
         * @param compare the ContextData being compared to the ContextData calling the method
         * @return -1 if calling object is less than input,  1 if calling object is bigger than input, and 0 if they are equal
         */
        @Override
        public int compareTo(ContextData compare) {
            return getContext().compareTo(compare.getContext());
        }

        /**
         * adds a word into the array of WordData or increments it's count
         *
         * @param word the word being added, or having it's count incremented
         */
        public void addFollowingWord(String word) {
            //creates a iterator for the list of WordData
            LLIterator<WordData> iterator = (LLIterator<WordData>) getWordDataHolder().iterator();
            //initilizes the pointer to be the first element in the list
            WordData pointer = null;
            if (iterator.hasNext()) {
                pointer = iterator.next();
        /*iterates through the list until it reaches the end, or it has just passed the spot
         * in the list where the word should be*/
                while (iterator.hasNext() && pointer.getWord().compareTo(word) < 0) {
                    pointer = iterator.next();
                }
            }
            if (pointer != null) {
                //checks if the element on the list contains the word
                if (pointer.getWord().equals(word))
                    pointer.incrementCount();
                    //otherwise, adds the word in it's apropriate spot
                else if (pointer.getWord().compareTo(word) > 0)
                    iterator.addBefore(new WordData(word));
                else
                    iterator.addAfter(new WordData(word));
            } else
                iterator.addAfter(new WordData(word));
        }

        /**
         * returns the word at the value from the ContextData's list of WordData
         *
         * @param value the value that indicates which word should be returned
         * @return the string at the given value from the list of WordData
         */
        public String getFollowingWord(int value) throws NoSuchElementException {
      /*if the input value is less than one, or greater than the sum of the count's of the WordData
       * in the list, throws a NoSuchElementException*/
            if (value < 1 || value > findIndex())
                throw new NoSuchElementException();
            else {
                //creates a iterator for the list of WordData
                LinkedList<WordData>.LinkedListIterator iterator = (LinkedList<WordData>.LinkedListIterator) getWordDataHolder().iterator();
                int totalCount = 0;
                //sets the pointer equal to the first element in the list
                GibberishWriter.WordData pointer = iterator.next();
                totalCount += pointer.getCount();
                //iterates through the list untill the sum of the WordData's count's is greater than the input value
                while (totalCount < value) {
                    pointer = iterator.next();
                    totalCount += pointer.getCount();
                }
                return pointer.getWord();
            }
        }

        /**
         * finds the total sum of the count values of all of the WordData elements in the list of WordData
         *
         * @return the sum of the count values of the WordData list
         */
        private int findIndex() {
            //creates a iterator for the list of WordData
            LinkedList<WordData>.LinkedListIterator iterator = (LinkedList<WordData>.LinkedListIterator) getWordDataHolder().iterator();
            int totalCount = 0;
            //iterates entirly through the list of WordData while adding each WordData's count value to the totalCount
            while (iterator.hasNext()) {
                totalCount += iterator.next().getCount();
            }
            return totalCount;
        }


    }


    /**
     * finds and returns the ContextData for a given context, or if it does not exist, adds it to the list of ContextData
     *
     * @param context         the context whose ContextData the method searches for
     * @param contextDataList the list of ContextData that is searched through
     * @return the ContextData that was found in the list of ContextData, or added to the list
     */
    public static ContextData addContextData(Context context, LinkedList<ContextData> contextDataList) {
        LLIterator<ContextData> iterator = (LLIterator<ContextData>) contextDataList.iterator();
        ContextData pointer = null;
        ContextData theContextData = null;
        //checks if list is not empty
        if (iterator.hasNext()) {
            pointer = iterator.next();
      /*iterates through the list until it reaches the end, or it has just passed the spot
       * in the list where the context should be*/
            while (iterator.hasNext() && pointer.getContext().compareTo(context) < 0) {
                pointer = iterator.next();
            }
        }
        //checks if pointer is not equal to null
        if (pointer != null) {
            //if the contexts at that point are the same, sets that ContextData to be returned
            if (pointer.getContext().compareTo(context) == 0) {
                theContextData = pointer;
                pointer.incrementNumOccurrences();
            }
      /*if the context at that point is greater than the input context, creates a new ContextData for
       * that object,places it in the correct spot in the list, and sets it to be returned*/
            else if (pointer.getContext().compareTo(context) > 0) {
                theContextData = new GibberishWriter.ContextData(context);
                theContextData.incrementNumOccurrences();
                iterator.addBefore(theContextData);

            }
      /*if the context at that point is less than the input context, creates a new ContextData for
       * that object,places it in the correct spot in the list, and sets it to be returned*/
            else {
                theContextData = new ContextData(context);
                theContextData.incrementNumOccurrences();
                iterator.addAfter(theContextData);

            }
        } else {
            theContextData = new ContextData(context);
            theContextData.incrementNumOccurrences();
            iterator.addAfter(theContextData);

        }
        return theContextData;

    }

    /**
     * Writes the table into a file
     */
    public void writeFile(String contents) {
        BufferedWriter writer = null;
        try {
           /* create a temporary file */
            File logFile = new File("READ_ME.txt");
           /* this will output the full path where the file will be written to */
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(contents);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
               /* close the writer regardless of what happens */
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * allows the user to create a GibberishWriter and give it a file, a context size, and the
     * number of words to be printed
     *
     * @param args the user inputs
     */
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        String[] args1 = new String[3];
//        args1[0] = "SeedNote.txt.txt";
//        args1[1] = "2517";
//        args1[2] = "1000";
        args1[0] = "PersonalWrittingStyle.txt";
        args1[1] = "1000";
        args1[2] = "1000";

        //catches any exeptions thrown by the code
        try {
            //creates a new GibberishWriter that has     a context size of the second input
            GibberishWriter writer = new GibberishWriter(Integer.parseInt(args1[1]));
            //adds the data file to the GibberishWriter
            writer.addDataFile(args1[0]);
            //sets the number of words to be printed
            int numWords = Integer.parseInt(args1[2]);
            //prints the following words until loop has printed the desired number of words
            String contents = null;
            while (numWords >= 0) {
                contents += " " + writer.next();
                System.out.print(" ");
                numWords--;
            }
            writer.writeFile(contents);
            System.out.println("");
        }
        //catches when user does not enter correct number of inputs
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of inputs, please try again");
        }
        //catches if user does not enter a correct file name
        catch (FileNotFoundException e) {
            System.out.println("That is not a name of a file, please try again");
        }
        //catches if user orders the inputs wrong
        catch (NumberFormatException e) {
            System.out.println("Wrong number, order or type of inputs, please try again");
        }
    }
}