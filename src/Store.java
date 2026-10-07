import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class Store {
    JFrame f;
    JComboBox<String> medicineComboBox;
    JTextField quantityTextField; // Add quantity JTextField
    JTable medicineTable;
    DefaultTableModel tableModel;
    Medicine[] arr;
    JLabel totalLabel; // Add total label
    double totalAmount;
    Login ll;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Store() {
        totalAmount = 0.0;
        Login ll = new Login();
        ll.f.dispose();
        f = new JFrame("Store");

        JLabel l1 = new JLabel();
        l1.setBounds(30, 10, 200, 100);
        l1.setText("Name: " + ll.f_nm + " " + ll.l_nm);
        f.add(l1);

        JLabel l2 = new JLabel();
        l2.setBounds(230, 10, 200, 100);
        l2.setText("Contact Number: " + ll.cont);
        f.add(l2);

        JLabel l3 = new JLabel();
        l3.setBounds(500, 10, 200, 100);
        l3.setText("Address: " + ll.add);
        f.add(l3);

        int med = 0;
        try {
            File f5 = new File("medlist.txt");
            Scanner s = new Scanner(f5);
            while (s.hasNextLine()) {
                s.nextLine();
                med++;
            }
            s.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        arr = new Medicine[med];
        try {
            File f8 = new File("medlist.txt");
            Scanner s1 = new Scanner(f8);
            for (int i = 0; i < med; i++) {
                String line = s1.nextLine();
                String[] line_a = line.split("\t");
                arr[i] = new Medicine(line_a[0], Integer.valueOf(line_a[1]), Double.valueOf(line_a[2]));
            }
            s1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        totalLabel = new JLabel("Total Amount: BDT 0.0");
        totalLabel.setBounds(480, 450, 200, 20);
        f.add(totalLabel);

        JButton addButton = new JButton("Add Medicine");
        addButton.setBounds(40, 520, 200, 30);
        f.add(addButton);

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(450, 520, 200, 30);
        f.add(generateBillButton);

        String[] columnNames = {"Medicine Name", "Rate", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        medicineTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(medicineTable);
        scrollPane.setBounds(50, 300, 400, 200);
        f.add(scrollPane);

        medicineComboBox = new JComboBox<>();
        String[] medicineNames = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            medicineNames[i] = arr[i].getName();
        }
        medicineComboBox = new JComboBox<>(medicineNames);
        medicineComboBox.setBounds(50, 250, 200, 30);
        f.add(medicineComboBox);

        JLabel ql = new JLabel("Quantity:");
        ql.setBounds(280, 250, 80, 30); // Adjust the bounds as needed
        f.add(ql);

        quantityTextField = new JTextField(); // Add quantity JTextField
        quantityTextField.setBounds(340, 250, 60, 30);
        f.add(quantityTextField);

        JButton deleteButton = new JButton("Delete Medicine");
        deleteButton.setBounds(255, 520, 180, 30);
        f.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = medicineTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Remove the selected row from the JTable
                    tableModel.removeRow(selectedRow);
                    // Update the total amount after deleting the medicine
                    updateTotalAmount();
                } else {
                    JOptionPane.showMessageDialog(f, "Select a medicine to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMedicine = (String) medicineComboBox.getSelectedItem();
                String quantityStr = quantityTextField.getText();
                if (selectedMedicine != null && !quantityStr.isEmpty()) {
                    addMedicineToTable(selectedMedicine, quantityStr, arr);
                }
            }
        });

        generateBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(f, "The medicine list is empty. Add medicines before generating a bill.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (checkQuantityExceeded()) {
                    JOptionPane.showMessageDialog(f, "Quantity of some medicines exceeded the available quantity. Please retry.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    generateBill();
                }
            }
        });

        f.setSize(650, 600);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addMedicineToTable(String medicineName, String quantityStr, Medicine[] medicines) {
        int quantity = Integer.parseInt(quantityStr);
        for (Medicine medicine : medicines) {
            if (medicine.getName().equals(medicineName)) {
                double rate = medicine.getRate();
                tableModel.addRow(new Object[]{medicine.getName(), rate, quantity});
                updateTotalAmount();
                break;
            }
        }
    }

    private boolean checkQuantityExceeded() {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String medicineName = (String) tableModel.getValueAt(row, 0);
            int quantity = (int) tableModel.getValueAt(row, 2);
            for (Medicine medicine : arr) {
                if (medicine.getName().equals(medicineName) && quantity > medicine.getQuantity()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void generateBill() {
        try {
            String randomBillFileName = "bill_" + new Random().nextInt(1000) + ".txt";
            StringBuilder billContent = new StringBuilder();

            billContent.append("Name: " + ll.f_nm + " " + ll.l_nm + "\n");
            billContent.append("Contact Number: " + ll.cont + "\n");
            billContent.append("Address: " + ll.add + "\n");
            billContent.append("Date Created: " + dateFormat.format(new Date()) + "\n\n");
            System.out.println();
            billContent.append("Medicine Name\tQuantity\tRate\tAmount\n\n");

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                String medicineName = (String) tableModel.getValueAt(row, 0);
                int quantity = (int) tableModel.getValueAt(row, 2);
                for (Medicine medicine : arr) {
                    if (medicine.getName().equals(medicineName)) {
                        double rate = medicine.getRate();
                        double amount = quantity * rate;
                        billContent.append(medicineName + "\t" + quantity + "\t" + rate + "\t" + amount + "\n");
                        totalAmount += amount;
                        // Update the quantity in the arr array
                        medicine.setQuantity(medicine.getQuantity() - quantity);
                    }
                }
            }

            billContent.append("------------------------------------------------------------\n");
            updateTotalAmount();
            billContent.append("Payable Amount: \tBDT " + totalAmount+" TK");

            // Print the bill to the console
            System.out.println(billContent.toString());

            // Write the bill content to the random bill file
            try (FileWriter writer = new FileWriter(randomBillFileName)) {
                writer.write(billContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Update the total label
            totalLabel.setText("Total Amount: $" + totalAmount);

            // Update the medlist.txt file
            try (FileWriter medlistWriter = new FileWriter("medlist.txt")) {
                for (Medicine medicine : arr) {
                    medlistWriter.write(medicine.getName() + "\t" + medicine.getQuantity() + "\t" + medicine.getRate() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Dispose of the frame
        f.dispose();
    }

    private void updateTotalAmount() {
        totalAmount = 0.0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            int quantity = (int) tableModel.getValueAt(row, 2);
            double rate = (double) tableModel.getValueAt(row, 1);
            totalAmount += quantity * rate;
        }
        totalLabel.setText("Total Amount: BDT " + totalAmount);
    }
}