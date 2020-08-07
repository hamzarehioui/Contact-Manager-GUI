package WhatsApp;

import WhatsApp.Interactions.Contact;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static WhatsApp.WhatsAppSetup.testAccount;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

public class WhatsAppContactSearchPanel extends JPanel {

    private JPanel p1;
    private JPanel p2;
    private JTable contactTable;
    private JScrollPane contactTableScroll;
    private JComboBox<String> comboBoxCriteria;
    private JButton searchContactBtn;
    private JTextField nameSearchField;
    private JTextField phoneSearchField;
    private DefaultTableModel contactTableModel;
    private static final String ComboBoxContent[] = {"Name", "Phone Number", "Both"};
    private static final String TableColumns[] = {"Name", "Phone Number", "About", "Status", "Last Seen", "Blocked"};
    private List<Contact> contactList;

    public WhatsAppContactSearchPanel() {
        initComponents();

        searchContactBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criterion = (String) comboBoxCriteria.getSelectedItem();
                if (criterion.equals("Name") && nameSearchField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill the required field (name)!",
                            "Lack of info...", JOptionPane.ERROR_MESSAGE);
                    removeRows();
                    return;
                } else if (criterion.equals("Phone Number") && phoneSearchField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill the required field (phone)!",
                            "Lack of info...", JOptionPane.ERROR_MESSAGE);
                    removeRows();
                    return;
                } else if (criterion.equals("Both") && (nameSearchField.getText().isEmpty()
                        || phoneSearchField.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Fill the required fields (name, phone)!",
                            "Lack of info...", JOptionPane.ERROR_MESSAGE);
                    removeRows();
                    return;
                }
                ArrayList<Contact> results = null;
                results = testAccount.findContact(criterion, phoneSearchField.getText(), nameSearchField.getText());
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No contact found!",
                            "No results...", JOptionPane.INFORMATION_MESSAGE);
                    removeRows();
                } else {
                    contactTableModel.setRowCount(0);
                    String str;
                    for (int i = 0; i < results.size(); i++) {
                        Profile cur = results.get(i).getProfile();
                        if (results.get(i).isBlocked()) {
                            str = "Yes";
                        } else {
                            str = "No";
                        }
                        contactTableModel.addRow(
                                new Object[]{cur.getName(), cur.getPhoneNumber(),
                                    cur.getAbout(), cur.getStatus(),
                                    cur.getLastSeen(), str});
                    }
                }
                contactTableModel.setRowCount(20);
            }
        });

        comboBoxCriteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxCriteria.getSelectedItem().equals("Name")) {
                    phoneSearchField.setText("");
                    phoneSearchField.setEditable(false);
                    nameSearchField.setText("");
                    nameSearchField.setEditable(true);
                } else if (comboBoxCriteria.getSelectedItem().equals("Phone Number")) {
                    phoneSearchField.setText("");
                    phoneSearchField.setEditable(true);
                    nameSearchField.setText("");
                    nameSearchField.setEditable(false);
                } else if (comboBoxCriteria.getSelectedItem().equals("Both")) {
                    phoneSearchField.setText("");
                    phoneSearchField.setEditable(true);
                    nameSearchField.setText("");
                    nameSearchField.setEditable(true);
                }

            }
        });

    }

    private void initComponents() {
        p1 = new JPanel();
        p2 = new JPanel();

        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        contactList = testAccount.getAllContacts();
        contactTableModel = new DefaultTableModel();
        comboBoxCriteria = new JComboBox<>(ComboBoxContent);
        contactTable = new JTable(contactTableModel);
        contactTable.setName("Search Results");
        contactTableModel.setRowCount(20);
        contactTableModel.setColumnIdentifiers(TableColumns);
        contactTableScroll = new JScrollPane(contactTable);
        contactTableScroll.setPreferredSize(new Dimension(400, 300));

        searchContactBtn = new JButton("Search",
                new ImageIcon(this.getClass().getResource("/icons/support-man.png")));

        nameSearchField = new JTextField(15);
        phoneSearchField = new JTextField(15);

        nameSearchField.setBorder(BorderFactory.createTitledBorder("Name"));
        phoneSearchField.setEditable(false);
        phoneSearchField.setBorder(BorderFactory.createTitledBorder("Phone Number"));
        comboBoxCriteria.setBorder(BorderFactory.createTitledBorder("Search Criteria"));

        nameSearchField.addActionListener(new ClickSearch());
        phoneSearchField.addActionListener(new ClickSearch());
        p1.add(nameSearchField);
        p1.add(Box.createVerticalStrut(10));
        p1.add(phoneSearchField);
        p1.add(Box.createVerticalStrut(10));
        p1.add(comboBoxCriteria);
        p1.add(Box.createVerticalStrut(10));
        p1.add(searchContactBtn);
        p2.add(contactTableScroll);

        add(p1);
        add(p2);

        setVisible(true);
    }

    private void removeRows() {
        contactTableModel.setRowCount(0);
        contactTableModel.setRowCount(20);
    }

    private class ClickSearch implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            searchContactBtn.doClick();
        }
    }

}
