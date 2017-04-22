/*class of the buy order*/
public class BuyOrder extends Order{
  
  /*constructor for BuyOrder*/
  public BuyOrder(char symbol, int numShares, double price, boolean allOrNone, Trader trader){
    super(symbol, numShares, price, allOrNone, trader);
  }
}