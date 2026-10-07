import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

class PasswordMismatchException extends Exception {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
public class Login{

    static String f_nm, l_nm, add, cont, u_nm;
    JFrame f;

    public Login() {
        f = new JFrame("Login");

        JLabel l1 = new JLabel("Username :  ");
        l1.setBounds(80, 100, 200, 30);
        f.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(300, 100, 250, 30);
        f.add(t1);

        JLabel l2 = new JLabel("Enter Password :  ");
        l2.setBounds(80, 150, 200, 30);
        f.add(l2);

        JPasswordField t2 = new JPasswordField();
        t2.setBounds(300, 150, 250, 30);
        f.add(t2);

        JLabel l3 = new JLabel("Confirm Password :  ");
        l3.setBounds(80, 200, 200, 30);
        f.add(l3);

        JPasswordField t3 = new JPasswordField();
        t3.setBounds(300, 200, 250, 30);
        f.add(t3);


        RoundedCornerButton lb = new RoundedCornerButton("login");
        lb.setForeground(Color.ORANGE);
        Font customFont2 = new Font("Times New Roman", Font.BOLD, 20);
        lb.setFont(customFont2);
        lb.setBounds(260, 280, 100, 50);
        f.add(lb);

        lb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String unm = t1.getText();
                String pw = t2.getText();
                String cpw = t3.getText();
                OtherMethods om = new OtherMethods();

                    try {
                        om.checkPasswordMatch(pw, cpw);

                        if (AdminCredentials.checkAdminCredentials(unm, pw)) {
                            // If the user is an admin, open the admin panel
                            f.dispose();
                            new AdminPanel();
                        }
                        else {
                            File f1 = new File("records.txt");
                            if (!f1.exists()) {
                                f1.createNewFile();
                            }
                            Scanner sc = new Scanner(f1);
                            int s = 0;
                            while (sc.hasNext()) {
                                String fName = sc.next();
                                String lName = sc.next();
                                String uName = sc.next();
                                String Address = sc.next();
                                String cNumber = sc.next();
                                String Password = sc.next();
                                String Gender = sc.next();

                                if (uName.equalsIgnoreCase(unm) && Password.equalsIgnoreCase(pw)) {
                                    f_nm = fName;
                                    l_nm = lName;
                                    add = Address;
                                    cont = cNumber;
                                    s = 1;
                                    f.dispose();
                                    new Store();
                                }
                            }
                            sc.close();
                            if (s == 0)
                                JOptionPane.showMessageDialog(f, "Wrong ID or Password", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        } catch (IOException ff) {
                        ff.printStackTrace();
                    }
                    catch (PasswordMismatchException pe) {
                        JOptionPane.showMessageDialog(f, pe.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

        f.setSize(650, 500);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
