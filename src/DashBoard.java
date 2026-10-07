import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class DashBoard {
    public DashBoard()
    {
        JFrame f= new JFrame("W e l c o m e");

        Font customFont = new Font("Arial", Font.BOLD, 28);
        Font customFont2 = new Font("Times New Roman", Font.BOLD, 20);

        JLabel l1 = new JLabel("Pharmacy Management System");
        l1.setForeground(Color.BLACK);
        l1.setFont(customFont2);
        l1.setBounds(193, 80, 350, 200);
        f.add(l1);

        RoundedCornerButton b = new RoundedCornerButton("Sign Up");
        b.setPreferredSize(new Dimension(150, 50));
        b.setForeground(Color.YELLOW);
        b.setFont(customFont2);
        b.setBounds(180,240,120,50);
        f.add(b);

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Signup s1 = new Signup();
                f. dispose();

            }
        });

        RoundedCornerButton b2 = new RoundedCornerButton("Login");
        b2.setForeground(Color.YELLOW);
        b2.setFont(customFont2);
        b2.setBounds(360,240,120,50);
        f.add(b2);

        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Login l = new Login();
                f. dispose();

            }
        });


        f.setSize(650,500);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}