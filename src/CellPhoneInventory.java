
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CellPhoneInventory extends JFrame {
    private JLabel labelModel, labelManufacturer, labelRetailPrice;
    private JTextField jTextFieldModel, jTextFieldManufacturer, jTextFieldRetailPrice;
    private JButton jButtonAdd, jButtonShow;
    private ArrayList<CellPhone> phoneArrayList = new ArrayList<>();

    public CellPhoneInventory() {
        setTitle("Cellphone Inventory");
        setLayout(new GridLayout(4, 2));

        labelModel = new JLabel("Model:");
        labelManufacturer = new JLabel("Manufacturer:");
        labelRetailPrice = new JLabel("Retail Price:");

        jTextFieldModel = new JTextField(15);
        jTextFieldManufacturer = new JTextField(15);
        jTextFieldRetailPrice = new JTextField(15);

        jButtonAdd = new JButton("Add");
        jButtonShow = new JButton("Show Inventory");

        add(labelModel);
        add(jTextFieldModel);
        add(labelManufacturer);
        add(jTextFieldManufacturer);
        add(labelRetailPrice);
        add(jTextFieldRetailPrice);
        add(jButtonAdd);
        add(jButtonShow);

        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String model = jTextFieldModel.getText();
                    String manufacturer = jTextFieldManufacturer.getText();
                    double price = Double.parseDouble(jTextFieldRetailPrice.getText());

                    CellPhone phone = new CellPhone(model, manufacturer, price);
                    phoneArrayList.add(phone);

                    JOptionPane.showMessageDialog(null, "Phone added successfully!");
                } catch (InvalidModelException | InvalidManufacturerException | InvalidRetailPriceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price. Please enter a valid number.");
                }
            }
        });

        jButtonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder inventory = new StringBuilder("Inventory: ");
                for (CellPhone phone : phoneArrayList) {
                    inventory.append(phone.toString()).append(" ");
                }
                JOptionPane.showMessageDialog(null, inventory.toString());
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}
