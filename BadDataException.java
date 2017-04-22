/** An exception for when a calculation contains invalid data */
public class BadDataException extends Exception {
  
  /** Stores the result of the calculation */
  private int value;
  
  /** Creates the exception storing the result of the calculation
    * @param value the result of some calculation where we also had bad data
    */
  public BadDataException(int value) {
    this.value = value;
  }
  
  /** Return the value of the calculation that threw this error
    * @return the value of the calculation
    */
  public int getValue() {
    return value;
  }
}