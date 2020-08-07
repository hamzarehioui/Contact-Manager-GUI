package WhatsApp;

import WhatsApp.Interactions.Contact;
import WhatsApp.Interactions.InvalidPhoneNumberException;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static WhatsApp.WhatsAppSetup.testAccount;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static WhatsApp.WhatsApp.*;

public class WhatsAppMainEditContactJDialog extends JDialog {

    private JPanel p;
    private JTextField phoneNumberField;
    private JTextField nameField;
    private JTextField aboutField;
    private JTextField statusField;
    private JTextField lastSeenField;
    private JComboBox<String> comboBoxBlock;
    private JButton saveBtn;
    private JButton cancelBtn;
    private static final String ComboBoxContent[] = {"NOT BLOCKED", "BLOCKED"};

    public WhatsAppMainEditContactJDialog(Contact contact) {
        super(mainFrame, "Edit Contact");
        setSize(new Dimension(250, 500));
        setMaximumSize(new Dimension(250, 500));
        setPreferredSize(new Dimension(250, 500));
        setMinimumSize(new Dimension(250, 500));
        setResizable(false);
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));

        phoneNumberField = new JTextField(15);
        nameField = new JTextField(15);
        aboutField = new JTextField(15);
        statusField = new JTextField(15);
        lastSeenField = new JTextField(15);
        comboBoxBlock = new JComboBox<String>(ComboBoxContent);
        saveBtn = new JButton("Save Changes",
                new ImageIcon(this.getClass().getResource("/icons/feedback.png")));
        cancelBtn = new JButton("Cancel",
                new ImageIcon(this.getClass().getResource("/icons/my-invoices.png")));

        phoneNumberField.setMaximumSize(new Dimension(200, 45));
        nameField.setMaximumSize(new Dimension(200, 45));
        aboutField.setMaximumSize(new Dimension(200, 45));
        statusField.setMaximumSize(new Dimension(200, 45));
        lastSeenField.setMaximumSize(new Dimension(200, 45));
        comboBoxBlock.setMaximumSize(new Dimension(200, 45));
        saveBtn.setMaximumSize(new Dimension(200, 45));
        cancelBtn.setMaximumSize(new Dimension(200, 45));

        p.add(Box.createVerticalStrut(10));
        if (contact.isBlocked()) {
            comboBoxBlock.setSelectedItem("BLOCKED");
        } else {
            comboBoxBlock.setSelectedItem("NOT BLOCKED");
        }
        p.add(comboBoxBlock);
        p.add(Box.createVerticalStrut(10));
        phoneNumberField.setBorder(BorderFactory.createTitledBorder("Phone Number"));
        phoneNumberField.setText(contact.getProfile().getPhoneNumber());
        p.add(phoneNumberField);
        p.add(Box.createVerticalStrut(10));
        nameField.setBorder(BorderFactory.createTitledBorder("Full Name"));
        nameField.setText(contact.getProfile().getName());
        p.add(nameField);
        p.add(Box.createVerticalStrut(10));
        aboutField.setBorder(BorderFactory.createTitledBorder("About"));
        aboutField.setText(contact.getProfile().getAbout());
        p.add(aboutField);
        p.add(Box.createVerticalStrut(10));
        statusField.setBorder(BorderFactory.createTitledBorder("Status"));
        statusField.setText(contact.getProfile().getStatus());
        p.add(statusField);
        p.add(Box.createVerticalStrut(10));
        lastSeenField.setBorder(BorderFactory.createTitledBorder("Last Seen"));
        lastSeenField.setText(contact.getProfile().getLastSeen().toString());
        lastSeenField.setEditable(false);
        p.add(lastSeenField);
        p.add(Box.createVerticalStrut(10));
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(saveBtn);
        p.add(Box.createVerticalStrut(10));
        p.add(cancelBtn);
        cancelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(Box.createVerticalStrut(10));

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!allImportantFieldsAreFull()) {
                    JOptionPane.showMessageDialog(null, "Fill the important fields!",
                            "Lack of info...", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!WhatsAppSetup.isValidPhoneNumber(phoneNumberField.getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number!",
                            "Phone Number", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if (testAccount.getAllContacts().contains(new Contact(new Profile(phoneNumberField.getText(), "")))
                            && !contact.getProfile().getPhoneNumber().equals(phoneNumberField.getText())) {
                        JOptionPane.showMessageDialog(null, "Contact with that phone number already exists!",
                                "Phone number exists...", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (InvalidPhoneNumberException ex) {
                }
                contact.getProfile().setName(nameField.getText());
                contact.getProfile().setAbout(aboutField.getText());
                contact.getProfile().setPhoneNumber(phoneNumberField.getText());
                contact.getProfile().setStatus(statusField.getText());
                if (comboBoxBlock.getSelectedItem().equals("BLOCKED")) {
                    contact.block();
                } else {
                    contact.unblock();
                }
                JOptionPane.showMessageDialog(null, "Changes Saved!",
                        "Edit Contact", JOptionPane.INFORMATION_MESSAGE);
                changeFlag = true;
                dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "All changes made are not saved!",
                        "Edit Contact", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        phoneNumberField.addActionListener(new ClickSearch());
        nameField.addActionListener(new ClickSearch());
        aboutField.addActionListener(new ClickSearch());
        statusField.addActionListener(new ClickSearch());

        add(p);
        p.setVisible(true);
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);

        pack();

    }

    private boolean allImportantFieldsAreFull() {
        return !nameField.getText().isEmpty()
                && !phoneNumberField.getText().isEmpty();
    }

    private class ClickSearch implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            saveBtn.doClick();
        }
    }

}
