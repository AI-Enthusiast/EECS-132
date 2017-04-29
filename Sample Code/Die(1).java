/* a class to represent a game die */
public class Die extends Object {
  
  /* the face value of the die */
  private int faceValue = 1;
  
  /* the size of the die, one copy owned by the class */
  private int dieSize = 6;
  
  /* a constructor for a default size */
  public Die() {
    super();
  }
  
  /* a constructor to set the size of the die */
  public Die(int dieSize) {
    this();   /* call's the constructor of Object with no input */
    this.dieSize = dieSize;
  }
  
  /* A constructor that sets the face value */
  public Die(int dieSize, int faceValue) {
    this(dieSize);
    this.faceValue = faceValue;
  }
  
  
  /* retrieve the face value */
  public int getFaceValue() {
    return this.faceValue;
  }
  
  /* set the face value */
  public void setFaceValue(int faceValue) {
    this.faceValue = faceValue;
  }
  
  /* roll the die */
  public int roll() {
    this.setFaceValue((int)(Math.random() * this.dieSize) + 1);
    return this.getFaceValue();
  }
}