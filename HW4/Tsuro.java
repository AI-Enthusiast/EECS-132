import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates the board game Tsuro
 *
 * @author Cormac Dacker
 * @since 11/10/2016
 */
public class Tsuro extends JFrame {

    /*Recalls the number of rows on the board*/
    private int rows;

    /*Recalls the number of columns on the board*/
    private int cols;

    /*Recalls the set hand size*/
    private int handsize;

    /*Recalls who's turn it is*/
    private boolean turn;

    /*Recalls the button that was last pressed*/
    private TsuroButton buttonPressed = null;

    /*Recalls the panel used to create the board*/
    private JPanel boardPanel;

    /*Recalls the array of buttons used to construct the board*/
    private TsuroButton[][] buttons;

    /*Recalls the array of TsuroButtons in playerOne's hand*/
    private TsuroButton[] buttonsOne;

    /*Recalls the array of TsuroButtons in playerTwo's hand*/
    private TsuroButton[] buttonsTwo;

    /*Recalls the location of playerOne's stone, starting location at 6 on path array*/
    private int plrOneStone = 6;

    /*Recalls the location of playerTwo's stone, starting location at 2 on path array*/
    private int plrTwoStone = 2;

    /*Recalls the number of turns that has passed, initialized to 0*/
    private int turnCount = 0;

    /*Recalls the identity of the button used in the hand*/
    private int usedButton;

    /*Recalls a boolean that determines if the game is over*/
    private boolean gameOver;

    /*Recalls the x co-ordinate of the button on the board that was pressed*/
    private int xRows;

    /*Recalls the y co-ordinate of the button on the board that was pressed*/
    private int yCols;

    /**
     * Creates the Tsuro Board with default inputs
     * default inputs are a 6x6 grid and a handsize of 3
     */
    public Tsuro() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        this.handsize = 3;
        this.rows = 6;
        this.cols = 6;
        makeBoard(6, 6);
        makeHandOne(3);
        makeHandTwo(3);
    }

    /**
     * Creates the Tsuro board
     *
     * @param rows the number of rows of the board
     * @param cols the number of columns of the board
     */
    public Tsuro(int rows, int cols) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        /*If the board size is impossible*/
        if (rows < 1 || cols < 1) {
            JOptionPane.showMessageDialog(new JFrame(), "Board Size Must be more than 1.\n Suggested size is 6x6");
        } else {
            this.handsize = 3;
            this.rows = rows;
            this.cols = cols;
            makeBoard(rows, cols);
            makeHandOne(3);
            makeHandTwo(3);
        }
    }

    /**
     * Creates a the Tsuro board and handsize
     *
     * @param rows     the number of rows of the board
     * @param cols     the number of columns of the board
     * @param handsize the size of the hand of the players
     */
    public Tsuro(int rows, int cols, int handsize) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        /*If the board size is impossible*/
        if (rows < 1 || cols < 1) {
            JOptionPane.showMessageDialog(new JFrame(), "Board size Must be more than 1.\n Suggested size is 6*6");
        } else {
            /*If the hand size is impossible*/
            if (handsize < 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Hand size must be more than 1.\nSuggested size is 3");
            } else {
                this.rows = rows;
                this.cols = cols;
                this.handsize = handsize;
                makeBoard(rows, cols);
                makeHandOne(handsize);
                makeHandTwo(handsize);
            }
        }
    }

    /**
     * Creates the board with the inputted rows and columns from the constructor
     *
     * @param rows the number of rows of the board
     * @param cols the number of columns of the board
     */
    private void makeBoard(int rows, int cols) {
        JPanel panels = new JPanel(new GridLayout(rows, cols));
        panels.setBackground(Color.BLACK);
        this.getContentPane().add(panels, "Center");
        /*Array of TsuroButtons that will be made into a grid representing the board*/
        TsuroButton[][] buttons = new TsuroButton[rows][cols];
        /*Runs through the button array made of rows and cols creating action listeners*/
        for (int rowX = 0; rowX < buttons.length; rowX++) {
            for (int colsY = 0; colsY < buttons[rowX].length; colsY++) {
                buttons[rowX][colsY] = new TsuroButton();
                buttons[rowX][colsY].addActionListener(new ActionListener() {

                    /**
                     * Action listener to determine the button that is pressed
                     *
                     * @param e ActionEvent that can be traced back to a button clicked
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        /*If the game is NOT over*/
                        if (!getGameOver()) {
                            TsuroButton[][] buttonArray = (TsuroButton[][]) e.getSource();
                            TsuroButton button = (TsuroButton) e.getSource();
                            /*If there is a TsuroButton highlighted*/
                            if (getButtonPressed() != null) {
                                locate(button);
                                /*If the move is legal add the TsuroButton to the board*/
                                if (isLegalMove(buttonArray)) {
                                    buttonToBoard(buttonArray);
                                    swichTurn();
                                }
                            }
                        }
                    }
                });
                buttons[rowX][colsY].setBackground(Color.DARK_GRAY);
                panels.add(buttons[rowX][colsY]);
            }
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); /*Gets the dimensions of the monitor*/
        this.setSize((150 * rows), (150 * cols));
        this.setLocation(((dim.width) - (this.getSize().width)),
                ((dim.height / 2) - (this.getSize().height / 2)));
        this.setTitle("Tsuro~~Ready Player One?");
        this.setVisible(true);
    }

    /**
     * Creates playerOne's hand
     *
     * @param handsize the number of Tsuro tiles assigned in the constructor
     */
    private void makeHandOne(int handsize) {
        JPanel panelsOne = new JPanel(new GridLayout(1, handsize));
        JFrame hand = new JFrame();
        hand.getContentPane().add(panelsOne, "Center");
        buttonsOne = new TsuroButton[handsize];
        /*Creates hand by working up an array*/
        for (int curButton = 0; curButton < handsize; curButton++) {
            buttonsOne[curButton] = new TsuroButton();
            buttonsOne[curButton].addActionListener(new ActionListener() {
                /**
                 * Action listener to determine the button that is pressed
                 *
                 * @param e ActionEvent that can be traced back to a button clicked
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*If the game is NOT over*/
                    if (!getGameOver()) {
                        /*If the action event is a Tsuro Button then do the following*/
                        if (e.getSource() instanceof TsuroButton) {
                            /*Creates a TsuroButton by typecasting the action even*/
                            TsuroButton button = (TsuroButton) e.getSource();
                            /*If b is in the hand of whom ever turn it is do the following, if not nothing is done*/
                            if (inTheCorrectHand(button)) {
                                /*If the last button pressed does not equal nothing*/
                                if (getButtonPressed() != null) {
                                    getButtonPressed().setBackground(Color.BLACK);
                                }
                                /*If the last button pressed is the current button, rotate dat button ;)*/
                                if ((TsuroButton) getButtonPressed() == button) {
                                    rotate(button);
                                }
                                setButtonPressed(button);
                                button.setBackground(Color.YELLOW);
                            }
                        }
                    }
                }
            });
            buttonsOne[curButton].setBackground(Color.black);
            buttonsOne[curButton].setConnections(TsuroButton.makeRandomConnectArray());
            buttonsOne[curButton].addStone(Color.CYAN, getPlayerOneStone());
            panelsOne.add(buttonsOne[curButton]);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); /*Gets the dimensions of the monitor*/
        hand.setSize((200 * handsize), 200);
        hand.setLocation(0, ((dim.height / 4) - (hand.getSize().height / 2)));
        hand.setTitle("Player One's Hand");
        hand.setVisible(true);
    }

    /**
     * Creates playerTwo's hand
     *
     * @param handsize the number of Tsuro tiles assigned in the constructor
     */
    private void makeHandTwo(int handsize) {
        JPanel panelsTwo = new JPanel(new GridLayout(1, handsize));
        JFrame hand = new JFrame();
        hand.getContentPane().add(panelsTwo, "Center");
        buttonsTwo = new TsuroButton[handsize];
    /*Creates hand by working up an array*/
        for (int curButton = 0; curButton < handsize; curButton++) {
            buttonsTwo[curButton] = new TsuroButton();
            buttonsTwo[curButton].addActionListener(new ActionListener() {
                /**
                 * Action listener to determine the button that is pressed
                 *
                 * @param e ActionEvent that can be traced back to a button clicked
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*If the game is NOT over*/
                    if (!getGameOver()) {
                        /*If the action event is a Tsuro Button then do the following*/
                        if (e.getSource() instanceof TsuroButton) {
                            /*Creates a TsuroButton by typecasting the action even*/
                            TsuroButton button = (TsuroButton) e.getSource();
                            /*If b is in the hand of whom ever turn it is do the following, if not nothing is done*/
                            if (inTheCorrectHand(button)) {
                                /*If the last button pressed does not equal nothing*/
                                if (getButtonPressed() != null) {
                                    getButtonPressed().setBackground(Color.BLACK);
                                }
                                /*If the last button pressed is the current button, rotate dat button ;)*/
                                if ((TsuroButton) getButtonPressed() == button) {
                                    rotate(button);
                                }
                                setButtonPressed(button);
                                button.setBackground(Color.YELLOW);
                            }
                        }
                    }
                }
            });
            buttonsTwo[curButton].setBackground(Color.black);
            buttonsTwo[curButton].setConnections(TsuroButton.makeRandomConnectArray());
            buttonsTwo[curButton].addStone(Color.MAGENTA, getPlayerTwoStone());
            panelsTwo.add(buttonsTwo[curButton]);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); /*Gets the dimensions of the monitor*/
        hand.setSize((200 * handsize), 200);
        hand.setLocation(0, ((dim.height / 2) - (hand.getSize().height / 2)));
        hand.setTitle("Player Two's Hand");
        hand.setVisible(true);
    }

    /**
     * Gets the number of rows assigned in the constructor
     *
     * @return int representing the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of column assigned in the constructor
     *
     * @return int representing the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Getter for the size of the hands
     *
     * @return int that represents the size of the hands
     */
    public int getHandsize() {
        return handsize;
    }

    /**
     * Gets the last pressed button
     *
     * @return TsuroButton that represents that last pressed button
     */
    public TsuroButton getButtonPressed() {
        return buttonPressed;
    }

    /**
     * Sets the pressed button
     *
     * @param bPressed TsuroButton that represents the button that was pressed
     */
    public void setButtonPressed(TsuroButton bPressed) {
        this.buttonPressed = bPressed;
    }

    /**
     * Checks to see if my parents are proud of me yet...
     *
     * @return they are not
     */
    public boolean areParentsProud() {
        return false;
    }

    /**
     * Gets the panels that are one the board
     *
     * @return JPannel that represent the panel used on the board
     */
    public JPanel getBoardPanels() {
        return boardPanel;
    }

    /**
     * Getter to determine who's turn it is
     *
     * @return boolean that if false means its playerOne's turn, if its true it's playerTwo's turn
     */
    public boolean getWhosTurn() {
        return turn;
    }

    /**
     * Sets who's turn it is
     *
     * @param turn boolean that represents a players turn
     */
    public void setWhosTurn(boolean turn) {
        /*If input is false = playerOnes's turn, if true = playerTwo's turn*/
        this.turn = turn;

    }

    /**
     * Gets the location of playerOn's stone
     *
     * @return the location of playerOne's stone
     */
    public int getPlayerOneStone() {
        return plrOneStone;
    }

    /**
     * Gets the location of the stone for playerTwo
     *
     * @return the location of playerTwo's stone
     */
    public int getPlayerTwoStone() {
        return plrTwoStone;
    }

    /**
     * Getter for the location of the stone for the current player
     *
     * @return int representing the location in the path array the stone is at
     */
    public int getStone() {
        /*If it's playerTwo's turn return the location of their stone*/
        if (getWhosTurn()) {
            return getPlayerTwoStone();
        } else {
            return getPlayerOneStone();
        }
    }

    /**
     * Gets the connecting path from where the stone is at
     *
     * @param button button with stone on it
     * @return int that represents where the stone should be placed should it move down the path correctly
     */
    public int getPath(TsuroButton button) {
        int start = getStone();
        int[] path = button.getConnections();
        return path[start];
    }

    /**
     * Moves the stone down the path
     *
     * @param button the Tsuro button pressed with the moved stone
     */
    public void stoneDownPath(TsuroButton button) {
        getButtonPressed().removeStone(getStone());
        if (getWhosTurn()) {
            getButtonPressed().addStone(Color.MAGENTA, getPath(button));
        } else {
            getButtonPressed().addStone(Color.CYAN, getPath(button));
        }
    /*Still needs a way to check if there's another path avaiable to go down,
     * such as if there's a TsuroButton it connects two.
     */
    }

    /**
     * The last action taken at the end of the turn.
     * Has several helper methods it goes through.
     */
    public void swichTurn() {
        //add a new TsuroButton to replace the used one
        //setButtonPressed() to null
        //remove the stones from the other TsuroButtons in hand and place an new location
        stoneDownPath(getButtonPressed());
        addTurn();
        isGameOver();
    }

    /**
     * Gets the location of the button in the hand that was used
     *
     * @return int that represents the location in the hand
     */
    public int getUsedButton() {
        return usedButton;
    }

    /**
     * Sets the location of the used TsuroButton so that we know what TsuroButton to replace
     *
     * @param button
     */
    public void setUsedButton(int button) {
        this.usedButton = button;
    }

    /**
     * Checks to see if the inputted TsuroButton is in the hand of whom ever turn it is
     *
     * @param button represents the TsuroButton pressed in actionListener
     * @return boolean that is true if variable b was in the correct hand, and false if not
     */
    public boolean inTheCorrectHand(TsuroButton button) {
        int index = 0;
        /*If it's playerTwo's turn do the following*/
        if (getWhosTurn()) {
            /*Compares the inputted TsuroButton to the buttons available in playerTwo's hand*/
            while ((button != buttonsTwo[index]) && (index < (getHandsize() - 1))) {
                index++;
            }
            /*if the buttons are the same then set the location of the used button
             * to add another one to that location later
             */
            if (button == buttonsTwo[index]) {
                setUsedButton(index);
            }
            return button == buttonsTwo[index];
        /*If it's playerOne's turn do the following*/
        } else {
             /*Compares the inputted TsuroButton to the buttons available in playerOne's hand*/
            while ((button != buttonsOne[index]) && (index < (getHandsize() - 1))) {
                index++;
            }
            /*if the buttons are the same then set the location of the used button
             * to add another one to that location later
             */
            if (button == buttonsTwo[index]) {
                setUsedButton(index);
            }
            return button == buttonsOne[index];
        }
    }

    /**
     * Gets the Location of the button on the board that was pressed
     * Helper method for actionPreformed
     *
     * @param button a TsuroButton that was pressed on the board
     * @return the location of variable button on the board
     */
    public TsuroButton getBBLocation(TsuroButton[][] button) {
        return button[getX()][getY()];
    }

    /**
     * Getter for the x co-ordinate of the button pressed on the board
     *
     * @return int that represents the row of the button on the board
     */
    @Override
    public int getX() {
        return xRows;
    }

    /**
     * Setter for the x co-ordinate of the button pressed on the board
     *
     * @param x represents the row of the button pressed on the board
     */
    public void setX(int x) {
        this.xRows = x;
    }

    /**
     * Getter for the y co-ordinate of the button pressed on the board
     *
     * @return int that represents the column of the button on the board
     */
    @Override
    public int getY() {
        return yCols;
    }

    /**
     * Setter for the y co-ordinate of the button pressed on the board
     *
     * @param y represents the column of the button pressed on the board
     */
    public void setY(int y) {
        this.yCols = y;
    }

    /**
     * Locates the position of the button on the board that was pressed
     *
     * @param button TsuroButton that represents the pressed button on the board
     */
    public void locate(TsuroButton button) {
        int xCord = 0;
        int yCord = 0;
        TsuroButton[][] hypButton = new TsuroButton[getRows()][getCols()];
        /*Loop goes though the rows compairing it to the button of the board that was pressed*/
        while (button != hypButton[xCord][yCord] && xCord < getRows()) {
            /*Loop goes through the columns compareing it to the button of the board that was pressed*/
            while (yCord < getCols() && button != hypButton[xCord][yCord]) {
                yCord++;
            }
            xCord++;
        }
        /*If it finds the matching button*/
        if (button == hypButton[xCord][yCord]) {
            setX(xCord);
            setY(yCord);
        }
    }

    /**
     * Gets the number of turns that have passed
     * Only used when determining if its the first turn of either player.
     *
     * @return
     */
    public int getTurnCount() {
        return turnCount;
    }

    /**
     * Adds one to variable turnCount
     */
    public void addTurn() {
        this.turnCount = getTurnCount() + 1;
    }

    /**
     * Adds the TsuroButton to the board
     *
     * @param button the location on the board where the TsuroButton will be placed
     */
    private void buttonToBoard(TsuroButton[][] button) {
        /*Dear Grader,
         *   I was struggling on implementing this button on the board for several days,
         * if you please could write a comment about how to do this correctly so I can feel
         * like an idiot, that would be awesome. Thanks so much!
         * - Cormac D.
         */
//        locate(button);
        button[getX()][getY()].setConnections(getButtonPressed().getConnections());
//        getBoardPanels().remove(getBBLocation(button));
//        getBoardPanels().add(getButtonPressed());
//        this.getContentPane().add(getBoardPanels());
    }

    /**
     * Checks to see if the move selected is allowed
     *
     * @param button TsuroButton of the board that the player wishes to add a TsuroButton to
     * @return boolean representing if the location is an acceptable placement of the TsuroButton
     */
    private boolean isLegalMove(TsuroButton[][] button) {
        /*If it's the first turn of either player*/
        if (getTurnCount() < 2) {
            /*If it's playerTwo's turn*/
            if (getWhosTurn()) {
                int index = 0;
                /*Loop ensures that the button selected is on the right hand side*/
                while (button[index][getCols()] != button[getX()][getY()] || index < getRows()) {
                    index++;
                }
                return button[index][getCols()] != button[getX()][getY()];
            /*Else it's playerOne's turn*/
            } else {
                int index = 0;
                /*Loop ensures that the button selected is ont the left hand side*/
                while (button[index][1] != button[getX()][getY()] || index < getRows()) {
                    index++;
                }
                return button[index][1] != button[getX()][getY()];
            }
        }
        return true;
    }

    /**
     * Checks to see if there are more there are more moves available
     *
     * @return boolean representing if the game is over
     */
    public boolean isGameOver() {
        /*Game is already been declared as over*/
        if (getGameOver()) {
            return true;
        } else {
            //check if there are other moves available
            //if no moves are available setGameOver(true);
            //this.setTitle("GAME OVER");
            return false; //temporary measure
        }
    }

    /**
     * Getter for the field that determines if the game is over
     *
     * @return boolean representing the end of the game
     */
    private boolean getGameOver() {
        return gameOver;
    }

    /**
     * Setter for if the game is over
     *
     * @param over if this is true then the game is over, else the game will continue
     */
    private void setGameOver(boolean over) {
        this.gameOver = over;
    }

    /**
     * Adds two to each (most) of the values inputted from the rotate Array method
     *
     * @param value represents a path location
     * @return the new variable value that is a component of the rotated Array
     */
    public int addTwo(int value) {
        /* If the inputted number is above 7 or below 0.
         * Note this is not actually possible but is created in order to properly test method.
         */
        if (value > 7 || value < 0) {
            return 0;
        }
        /*if the path value is not any of these values that Connamacher set to screw us up*/
        else if (value != 2 && value != 6 && value != 7 && value != 3) {
            value = value + 2;
        } else {
            if (value == 2) {
                value = value + 3;
            } else if (value == 3) {
                value = value + 1;
            } else if (value == 6) {
                value = 1;
            } else {
                value = 0;
            }
        }
        return value;
    }

    /**
     * Produces an array that represents the new connections of a rotated TsuroButton
     *
     * @return array that is the rotated version of the paths rotated
     */
    public int[] rotateArray() {
        int[] buttonArray = ((TsuroButton) getButtonPressed()).getConnections();
        int[] rotator = new int[8];
        /*Works up the array and changing the placement of each path connection point by two*/
        for (int counter = 0; counter < 8; counter++) {
            rotator[addTwo(counter)] = addTwo(buttonArray[counter]);
        }
        return rotator;
    }

    /**
     * Rotates the paths of the TsuroButton
     *
     * @param button TsuroButton with rotated paths
     */
    public void rotate(TsuroButton button) {
        button.setConnections(rotateArray());
    }

    /**
     * The main method of Tsuro
     *
     * @param args the number of inputted arg
     */
    public static void main(String[] args) {
        int[] intArgs = new int[args.length + 1];
        int count = 0;
        /*The loop goes through the arguments and determines that they are all the correct type*/
        for (int index = 0; index < args.length && count < args.length; index++) {
            try {
                intArgs[count] = Integer.parseInt(args[index]);
                count++;
            } catch (NumberFormatException e) {
            }
        }
        /*If there is three arguments and they are all grater than 0*/
        if (intArgs[0] > 0 && intArgs[1] > 0 && intArgs[2] > 0 && count == 3) {
            new Tsuro(intArgs[0], intArgs[1], intArgs[2]);
        /*If there is two arguments and they are both greater than 0*/
        } else if (intArgs[0] > 0 && intArgs[1] > 0 && count == 2) {
            new Tsuro(intArgs[0], intArgs[1]);
        /*If there was no arguments*/
        } else if (count == 0) {
            new Tsuro();
        /*If there is only one argument or more than 3 arguments*/
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "ERROR\nYou need to specify the board size\n" +
                    "Please input either two arguments representing the number of rows and columns you desire\n" +
                    "or\nThree arguments representing (in order) the number of rows, columns, and hand size\n" +
                    "or\nZero arguments setting everything to its default size\n\nNOTE: all inputs must be " +
                    "at least be a possible board size (positive integer)");
        }
    }
}