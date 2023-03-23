import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TicTacToeFrame extends JFrame
{
 JPanel mainPnl, topPnl, centerPnl, bottomPnl;
 //JLabel turn, a;
 JButton quitBtn;
 JLabel turn;

 int numMove = 0;

 boolean playerMove = true;

 private static final int ROW = 3;
 private static final int COL = 3;

 TicTacToeTile[][] board = new TicTacToeTile[3][3];
    public TicTacToeFrame() {

       Toolkit kit = Toolkit.getDefaultToolkit();
       Dimension screenSize = kit.getScreenSize();
       int screenHeigh = screenSize.height;
       int screenWidth = screenSize.width;

       setSize(screenWidth * 3/4,600);
       setLocation(screenWidth / 8, (screenHeigh - 600) / 2 );

       setTitle("Tic Tac Toe Game");
       setDefaultCloseOperation(EXIT_ON_CLOSE);

       createGUI();
       setVisible(true);



   }

   private void createGUI()
   {
       mainPnl = new JPanel();
       topPnl = new JPanel();
       centerPnl = new JPanel();
       bottomPnl = new JPanel();

       mainPnl.setLayout(new BorderLayout());
       mainPnl.add(topPnl, BorderLayout.NORTH);
       mainPnl.add(centerPnl, BorderLayout.CENTER);
       mainPnl.add(bottomPnl,BorderLayout.SOUTH);

       add(mainPnl);
       createTopPnl();
       createGridPnl();
       createBottomPnl();


   }
    private void createTopPnl()
    {
       turn = new JLabel("X turn");

       topPnl.add(turn);

    }
    private void createGridPnl() {
        centerPnl.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                board[row][col].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                board[row][col].addActionListener((ActionEvent ae) -> {

                        TicTacToeTile player =  (TicTacToeTile) ae.getSource();

                        if (!player.getText().equals(" "))
                        {
                            JOptionPane.showMessageDialog(null, "Illegal Move. Choose an empty square");
                            return;

                        }
                        if (playerMove)
                        {
                            player.setText("X");
                            turn.setText("O turn");
                        }
                        else
                        {
                            player.setText("O");
                            turn.setText("X turn");
                        }
                        playerMove = !playerMove;
                        numMove++;

                        if (numMove >= 5)
                        {
                            checkWin();
                        }
                        if (numMove >= 7)
                        {
                            checkTie();
                        }
                });
                centerPnl.add(board[row][col]);

            }

    }

    private void checkTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < 3; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
               // xFlag = true;// there is an X in this row
                JOptionPane.showMessageDialog(null, "Tie Game");
                restartGame();
                return;
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
               // oFlag = true; // there is an O in this row
                JOptionPane.showMessageDialog(null, "Tie Game");
                restartGame();
                return;
            }

           /* if(! (xFlag && oFlag) )
            {
                return; // No tie can still have a row win
            }

            xFlag = oFlag = false; */

        }
        // Now scan the columns
        for(int col=0; col < 3; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
               // xFlag = true; // there is an X in this col
                JOptionPane.showMessageDialog(null, "Tie Game");
                restartGame();
                return;
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
              //  oFlag = true; // there is an O in this col
                JOptionPane.showMessageDialog(null, "Tie Game");
                restartGame();
                return;
            }

          /*  if(! (xFlag && oFlag) )
            {
                return; // No tie can still have a col win
            } */
        }
        // Now check for the diagonals
       // xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
           // xFlag = true;
            JOptionPane.showMessageDialog(null, "Tie Game");
            restartGame();
            return;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
           // oFlag = true;
            JOptionPane.showMessageDialog(null, "Tie Game");
            restartGame();
            return;
        }
         /* if(! (xFlag && oFlag) )
        {
            return; // No tie can still have a diag win
        }
        xFlag = oFlag = false; */

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
           // xFlag =  true;
            JOptionPane.showMessageDialog(null, "Tie Game");
            restartGame();
            return;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
           // oFlag =  true;
            JOptionPane.showMessageDialog(null, "Tie Game");
            restartGame();
            return;
        }
      /*  if(! (xFlag && oFlag) )
        {
            return; // No tie can still have a diag win
        } */

        // Checked every vector so I know I have a tie
        return;
    }

    private void checkWin()
    {
        // checking row win
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(board[row][1].getText())
                    && board[row][1].getText().equals(board[row][2].getText())
                    && !board[row][0].getText().equals(" ")) {
                JOptionPane.showMessageDialog(null, board[row][0].getText() + " wins!");
                restartGame();
                return;
            }
        }
        // checking col win
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(board[1][col].getText())
                    && board[1][col].getText().equals(board[2][col].getText())
                    && !board[0][col].getText().equals(" ")) {
                JOptionPane.showMessageDialog(null, board[0][col].getText() + " wins!");
                restartGame();
                return;
            }

        }
        // checking diagonol win
        if (board[0][0].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][2].getText())
                && !board[0][0].getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, board[0][0].getText() + " wins!");
            restartGame();
            return;
        }
        // checking diagonal win
        if (board[0][2].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][0].getText())
                && !board[0][2].getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, board[0][2].getText() + " wins!" );
            restartGame();
            return;
        }
    }


    private boolean restartGame() {

        int dialogButton = JOptionPane.showConfirmDialog(null, "Do you play the game again?", "End Game?", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION)
        {
            clearBoard();
        }
        else
        {
            System.exit(0);
        }
        return false;
    }

    private void clearBoard() {
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col].setText(" ");
            }
        }
        numMove = 0;
        playerMove = true;
        turn.setText("X turn");
    }

    private void createBottomPnl()
    {
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        bottomPnl.add(quitBtn);
    }
}
