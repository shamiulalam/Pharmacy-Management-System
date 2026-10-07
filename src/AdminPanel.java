import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class AdminPanel {
    JFrame adminFrame;
    JTextField medicineNameField, quantityField, rateField;
    Medicine[] medicines;

    public AdminPanel() {
        adminFrame = new JFrame("Admin Panel");

        JLabel nameLabel = new JLabel("Medicine Name:");
        nameLabel.setBounds(50, 30, 120, 30);
        adminFrame.add(nameLabel);

        medicineNameField = new JTextField();
        medicineNameField.setBounds(200, 30, 200, 30);
        adminFrame.add(medicineNameField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 80, 120, 30);
        adminFrame.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(200, 80, 200, 30);
        adminFrame.add(quantityField);

        JLabel rateLabel = new JLabel("Rate:");
        rateLabel.setBounds(50, 130, 120, 30);
        adminFrame.add(rateLabel);

        rateField = new JTextField();
        rateField.setBounds(200, 130, 200, 30);
        adminFrame.add(rateField);

        JButton addMedicineButton = new JButton("Add Medicine");
        addMedicineButton.setBounds(50, 180, 150, 30);
        adminFrame.add(addMedicineButton);

        JButton deleteMedicineButton = new JButton("Delete Medicine");
        deleteMedicineButton.setBounds(220, 180, 150, 30);
        adminFrame.add(deleteMedicineButton);

        JButton updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.setBounds(50, 230, 150, 30);
        adminFrame.add(updateQuantityButton);

        JButton updateRateButton = new JButton("Update Rate");
        updateRateButton.setBounds(220, 230, 150, 30);
        adminFrame.add(updateRateButton);

        addMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = medicineNameField.getText();
                String quantityStr = quantityField.getText();
                String rateStr = rateField.getText();

                if (!name.isEmpty() && !quantityStr.isEmpty() && !rateStr.isEmpty()) {
                    int quantity = Integer.parseInt(quantityStr);
                    double rate = Double.parseDouble(rateStr);
                    addMedicine(name, quantity, rate);
                    clearFields();
                }
            }
        });

        deleteMedicineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = medicineNameField.getText();
                if (!name.isEmpty()) {
                    deleteMedicine(name);
                    clearFields();
                }
            }
        });

        updateQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = medicineNameField.getText();
                String quantityStr = quantityField.getText();
                if (!name.isEmpty() && !quantityStr.isEmpty()) {
                    int quantity = Integer.parseInt(quantityStr);
                    updateQuantity(name, quantity);
                    clearFields();
                }
            }
        });

        updateRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = medicineNameField.getText();
                String rateStr = rateField.getText();
                if (!name.isEmpty() && !rateStr.isEmpty()) {
                    double rate = Double.parseDouble(rateStr);
                    updateRate(name, rate);
                    clearFields();
                }
            }
        });

        adminFrame.setSize(450, 350);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setLayout(null);
        adminFrame.setVisible(true);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addMedicine(String name, int quantity, double rate) {
        try (FileWriter writer = new FileWriter("medlist.txt", true)) {
            writer.write(name + "\t" + quantity + "\t" + rate + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteMedicine(String name) {
        // Load the existing medicines from medlist
        loadMedicines();

        boolean found = false;
        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i].getName().equals(name)) {
                medicines[i] = null; // Mark for deletion
                found = true;
            }
        }

        if (found) {
            // Write the updated medlist
            try (FileWriter writer = new FileWriter("medlist.txt")) {
                for (Medicine medicine : medicines) {
                    if (medicine != null) {
                        writer.write(medicine.getName() + "\t" + medicine.getQuantity() + "\t" + medicine.getRate() + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateQuantity(String name, int quantity) {
        // Load the existing medicines from medlist
        loadMedicines();

        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i].getName().equals(name)) {
                medicines[i].setQuantity(quantity);
                break;
            }
        }

        // Write the updated medlist
        try (FileWriter writer = new FileWriter("medlist.txt")) {
            for (Medicine medicine : medicines) {
                writer.write(medicine.getName() + "\t" + medicine.getQuantity() + "\t" + medicine.getRate() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRate(String name, double rate) {
        // Load the existing medicines from medlist
        loadMedicines();

        for (int i = 0; i < medicines.length; i++) {
            if (medicines[i].getName().equals(name)) {
                medicines[i].setRate(rate);
                break;
            }
        }

        // Write the updated medlist
        try (FileWriter writer = new FileWriter("medlist.txt")) {
            for (Medicine medicine : medicines) {
                writer.write(medicine.getName() + "\t" + medicine.getQuantity() + "\t" + medicine.getRate() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMedicines() {
        try {
            File f5 = new File("medlist.txt");
            Scanner s = new Scanner(f5);
            int med = 0;
            while (s.hasNextLine()) {
                s.nextLine();
                med++;
            }
            s.close();

            medicines = new Medicine[med];
            Scanner s1 = new Scanner(new File("medlist.txt"));
            for (int i = 0; i < med; i++) {
                String line = s1.nextLine();
                String[] line_a = line.split("\t");
                medicines[i] = new Medicine(line_a[0], Integer.valueOf(line_a[1]), Double.valueOf(line_a[2]));
            }
            s1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        medicineNameField.setText("");

    }
}