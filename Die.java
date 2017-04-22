/* a class to represent a game die */
public class Die extends Object {
  
  /* the face value of the die */
  private int faceValue = 1;
  
  /* the size of the die, one copy owned by the class */
  private int dieSize = 6;
  
  /* a constructor to set the size of the die */
  public Die(int dieSize) {
    super();   /* call's the constructor of Object with no input */
    this.dieSize = dieSize;
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