import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JFrame {
    private boolean isXTurn = true;
    private JButton[][] buttons = new JButton[3][3];
    private String[][] board = new String[3][3];
    public Board() {
        setTitle("TIC TAC TOE");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new TitcTacToePanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new GridLayout(3,3));
        add(panel);

        // CREATE THE BUTTONS
               // CREATE THE BUTTONS
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) { // Cambiado de i++ a j++
        
                JButton button = new JButton("");
                button.setOpaque(false); // Hacer el botón transparente
                button.setContentAreaFilled(false); // No llenar el área del contenido
                button.setBorderPainted(false); // No pintar el borde
                button.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
                panel.add(button);
                buttons[i][j] = button;
                int row = i;
                int col = j;
                button.addActionListener(e -> {
                    if (button.getText().isEmpty()) {
                        button.setText(isXTurn ? "X" : "O");
                        board[row][col] = isXTurn ? "X" : "O";
                        isXTurn = !isXTurn;
                        checkGameState();
                    }
                });
            }
        }
        setVisible(true);

        

    }

    private void checkGameState() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                showWinner(board[i][0]);
                return;
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                showWinner(board[0][i]);
                return;
            }
        }

        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            showWinner(board[0][0]);
            return;
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            showWinner(board[0][2]);
            return;
        }

        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private void showWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        isXTurn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                board[i][j] = null;
            }
        }
    }

}
