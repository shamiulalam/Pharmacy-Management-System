import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingPanel {
    private JFrame frame;
    private JPanel panel;
    private JProgressBar progressBar;
    private Timer timer;
    private int progress;
    private static final int LOADING_TIME = 8000; //  seconds in milliseconds

    public LoadingPanel() {
        frame = new JFrame("P l e a s e   W a i t");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel = new JPanel(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        panel.add(progressBar, BorderLayout.CENTER);

        frame.add(panel);
        timer = new Timer(LOADING_TIME / 100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 100) {
                    timer.stop();
                    frame.dispose();
                    new DashBoard();
                } else {
                    progress++;
                    progressBar.setValue(progress);
                    progressBar.setString("p   r   o   g   r   a   m        i   s        l   o   a   d   i   n   g   [" + progress + "%]");
                }
            }
        });

        timer.start();
    }
}