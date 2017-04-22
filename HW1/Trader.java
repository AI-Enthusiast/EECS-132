/*Cormac Dacker, this class is the fist time I've been taught code,
 * and the first time I can ask for help if I get stuck on a problem.
 * While it's hard being a complete noob in java it's also incredibly
 * exciting.
 */

/*a class that represents traders*/
public class Trader{
  
  /*the name of the trader name*/ 
  private String name;
  
  /*the balance of trade*/
  private double balance;
  
  /*constructor for the trader that states their name*/
  public Trader (String name){
    this.name = name;
  }
  
  /*retrive trader name*/
  public String getName(){
    return name;
  }
  
  /*sets or resets trader name*/
  public void setName(String name){
    this.name = name;
  }
  
  /*gets balance of trader's account*/
  public double getBalance(){
    return balance;
  }
  
  /*withdraws from balance*/
  public void withdraw(double withdraw){
    balance = (balance - withdraw); 
  }
  
  /*deposits money into the account*/
  public void deposit(double deposit){
    balance = (balance + deposit);
  }
}