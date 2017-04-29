/** Model the classic board game Chutes and Ladders */
public class ChutesAndLadders {
 
  /** represents the board */
  private int[] board;
  
  /** represents the players of the game */
  private int[] players;
  
  /** Create the game
    * @param numPlayers the number of players in the game
    */
  public ChutesAndLadders(int numPlayers) {
    /* 1. create the board */
    board = new int[101];
    for (int i = 0; i < board.length; i++)
      board[i] = i;
    
    /* 2. add the ladders */
    board[1] = 38;
    board[4] = 14;
    board[78] = 90;
    board[80] = 100;
    board[24] = 87;
    board[13] = 45;
    board[36] = 48;
    
    /* 3. add the chutes */
    board[99] = 1;
    board[44] = 23;
    board[95] = 75;
    board[64] = 60;
    // etc.
    
    /* 4. add the players */
    players = new int[numPlayers];
    /* 5. start all players at 0 */
    for (int i = 0; i < numPlayers; i++)
      players[i] = 0;
  }
  
  /** change the current player
    * @param currentPlayer the current player of the game
    * @return the next player whose turn it is
    */
  public int nextPlayer(int currentPlayer) {
    return (currentPlayer + 1) % players.length;
  }
  
  /** move a player
    * @param currentSpace where the player is
    * @param dieRoll what the player rolled
    * @param board the game board
    * @return the new space for the player
    */
  public int movePlayer(int currentSpace, int dieRoll, int[] board) {
    try {
      return board[currentSpace + dieRoll];
    } 
    catch (ArrayIndexOutOfBoundsException e) {
      return currentSpace;
    }
  }
  
  /**
   * Determines if we have a winner
   * @param currentSpace the space the player is on
   * @param board the gameboard
   * @return true if the current space is a winning space
   */
  public boolean isWinner(int currentSpace, int[] board) {
    return currentSpace == board.length - 1;
  }
  
  /**
   * Play the game
   * @return the player that wins
   */
  public int playGame() {
    int currentPlayer = 0;    // whose turn is it?
    Die die = new Die(6);     // a 6-sided die
    
    // players take turns until we have a winner
    do {
      // 1. roll the die and move the player
      players[currentPlayer] = movePlayer(players[currentPlayer], die.roll(), board);
      
      // 3. check for a winner
      if (!isWinner(players[currentPlayer], board))
        // 4. next player's turn
        currentPlayer = nextPlayer(currentPlayer);
    } while (!isWinner(players[currentPlayer], board));
    
    return currentPlayer;
  }
  
  /**
   * The main method launches the game and reports the result.
   * @param args currently ignored but we could input the number of players
   */
  public static void main(String[] args) {
    ChutesAndLadders game = new ChutesAndLadders(4);
    int winner = game.playGame();
    System.out.println("Player " + winner + " wins!");
  }
}