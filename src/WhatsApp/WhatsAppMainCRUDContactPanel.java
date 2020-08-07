package WhatsApp;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import static WhatsApp.Account.allAccounts;
import WhatsApp.Interactions.Contact;
import WhatsApp.Interactions.InvalidPhoneNumberException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static WhatsApp.WhatsAppSetup.testAccount;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import static WhatsApp.WhatsApp.changeFlag;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.text.TextAction;

public class WhatsAppMainCRUDContactPanel extends JPanel {

    private JPanel p1;
    private JPanel p2;
    private JList<Contact> contactList;
    private JScrollPane contactListScroll;
    private JComboBox comboBoxBlock;
    private JButton addContactBtn;
    private JButton editContactBtn;
    private JButton deleteContactBtn;
    private JButton clearAllBtn;
    private JTextField phoneNumberField;
    private JTextField nameField;
    private JTextField aboutField;
    private JTextField statusField;
    private JTextField lastSeenField;
    private DefaultListModel<Contact> contactListModel;
    private static final String ComboBoxContent[] = {"NOT BLOCKED", "BLOCKED"};
    private JPanel p3;
    private JPopupMenu listPopup;
    private JMenuItem deleteItem;
    private JMenuItem editItem;


    public WhatsAppMainCRUDContactPanel() {

        initComponents();

        contactList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    setFields(1);
                    Contact cur = (Contact) contactList.getSelectedValue();
                    if (cur != null) {
                        phoneNumberField.setText(cur.getProfile().getPhoneNumber());
                        nameField.setText(cur.getProfile().getName());
                        aboutField.setText(cur.getProfile().getAbout());
                        statusField.setText(cur.getProfile().getStatus());
                        if (cur.isBlocked()) {
                            comboBoxBlock.setSelectedIndex(1);
                        } else {
                            comboBoxBlock.setSelectedIndex(0);
                        }
                        lastSeenField.setText(cur.getProfile().getLastSeen().toString());
                    }
                }
            }
        });

        clearAllBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freeFields();
                setFields(0);
            }
        });

        addContactBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!allFieldsAreEditable()) {
                    JOptionPane.showMessageDialog(null, "You must clear all first!",
                            "Add Contact", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!allImportantFieldsAreFull()) {
                    JOptionPane.showMessageDialog(null, "Fill all required fields (phone, name)!",
                            "Lack of info...", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Profile new_prof = new Profile(phoneNumberField.getText(), nameField.getText());
                    new_prof.setAbout(aboutField.getText());
                    new_prof.setStatus(statusField.getText());
                    Account new_acc = new Account(new_prof);
                    Contact new_con = new Contact(new_prof);
                    if (comboBoxBlock.getSelectedItem().equals("BLOCKED")) {
                        new_con.block();
                    }
                    allAccounts.put(new_prof.getPhoneNumber(), new_acc);
                    testAccount.addContact(new_con);
                    freeFields();
                    changeFlag = true;
                    JOptionPane.showMessageDialog(null, "Contact added successfully!",
                            "Add Contact", JOptionPane.INFORMATION_MESSAGE);
                } catch (AlreadyExistingContactException ex) {
                    JOptionPane.showMessageDialog(null, "Contact with that phone number already exists!",
                            "Phone number exists...", JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (InvalidPhoneNumberException ex) {
                    JOptionPane.showMessageDialog(null, "The provided phone number is invalid!",
                            "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        editContactBtn.addActionListener(new EditContact());

        deleteContactBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contactList.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(null, "Select a contact to delete them!",
                            "No Selected Contact", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Contact cur = (Contact) contactList.getSelectedValue();
                testAccount.removeContact(cur);
                freeFields();
                changeFlag = true;
                JOptionPane.showMessageDialog(null, "Contact deleted successfully!",
                        "Delete Contact", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    private void initComponents() {
        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        contactList.setVisibleRowCount(20);
        refreshList();

        contactListScroll = new JScrollPane(contactList);
        comboBoxBlock = new JComboBox<>(ComboBoxContent);
        addContactBtn = new JButton("Add Contact",
                new ImageIcon(this.getClass().getResource("/icons/support-man.png")));
        editContactBtn = new JButton("Edit Contact",
                new ImageIcon(this.getClass().getResource("/icons/feedback.png")));
        deleteContactBtn = new JButton("Delete Contact",
                new ImageIcon(this.getClass().getResource("/icons/keypad.png")));
        clearAllBtn = new JButton("Clear All",
                new ImageIcon(this.getClass().getResource("/icons/click-to-call.png")));

        addContactBtn.setMaximumSize(new Dimension(200, 35));
        editContactBtn.setMaximumSize(new Dimension(200, 35));
        deleteContactBtn.setMaximumSize(new Dimension(200, 35));
        clearAllBtn.setMaximumSize(new Dimension(200, 35));

        phoneNumberField = new JTextField(15);
        nameField = new JTextField(15);
        aboutField = new JTextField(15);
        statusField = new JTextField(15);
        lastSeenField = new JTextField(15);
        phoneNumberField.setEditable(false);
        nameField.setEditable(false);
        aboutField.setEditable(false);
        statusField.setEditable(false);
        lastSeenField.setEditable(false);
        comboBoxBlock.setEnabled(false);

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(addContactBtn);
        p1.add(Box.createVerticalStrut(10));
        p1.add(editContactBtn);
        p1.add(Box.createVerticalStrut(10));
        p1.add(deleteContactBtn);
        p1.add(Box.createVerticalStrut(10));
        p1.add(clearAllBtn);
        p1.add(Box.createVerticalStrut(10));

        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(comboBoxBlock);
        p2.add(Box.createVerticalStrut(10));
        phoneNumberField.setBorder(BorderFactory.createTitledBorder("Phone Number"));
        p2.add(phoneNumberField);
        p2.add(Box.createVerticalStrut(10));
        nameField.setBorder(BorderFactory.createTitledBorder("Full Name"));
        p2.add(nameField);
        p2.add(Box.createVerticalStrut(10));
        aboutField.setBorder(BorderFactory.createTitledBorder("About"));
        p2.add(aboutField);
        p2.add(Box.createVerticalStrut(10));
        statusField.setBorder(BorderFactory.createTitledBorder("Status"));
        p2.add(statusField);
        p2.add(Box.createVerticalStrut(10));
        lastSeenField.setBorder(BorderFactory.createTitledBorder("Last Seen"));
        p2.add(lastSeenField);
        p2.add(Box.createVerticalStrut(10));

        listPopup = new JPopupMenu();
        deleteItem = new JMenuItem("Delete contact");
        editItem = new JMenuItem("Edit contact");

        contactList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    contactList.setSelectedIndex(getRow(e.getPoint()));
                }
                if (e.getClickCount() == 2) {
                    editContactBtn.doClick();
                }
            }

            private int getRow(Point point) {
                return contactList.locationToIndex(point);
            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContactBtn.doClick();
            }
        });
        editItem.addActionListener(new EditContact());

        listPopup.add(deleteItem);
        listPopup.add(editItem);

        contactList.setComponentPopupMenu(listPopup);

        contactListScroll.setPreferredSize(new Dimension(150, 230));
        contactListScroll.setBorder(BorderFactory.createTitledBorder("All Contacts"));
        p3.add(contactListScroll);

        p1.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(true);

        add(p3);
        add(Box.createHorizontalStrut(10));
        add(p2);
        add(Box.createHorizontalStrut(10));
        add(p1);

        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setVisible(true);
    }

    private boolean allImportantFieldsAreFull() {
        return !nameField.getText().isEmpty()
                && !phoneNumberField.getText().isEmpty();
    }

    private void setFields(int n) {
        if (n == 0) {
            phoneNumberField.setEditable(true);
            nameField.setEditable(true);
            aboutField.setEditable(true);
            statusField.setEditable(true);
            comboBoxBlock.setEnabled(true);
        } else if (n == 1) {
            phoneNumberField.setEditable(false);
            nameField.setEditable(false);
            aboutField.setEditable(false);
            statusField.setEditable(false);
            comboBoxBlock.setEnabled(false);
        }
        comboBoxBlock.setSelectedItem("NOT BLOCKED");
    }

    private void freeFields() {
        phoneNumberField.setText("");
        nameField.setText("");
        aboutField.setText("");
        statusField.setText("");
        lastSeenField.setText("");
        comboBoxBlock.setSelectedItem("NOT BLOCKED");
        comboBoxBlock.setEnabled(false);
        refreshList();
    }

    private boolean allFieldsAreEditable() {
        return phoneNumberField.isEditable()
                && nameField.isEditable()
                && aboutField.isEditable()
                && statusField.isEditable();
    }

    private void refreshList() {
        Collections.sort(testAccount.getAllContacts());
        contactListModel.clear();
        Iterator iter = testAccount.getAllContacts().iterator();
        while (iter.hasNext()) {
            contactListModel.addElement((Contact) iter.next());
        }
    }

    private class EditContact implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (contactList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "Select a contact to edit their info!",
                        "No Selected Contact", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Contact cur = (Contact) contactList.getSelectedValue();
            WhatsAppMainEditContactJDialog dialogBox = new WhatsAppMainEditContactJDialog(cur);
            freeFields();
        }
    }

}
