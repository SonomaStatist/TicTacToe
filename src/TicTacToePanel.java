import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class TicTacToePanel extends JPanel {
    private TicTacToe ticTacToe;
    private int player;
    private int winner;
    private boolean done;
    private Font font;
    private int wins[];
    private JLabel title;
    private JLabel stats;
    private DisplayPanel displayPanel;
    private Image imageX;
    private Image imageO;

    TicTacToePanel() {
        ticTacToe = new TicTacToe();
        player = 1;
        winner = 0;
        done = false;
        wins = new int[4];
        setBackground(Color.WHITE);
        font = new Font("Arial", Font.PLAIN, 30);
        imageO = new ImageIcon(getClass().getResource("res/o.jpg")).getImage();
        imageX = new ImageIcon(getClass().getResource("res/x.jpg")).getImage();
        title = new JLabel("Tic Tac Toe");
        title.setFont(font);
        add(title);
        displayPanel = new DisplayPanel();
        add(displayPanel);
        stats = new JLabel("X: " + wins[1] + "  O: " + wins[2] + "  Cat: " + wins[3]);
        stats.setFont(font);
        add(stats);
    }

    void UpdatePanel() {
        String who;
        switch (winner) {
            case 1:
                who = "X";
                break;
            case 2:
                who = "O";
                break;
            case 3:
                who = "Cat";
                break;
            default:
                who = "";
        }

        stats.setText(who + " wins!");
        stats.repaint();

        int delay = 2000;

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wins[winner]++;
                ticTacToe = new TicTacToe();
                displayPanel.repaint();
                stats.setText("X: " + wins[1] + "  O: " + wins[2] + "  Cat: " + wins[3]);
                stats.repaint();
            }
        };

        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }

    class DisplayPanel extends JPanel {
        public TicTacToeSquare[] squares;

        DisplayPanel() {
            setLayout(new GridLayout(3,3,2,2));
            setBackground(Color.BLACK);
            squares = new TicTacToeSquare[9];
            int s = 0;
            for (int r = 1; r <= 3; r++) {
                for (int c = 1; c <= 3; c++) {
                    squares[s] = new TicTacToeSquare(r, c);
                    add(squares[s]);
                    s++;
                }
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (int i = 0; i < 9; i++) {
                squares[i].repaint();
            }
        }
    }

    class TicTacToeSquare extends JPanel implements MouseListener {

        public int row;
        public int col;

        TicTacToeSquare(int row, int col) {
            this.row = row;
            this.col = col;
            setPreferredSize(new Dimension(100,100));
            setBackground(Color.WHITE);
            addMouseListener(this);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(font);
            char who = ticTacToe.GetPosition(row, col);

            String owner = new String();
            if (who == '1') {
                g2.drawImage(imageX, 0, 0, 100, 100, null);
            }
            else if (who == '2') {
                g2.drawImage(imageO, 0, 0, 100, 100, null);
            }
            g2.drawString(owner, 35, 60);
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            boolean goodMove = ticTacToe.Play(row, col, player);
            if (goodMove) {
                player = player == 1 ? 2 : 1;
                repaint();
                winner = ticTacToe.Test();
                if (winner > 0) {
                    UpdatePanel();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {  }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {  }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {  }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {  }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        TicTacToePanel ticTacToePanel = new TicTacToePanel();
        frame.getContentPane().add(ticTacToePanel);
        frame.setSize(312, 430);
        frame.setLocation(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
