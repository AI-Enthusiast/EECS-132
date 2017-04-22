/*Cormac Dacker
 * Class gives multiple examples of how to write and manipulate Strings through the use of string builder
 */
public class HW2{
  /*method compares two strings to see if they match*/
  public static boolean samePrefix( String lineOne, String lineTwo, int lengthCheck){
    int checker = 0;  //used in charAt(); so that it begins at 0
    /*if the length of the lengthCheck exeeds either String, return false*/
    if(lengthCheck > lineOne.length() || lengthCheck > lineTwo.length()){
      return false;
    }
    /*if the length of the Strings do not match, return false*/
    else if(lineOne.length() != lineTwo.length()){
      return false;
    }
    /*the goal of the loop is to determin whether the two strings match at every char
     * for this it needs both the strings, the length it is checking (lengthCheck) and
     * what char number it is at in the loop (checker)
     */
    while(checker != lengthCheck){
      /*if the char it is on matches, it moves on to the next char if not then it returns false*/
      if (lineOne.charAt(checker) == lineTwo.charAt(checker)){
        checker = checker + 1;
      }
      else{
        return false;
      }
    }
    return true;
  }
  
  /*meathod to cut out the 'whitespaces' form the front of a string*/
  public static String trimSpacesFromFront(String spaceLine){
    StringBuilder builder = new StringBuilder();  //a vairiable to build a new string
    int checker = 0;  //a variable used in charAt(); so that it begins at 0
    /*the goal of the loop is to go down the inputed string, starting at 0, and counstruct a new string with
     * only the whitespaces at the front of the string removed.
     */
    while(checker != spaceLine.length()){
      char a = (spaceLine.charAt(checker));  //a variable to make append assighment easier
      /*if the char at checker is a whitespace then that char is removed, the checker goes up one and
       * the builder appends the char variable a
       */
      if(a == ' '){
        a = (char)(a - ' ');
        checker = checker + 1;
        builder.append(a);
      }
      /*if the char variable a is not a whitespace then we create another loop to repeat till checker is
       * the length of the string.
       */
      else{
        /*the goal of the loop is to append every char of variable b (same as variable a) after all the
         * whitespaces infront of the string have been cut.
         */
        while(checker != spaceLine.length()){
          char b = (spaceLine.charAt(checker));   //a variable to make append assighment easier
          spaceLine.charAt(checker);
          builder.append(b);
          checker = checker + 1;
        }
      }
    }
    return builder.toString();
  }
  
  /*method counts the words in a string*/
  public static int countWords(String countMe){
    countMe = HW2.trimSpacesFromFront(countMe);
    int count = 0; //a variable to store the number of words
    if(countMe.length() != 0){
      count = 1; //reassighned to one because the trimSpacesFromFront causes it to start on a word
    }
    /*the goal of the loop is to count the number of words in a string by counting the whitespaces
     * conditions require the loop to have a checker variable to go down the string with chatAt
     */
    for(int checker = 0; checker < countMe.length() && countMe.length() != 0; checker = checker + 1){
      /*checks to see if the char at checker is a whitespace*/
      if(countMe.charAt(checker) == ' '){
        count = count + 1;
      }
      else{
        ;
      }
    }
    /*if the last char in the string is a space and there is not only one word counted up untill now
     * we subtract count by one becasue in the previos loop it would have added that space as a word
     */
    if(countMe.charAt(countMe.length() - 1) == ' ' && count != 1){
      count = count - 1;
    }
    else{
      ;
    }
    return count;
  }
  
  /*meathod truncates words in a string that exceed its boundary, set by user*/
  public static String truncate(String cutMe, int cutter){
    StringBuilder builder =  new StringBuilder();  //a vairiable to build a new string
    /*if string is less than or equal to the cutter length*/
    if(cutMe.length() <= cutter + 1){
      return cutMe;
    }
    /*if the cutter int is on a char ' ' cut the string one short of that*/
    else if(cutMe.charAt(cutter) == ' '){
      /*the goal of the loop is to construct a new string that is one less than the string cutMe.
       * the precondition requires that the charAt(cutter) to be a whitespace.
       */
      for(int checker = 0; checker < cutter; checker = checker + 1){
        char a = cutMe.charAt(checker); //a variable to make append assighment easier
        builder.append(a);
      }
      return builder.toString();
    }
    /*if the charAt cutter is not on a ' ' truncate the word its on*/
    else if(cutMe.charAt(cutter) != ' '){
      int cutterForBuilder = cutter; //a variable to manipulate the length of the truncation
      /*the goal of  the loop is to shorten the cutterForBuilder till its no longer on a whitespace.
       * the precondition for the loop is that it not be cutter not be equal to the length of the
       * string cutMe and the charAt(cutter) not be equal to s whitespace.
       */
      for( ; cutMe.charAt(cutterForBuilder) != ' ' && cutterForBuilder != 0; cutterForBuilder = cutterForBuilder - 1){
        ;
      }
      /*the goal of the loop is to build the string with the now truncated length.
       * preconditions require the new length to be formed by the prior loop.
       */
      for(int checker = 0; checker != cutterForBuilder; checker = checker + 1){
        char a = cutMe.charAt(checker); //a variable to make append assighment easier
        builder.append(a);
      }
      /*if this truncated the entire string so that its only whitespaces in front it will rebuild
       * the string so that the last word is included
       */
      if(HW2.countWords(builder.toString()) == 0){
        builder.setLength(0);  //a vairiable to build a new string
        int newCutterForBuilder = cutter;  //resets the length of the truncation
        /*the goal of the loop is to increase the length untill int cutterForBuilder is
         * on a non whitespace and followed by a whitespace. The preconditions of the loop is that
         * the length of cutMe is not less than or equal to the truncation length given by cutter
         * reassigned to cutterForBuilder.
         */
        for( ; cutMe.charAt(newCutterForBuilder - 1) == ' ' ; newCutterForBuilder = newCutterForBuilder + 1){
          ;
        }
        for( ; cutMe.charAt(newCutterForBuilder + 1) != ' '; newCutterForBuilder = newCutterForBuilder + 1){
          ;
        }
        
        /*the goal of the loop is to reconstruct the string with the new given length.
         * preconditions requiers the previos string to have contained no words because of truncatrion.
         */
        for(int checker = 0; checker > newCutterForBuilder; checker = checker + 1){
          char a = cutMe.charAt(checker); //a variable to make append assighment easier
          builder.append(a);
        }
        return builder.toString();
      }
      /*if the truncation did not get rid of the only, or last word in the string*/
      else{
        return builder.toString();
      }
    }
    return builder.toString();
  }
  
  /*method adds spaces in between words so that the resulting string is equal to the value of spaaace*/
  public static String padString(String iNeedSpace, int spaaace){
    iNeedSpace = HW2.trimSpacesFromFront(iNeedSpace);
    StringBuilder builder =  new StringBuilder();  //a vairiable to build a new string
    /*if there is only one word (or none) in the string then just return the string*/
    if(HW2.countWords(iNeedSpace) <= 1){
      return iNeedSpace;
    }
    /*if the inputed strings length matches the int then just return the string*/
    else if(iNeedSpace.length() == spaaace){
      return iNeedSpace;
    }
    /*if the string is bigger than the value of spaaace truncate the string using the prior method*/
    else if(iNeedSpace.length() > spaaace){
      iNeedSpace = HW2.truncate(iNeedSpace, spaaace);
      return iNeedSpace;
    }
    /*if there is not one or no words, spaaace is not equal to the length, and the length is not greater
     * than spaaace, than we add spaces between the words.
     */
    else{
      /*the goal of the loop is to add spaces to the string untill it is equal to the desierd length.
       * preconditions require the inputted int to be greater than the length of the string
       */
//      while(builder.length() < spaaace){
      int numOfSpaces = iNeedSpace.length() - spaaace; //a variable that tells us the number of spaces
      int numOfSpaceSlots = HW2.countWords(iNeedSpace) - 1; //a varabile that tells us the slots open for spaces
      /*the goal of the loop is to run up the sting adding spaces into slots...*/
      for(int checker = 0; checker != spaaace; checker = checker + 1){
        char a = iNeedSpace.charAt(checker); //a variable to make append assighment easier
        int s = 0; //a variable used as a placeholder before the if statment below is run through
        /*if the number of avaiable slots is not 0 we assighn variable s*/
        if(numOfSpaceSlots != 0){
          s = (numOfSpaces / numOfSpaceSlots);  //a variable that signifies the number of spaces that'll be appended
        }
        /*if the char at checker is a whitespace, and is followed by a non whitespace, append that char
         * then append the (int)numOfSpaces/numOfSlots = which gives you the number of whitespaces you
         * are adding to that slot
         */
        if(a == ' ' && iNeedSpace.charAt(checker + 1) != ' '){
          builder.append(a);
          numOfSpaces = numOfSpaces - s;
          numOfSpaceSlots = numOfSpaceSlots - 1;
          /*the goal of the loop is to continue till there is no more spaces that need to be appended in
           * the slot it is on. predonditions require char variable a to be a whitespace, and the char
           * following it to not be a whitespace.
           */
          for(int value = s; value != 0; value = value - 1){
            builder.append(' ');
          }
        }
        /*if the char at checker is not a whitespace or it is not followed by a non white space then
         * add that char to builder
         */
        else{
          builder.append(a);
        }
//        }
      }
      return builder.toString();
    }
  }
  
  public static void prettyPrint(String iBePretty, int makeup){
    
  }
  
  public static boolean isAnagram(String smiles, String limes){
    boolean match = false;
    int maxLength = smiles.length();
    if(limes.length() > smiles.length()){
      maxLength = limes.length();
    }
    else{
      ;
    }
    for(int checker = 0; checker < maxLength; checker = checker + 1){
      char a = smiles.charAt(checker);
      if(character.isWhiteSpace(s)){
        ;
      }
      else {
        for (int otherChecker = 0; otherChecker > limes.length() || match == true; otherChecker = otherChecker + 1)
          char b = limes.charAt(otherChecker);
        if (b == a) {
          match = true;
        }
        
      }
    }
  }
}

