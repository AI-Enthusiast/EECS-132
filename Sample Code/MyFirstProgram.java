/* A stand alone program */
public class MyFirstProgram {
  
  /* the main method to launch the program */
  public static void main(String[] args) {
    
    System.out.println("I live");

    // printing out the command line arguments
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
    }
  }
}
