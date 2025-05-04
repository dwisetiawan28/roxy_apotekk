import ui.Login;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() ->{
            //buat jframe
            JFrame frame = new JFrame("INVENTORY APOTEK ROXY");
            
            //JPanel Login ke JFrame
            frame.setContentPane(new Login());
            
            // atur JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
