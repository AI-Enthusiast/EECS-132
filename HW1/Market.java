/*class of the market*/
public class Market extends Trader{
  
  /*the symbol for the market*/
  private char symbol;
  
  /*the commission of a trade*/
  private double commission;
  
  /*the fee for a trade*/
  private double tradeFee;
  
  /*the best buy order*/
  private BuyOrder bestBuyOrder;
  
  /*the 2nd best buy order*/
  private BuyOrder secondBestBuyOrder;
  
  /*the best sell order*/
  private SellOrder bestSellOrder;
  
  /*the 2nd best sell order*/
  private SellOrder secondBestSellOrder;
  
  /*the order size of the order*/
  private int marketOrderSize;
  
  /*the maket maker's order price offset*/
  private double priceOffset;
  
  /*the buy order*/
  private MarketBuyOrder marketBuyOrder;
  
  /*the sell order*/
  private MarketSellOrder marketSellOrder;
  
  /*the value of transaction*/
  Transaction transaction;
  
  /*constructor for market*/
  public Market(String name, char symbol, double tradeFee, double commission, int marketMakerOrderSize, double priceOffSet){
    
    super(name);
    
    this.symbol = symbol;
    this.tradeFee = tradeFee; 
    this.commission = commission;
    this.marketOrderSize = marketOrderSize;
    this.priceOffset = priceOffSet;
  }
  
  /*retrieves the stock symbol*/
  public final char getSymbol(){
    return symbol; 
  }
  
  /*retrives commission of market*/
  public double getCommission(){
    return commission; 
  }
  
  /*sets the commission of the market on a trade*/
  public void setCommission(double commission){
    this.commission = commission;
  }
  
  /*retrives the fee of a trade*/
  public double getTradeFee(){
    return tradeFee;
  }
  
  /*sets the fee of a trade*/
  public void setTradeFee(double tradeFee){
    this.tradeFee = tradeFee; 
  }
  
  /*retrives number of shares in a stock*/
  public int getMarketOrderSize(){
    return marketOrderSize; 
  }
  
  /*set the number of shares in a stock*/
  public void setMarketOrderSize(int marketOrderSize){
    this.marketOrderSize = marketOrderSize;
  }
  
  /*retrives the market maker's price offset*/
  public double getPriceOffset(){
    return priceOffset; 
  }
  
  /*sets the price offset of the market maker*/
  public void setPriceOffset(double priceOffset){
    this.priceOffset = priceOffset; 
  }
  
  /*retrives the best buy order*/
  public BuyOrder getBestBuyOrder(){
    return bestBuyOrder; 
  }
  
  /*retrives the best sell order*/
  public SellOrder getBestSellOrder(){
    return bestSellOrder;
  }
  
  /*retrieves the second best buy order*/
  public BuyOrder get2ndBestBuyOrder(){
    return secondBestBuyOrder;
  }
  
  /*retrives the second best sell order*/
  public SellOrder get2ndBestSellOrder(){
    return secondBestSellOrder;
  }
  
  /*retrives the market buy order*/
  public MarketBuyOrder getMarkerBuyOrder(){
    return marketBuyOrder; 
  }
  
  /*sets the market buy order*/
  public void setMarketBuyOrder(MarketBuyOrder marketBuyOrder){
    this.marketBuyOrder = marketBuyOrder;
  }
  
  /*retrives the market sell order*/ 
  public MarketSellOrder getMarketSellOrder(){
    return marketSellOrder;
  }
  
  /*sets the market sell order*/
  public void setMarketSellOrder(MarketSellOrder marketSellOrder){
    this.marketSellOrder = marketSellOrder;
  }
  
  /*price of best buy order (is it exists) or the 
   * price of the market maker buy order (if is does't)*/
  public double currentMarketBuyPrice(){
    if(getBestBuyOrder() != null){
      return bestBuyOrder.getPrice();
    }
    else{ 
      return marketBuyOrder.getPrice();
    }
  }
  
  /*price of best sell order (is it exists) or the 
   * price of the market maker sell order (if is does't)*/
  public double currentMarketSellPrice(){
    if(getBestSellOrder() != null){
      return bestSellOrder.getPrice();
    }
    else{
      return marketSellOrder.getPrice();
    }
  }
  
  /*determins weather the market order is still open*/
  public boolean isOpen(){
    if(marketBuyOrder != null && getMarketSellOrder()!= null && marketBuyOrder.getPrice() < marketSellOrder.getPrice()){
      return true; 
    }
    else{
      return false; 
    }
  }
  
  /*determins weather the order is valid*/
  public boolean isValidOrder(Order order){
    if(order.getSymbol() == this.getSymbol() && order.getPrice() >= marketBuyOrder.getPrice() && order.getPrice() <= marketSellOrder.getPrice()){
      return true;
    }
    else{
      return false;
    }
  }
  
  /*determins weather the order of the buyer and the order of the seller match*/
  public boolean matchingOrder(BuyOrder b, SellOrder s){
    if((b.getPrice() >= s.getPrice()) && (b.getNumberShares() == s.getNumberShares())){
      return true;
    }
    else{
      return false;
    }
  }
  
  /*checks to see is the buy order is the same type as the sell order
   *if so then checks to see is their is an best buy order and if the order is better than that
   * if not then does the same for the second best buy order better then either, takes the spot of that one
   */
  public void addOrderToMarket(BuyOrder b){
    if(b.getSymbol() == symbol){
      if(bestBuyOrder == null || b.getPrice() < bestBuyOrder.getPrice()){
        secondBestBuyOrder = bestBuyOrder;
        bestBuyOrder = b;
      }
      else if(b.getPrice() <= bestBuyOrder.getPrice() || secondBestBuyOrder == null && b.getPrice() > secondBestBuyOrder.getPrice()){
        secondBestBuyOrder = b;
      }
      else{
        ;
      }
    }
  }
  
  
  /*checks to see is the sell order is the same type as the buy order
   *if so then checks to see is their is an best sell order and if the order is better than that
   * if not then does the same for the second best sell order better then either, takes the spot of that one
   */
  public void addOrderToMarket(SellOrder s){
    if(s.getSymbol() == symbol){
      if(bestSellOrder == null && s.getPrice() > bestSellOrder.getPrice()){
        secondBestSellOrder = bestSellOrder;
        bestSellOrder = s;
      }
      else if(s.getPrice() <= bestSellOrder.getPrice() && secondBestSellOrder == null || s.getPrice() > secondBestSellOrder.getPrice()){
        secondBestSellOrder = s;
      }
      else{
        ;
      }
    }
  }
  
  /*checks to see if the market is open and order is valad
   * sees if orders match request or if theive been added to the list of orders
   * it matches and is the best option it proceeds with the transaction 
   */
  public Transaction placeOrder(BuyOrder b){
    if(isOpen() == false || isValidOrder(b) == false){
      return null;
    }
    else if(matchingOrder(b,bestSellOrder) == false && matchingOrder(b, secondBestSellOrder) == false && matchingOrder(b, marketSellOrder) == false){
      addOrderToMarket(b);
      return null;
    }
    if(matchingOrder(b, bestSellOrder) == true){
      if(b.getNumberShares() < bestSellOrder.getNumberShares()){
        transaction = new Transaction(symbol, b.getNumberShares(), bestSellOrder.getPrice(), bestSellOrder.getTrader(), b.getTrader(), this); 
      }
      else{
        transaction = new Transaction(symbol, bestSellOrder.getNumberShares(), bestSellOrder.getPrice(), bestSellOrder.getTrader(), b.getTrader(), this); 
      }
      bestSellOrder = secondBestSellOrder;
      secondBestSellOrder = null;
    }
    else if (matchingOrder(b, secondBestSellOrder) == true){
      if(b.getNumberShares() > secondBestSellOrder.getNumberShares()){
        transaction = new Transaction(symbol, secondBestSellOrder.getNumberShares(), secondBestSellOrder.getPrice(), secondBestSellOrder.getTrader(), b.getTrader(), this); 
      }
      else{
        transaction = new Transaction(symbol, b.getNumberShares(), secondBestSellOrder.getPrice(), secondBestSellOrder.getTrader(), b.getTrader(), this); 
      }
    }
    return transaction;
  }
  
  /*checks to see if the market is open and order is valad
   * sees if orders match request or if theive been added to the list of orders
   * it matches and is the best option it proceeds with the transaction 
   */
  public Transaction placeOrder(SellOrder s){
    if(isOpen() == false || isValidOrder(s) == false){
      return null;
    }
    else if(matchingOrder(bestBuyOrder, s) == false && matchingOrder(secondBestBuyOrder, s) == false && matchingOrder(marketBuyOrder, s) == false){
      addOrderToMarket(s);
      return null;
    }
    if(matchingOrder(bestBuyOrder, s) == true){
      if(s.getNumberShares() < bestSellOrder.getNumberShares()){
        transaction = new Transaction(symbol, s.getNumberShares(), bestBuyOrder.getPrice(), bestBuyOrder.getTrader(), s.getTrader(), this); 
      }
      else{
        transaction = new Transaction(symbol, bestBuyOrder.getNumberShares(), bestBuyOrder.getPrice(), bestBuyOrder.getTrader(), s.getTrader(), this); 
      }
      bestBuyOrder = secondBestBuyOrder;
      secondBestBuyOrder = null;
    }
    else if (matchingOrder(secondBestBuyOrder, s) == true){
      if(s.getNumberShares() > secondBestBuyOrder.getNumberShares()){
        transaction = new Transaction(symbol, secondBestBuyOrder.getNumberShares(), secondBestBuyOrder.getPrice(), secondBestBuyOrder.getTrader(), s.getTrader(), this); 
      }
      else{
        transaction = new Transaction(symbol, s.getNumberShares(), secondBestBuyOrder.getPrice(), secondBestBuyOrder.getTrader(), s.getTrader(), this); 
      }
    }
    return transaction;
  }
}