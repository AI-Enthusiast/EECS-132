/*class of the sell order*/
public class SellOrder extends Order{
  
  /*constructer for sell order*/
  public SellOrder(char symbol, int numShares, double price,  boolean allOrNone, Trader trader){
    super(symbol, numShares, price, allOrNone, trader);
  }
}