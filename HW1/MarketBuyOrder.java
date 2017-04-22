/*class of the market buy order*/
public class MarketBuyOrder extends BuyOrder{
  
  /*constructor for marketbuyorder*/
  public MarketBuyOrder(char symbol, int numShares, double price, boolean allOrNone, Trader trader){
    super(symbol, numShares, price, allOrNone, trader);
  }
  
  /*sets allornone as always false*/
  public boolean getAllOrNone(){
    return false; 
  } 
}