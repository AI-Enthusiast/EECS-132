/*class of the market sell order*/
public class MarketSellOrder extends SellOrder{
  
  /*constructor for marketsellorder*/
  public MarketSellOrder(char symbol, int numShares, double price, boolean allOrNone, Trader trader){
    super(symbol, numShares, price, allOrNone, trader);
  }
  
  /*sets allornone as always false*/
  public boolean getAllOrNone(){
    return false; 
  }
}