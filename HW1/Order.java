/*class of the order*/
public class Order{
  
  /*the symbol of the stock*/
  private char symbol;
  
  /*the number of shares*/
  private int numShares;
  
  /*the price of the share*/
  private double price;
  
  /*the desition between all or some of the shares*/
  private boolean allOrNone;
  
  /*the name of the trader*/
  Trader trader;
  
  /*constructor to summarize an order*/
  public Order(char symbol, int numShares, double price,  boolean allOrNone, Trader trader){
    this.symbol = symbol;
    this.numShares = numShares;
    this.price = price;
    this.allOrNone = allOrNone;
    this.trader = trader;
  }
  
  /*stock symbol*/
  public char getSymbol(){
    return symbol;
  }
  
  /*gets number of shares*/
  public int getNumberShares(){
    return numShares;
  }
  
  /*set number of shares*/
  public void setNumberShares(int numShares){
    this.numShares = numShares; 
  }
  
  /*gets the price per share of the order*/
  public double getPrice(){
    return price; 
  }
  
  /*sets the price per share*/
  public void setPrice(double price){
    this.price = price;
  }
  
  /*determins whether all shares in an order must be sold*/ 
  public boolean getAllOrNone(){
    return allOrNone; 
  }
  
  /*sets whether all shares must be sold or not*/
  public void setAllOrNone(boolean allOrNone){
    this.allOrNone = allOrNone;
  }
  
  /*retrieve the trader that placed the order*/
  public Trader getTrader(){
    return trader;
  }
}