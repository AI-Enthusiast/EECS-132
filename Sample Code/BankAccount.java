/**
 * A class to simulate a bank account.  An example of threads.
 */
public class BankAccount {
  private static final int initialAmount = 1000;         // the initial balance of the account
  private int balance = initialAmount;                   // the current balance of the account
  private int totalDeposits = 0;                         // the total amount deposited
  private int totalWithdraws = 0;                        // the total amount withdrawn
  private volatile boolean depositsDone = false;         // a flag to indicate when we are done with the operation
  private volatile boolean withdrawsDone = false;        //   "volatile" lets compiler know a thread can change the value
  private Object balanceLock = new Object();             // used to lock a portion of the code
  
  /**
   * deposit money in the account
   * @param amount the amount to deposit
   */
  public void deposit (int amount) {
    synchronized (balanceLock) {
      balance += amount;                    // modify the balance
    }
    totalDeposits += amount;
  }
  
  /**
   * withdraw money from the account
   * @param amount the amount to withdraw
   */
  public void withdraw (int amount) {
    synchronized (balanceLock) {
      balance -= amount;                   // modify the balance
    }
    totalWithdraws += amount;
  }
  
  /**
   * do a whole bunch of small deposits
   */
  public void doDeposits() {
    for (int i = 0; i < 1000000; i++) {
      deposit((int)(Math.random() * 10)+1);
    }
  }
  
  /**
   * do a whole bunch of small withdraws
   */
  public void doWithdraws() {
    for (int i = 0; i < 1000000; i++) {
      withdraw((int)(Math.random() * 10)+1);
    }
  }
  
  /**
   * Prints what the balance is and what it should be.
   */
  public void checkBalance() {
    System.out.println("Final balance: " + balance);
    System.out.println("Initial " + initialAmount + " + deposits of " + totalDeposits +
                       " - withdraws of " + totalWithdraws + " = " +
                       (initialAmount + totalDeposits - totalWithdraws));
  }
  
  /**
   * The main method to run the bank simulation.
   * @param args  the command line arguments are ignored
   */
  public static void main(String[] args) {
    final BankAccount account = new BankAccount();    // create an account

    Thread thread1 = new Thread() {
      public void run() {
        account.doDeposits();                       // do a bunch of deposits
        account.depositsDone = true;
      }
    };
    
    Thread thread2 = new Thread() {
      public void run() {
        account.doWithdraws();                      // do a bunch of withdraws
        account.withdrawsDone = true;
      }
    };
    
    thread1.start();                                // start the threads to run in parallel
    thread2.start();
    
                                                    // wait for the threads to complete
    while (!account.depositsDone || !account.withdrawsDone)
      ;                                             // would be better to sleep then spin CPU cycles
    
    account.checkBalance();                     // verify the balance
  }
}
 
