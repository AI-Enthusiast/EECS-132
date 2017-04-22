/** Calculates averages and is an example of handling exceptions */
public class Average {

  /**
   * Receives numbers as strings and returns the (int) average.
   * @param values  an array of string values that represent numbers
   * @return the int average of the input values
   * @throws BadDataException if there was invalid data mixed with the numbers
   */
  public static int average(String[] values) throws BadDataException {
    int total = 0;                          // sum of all the values
    int count = 0;                          // number of valid numbers
    boolean badData = false;                // was there an invalid input?
    
    // get each number and add it to the total
    for (int i = 0; i < values.length; i++) {
      try {
        total += Integer.parseInt(values[i]); 
        count++;
      }
      catch (NumberFormatException e) {     // if not an int, see if it is a double
        try {
          total += (int)(Double.parseDouble(values[i]) + 0.5);
          count++;
        }
        catch (NumberFormatException e2) {  // if not a double, ignore it
          badData = true;                   // but record that we have bad data
        }
      }
    }
    
    if (badData)                            // if bad data, let the caller know
      throw new BadDataException(total / count);
    else
      return total / count;
  }
  
  /**
   * Gets data from the command line, computes and reports the average of the data
   * @param args  the command line arguments
   */
  public static void main(String[] args) {
    try {
      int average = average(args);
      System.out.println("The average is " + average);
    }
    catch (ArithmeticException e) {
      System.out.println("There are no numbers.  Get out.");
    }
    catch (BadDataException e) {
      System.out.println("The average of the NUMBERS is " + e.getValue() + ". Get out.");
    }
  }
}