import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
class Person {
    private String fnm;
    private String lnm;
    private String unm;
    private String gender;
    private String address;
    private String cnumber;
    private String password;
    private String cpassword;
    static int count = 0;

    Person() {
        count++;
        JFrame f = new JFrame("Sign Up");

        JLabel l1 = new JLabel("Enter First Name: ");
        l1.setBounds(80, 30, 200, 30);
        f.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(300, 30, 250, 30);
        f.add(t1);

        JLabel l2 = new JLabel("Enter Last Name: ");
        l2.setBounds(80, 60, 200, 30);
        f.add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(300, 60, 250, 30);
        f.add(t2);

        JLabel l3 = new JLabel("Enter Username: ");
        l3.setBounds(80, 90, 200, 30);
        f.add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(300, 90, 250, 30);
        f.add(t3);

        JLabel l4 = new JLabel("Enter Address: ");
        l4.setBounds(80, 120, 200, 30);
        f.add(l4);

        JTextField t4 = new JTextField();
        t4.setBounds(300, 120, 250, 30);
        f.add(t4);

        JLabel l5 = new JLabel("Enter Contact Number: ");
        l5.setBounds(80, 150, 200, 30);
        f.add(l5);

        JTextField t5 = new JTextField();
        t5.setBounds(300, 150, 250, 30);
        f.add(t5);

        JLabel l6 = new JLabel("Enter Password: ");
        l6.setBounds(80, 180, 200, 30);
        f.add(l6);

        JPasswordField t6 = new JPasswordField();
        t6.setBounds(300, 180, 250, 30);
        f.add(t6);

        JLabel l7 = new JLabel("Confirm Password: ");
        l7.setBounds(80, 210, 200, 30);
        f.add(l7);

        JPasswordField t7 = new JPasswordField();
        t7.setBounds(300, 210, 250, 30);
        f.add(t7);

        JRadioButton r1=new JRadioButton("Male");
        JRadioButton r2=new JRadioButton("Female");
        JRadioButton r3=new JRadioButton("Others");
        r1.setBounds(300,240,100,30);
        r2.setBounds(400,240,100,30);
        r3.setBounds(500,240,100,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        f.add(r1);
        f.add(r2);
        f.add(r3);

        RoundedCornerButton sb = new RoundedCornerButton("Submit");
        sb.setForeground(Color.YELLOW);
        Font customFont2 = new Font("Times New Roman", Font.BOLD, 20);
        sb.setFont(customFont2);
        sb.setBounds(260, 350, 200, 40);
        f.add(sb);

        sb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String UNM_Check = t3.getText();
                String pw1 = t6.getText();
                String pw2 = t7.getText();
                String row = t1.getText() + "\t" + t2.getText() + "\t" + t3.getText() + "\t" + t4.getText() + "\t" + t5.getText() + "\t" + t6.getText() + "\t";
                String g="";
                if (r1.isSelected()) {
                    g = r1.getText();
                    row = row + r1.getText();
                } else if (r2.isSelected()) {
                    g = r2.getText();
                    row = row + r2.getText();
                } else if (r3.isSelected()) {
                    g = r3.getText();
                    row = row + r3.getText();
                }
                OtherMethods om1 = new OtherMethods();

                try {
                    om1.checkPasswordMatch(pw1,pw2);
                    File f1 = new File("records.txt");
                    if (!f1.exists()) {
                        f1.createNewFile();
                    }
                    Scanner sc = new Scanner(f1);
                    int s = 0;
                    while (sc.hasNext()) {
                        String fName1 = sc.next();
                        String lName1 = sc.next();
                        String uName1 = sc.next();
                        String Address1 = sc.next();
                        String cNumber1 = sc.next();
                        String Password1 = sc.next();
                        String Gender1 = sc.next();

                        if (UNM_Check.equalsIgnoreCase(uName1)) {
                            s = 1;
                            break;
                        }
                    }
                    sc.close();
                    if(s==0){
                        setFnm(t1.getText());
                        setLnm(t2.getText());
                        setUnm(t3.getText());
                        setAddress(t4.getText());
                        setCnumber(t5.getText());
                        setPassword(t6.getText());
                        setGender(g);

                        File f2 = new File("records.txt");
                        FileWriter fw = new FileWriter(f2,true);
                        fw.write(row+"\n");
                        fw.close();

                        new Login();
                        f.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "ID Already Exists.\n LOGIN instead","Duplicate ID", JOptionPane.ERROR_MESSAGE);
                        f.dispose();
                    }
                }
                catch (IOException fq){
                    fq.printStackTrace();
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

    Person(String fnm, String lnm, String unm, String address, String cnumber, String password, String gender){
        this.fnm=fnm;
        this.lnm=lnm;
        this.unm=unm;
        this.address=address;
        this.cnumber=cnumber;
        this.password=password;
        this.gender=gender;
        count++;
    }

    public String getFnm() {
        return fnm;
    }

    public void setFnm(String fnm) {
        this.fnm = fnm;
    }

    public String getLnm() {
        return lnm;
    }

    public void setLnm(String lnm) {
        this.lnm = lnm;
    }

    public String getUnm() {
        return unm;
    }

    public void setUnm(String unm) {
        this.unm = unm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return "First Name : " + fnm+
                "\nLast Name: " + lnm +
                "\nUsername: " + unm +
                "\nGender: " + gender +
                "\nAddress: " + address+
                "\nContact Number: '" + cnumber;
    }
}
