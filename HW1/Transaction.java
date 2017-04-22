/*a class that represents the transaction*/
public class Transaction {
  
  /*the symbol of the stock*/
  private final char symbol;
  
  /*the number of shares*/
  private final int shares;
  
  /*the price per stock*/
  private final double price;
  
  /*the name of the buyer*/
  private final Trader buyer;
  
  /*the name of the seller*/
  private final Trader seller;
  
  /*the market handeling the transaction*/
  private String marketName;
  
  /*the indicator whether the transaction is still open*/
  private boolean isClosed = false;
  
  /*the commission of a transaction*/
  private double commission;
  
  /*the fee of a transaction*/
  private double fee;
  
  /*the cost of the transaction*/
  private double cost;
  
  /*the name of the market*/
  private Market market;
  
  /*constructor for the transaction*/
  public Transaction(char symbol, int shares, double price, Trader seller, Trader buyer, Market market){
    this.symbol= symbol;
    this.shares = shares;
    this.price = price;
    this.seller = seller;
    this.buyer = buyer;
    this.market = market;
  }
  
  /*outputs the stock symbol*/
  public char getStockSymbol(){
    return symbol; 
  }
  
  /*number of shares in the transaction*/
  public int getNumberShares(){
    return shares; 
  }
  
  /*price per share of thransaction*/
  public double getPrice(){
    return price; 
  }
  
  /*name of the buyer*/
  public Trader getBuyer(){
    return buyer; 
  }
  
  /*name of the seller*/
  public Trader getSeller(){
    return seller; 
  }
  
  /*name of the market*/
  public String getMarketMaker(){
    return marketName; 
  }
  
  /*whether the transaction is open*/
  public boolean isClosed(){
    return isClosed; 
  }
  
  /*processes the transaction*/
  public void processTransaction(){
    if(!isClosed){
      double cost = this.getNumberShares() * this.getPrice();  //storing the total cost of the transaction
      buyer.withdraw(cost);
      seller.deposit(cost);
      buyer.withdraw(.5 * (market.getTradeFee() + (market.getCommission() * cost)));
      seller.withdraw(.5 * (market.getTradeFee() + (market.getCommission() * cost)));
      market.deposit(market.getTradeFee());
      isClosed = true;
    }
    else{
      ;
    }
  }
}