/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhatsApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Hamza Rehioui
 */
public class WhatsApp extends JFrame {

    public static JFrame mainFrame;
    public static boolean changeFlag;
    private JTabbedPane jTab;
    private ImageIcon contactViewIcon;
    private ImageIcon contactSearchIcon;

    public static void main(String args[]) throws Exception {
        new WhatsApp();
    }

    public WhatsApp() throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ex) {
        }

        WhatsAppSetup.setUp();
        WhatsAppSetup tool = new WhatsAppSetup();

        changeFlag = false;

        setTitle("WhatsApp Application");
        setLayout(new BorderLayout(8, 6));
        jTab = new JTabbedPane();
        contactViewIcon = new ImageIcon(tool.getURL("/icons/my-invoices.png"));
        contactSearchIcon = new ImageIcon(tool.getURL("/icons/my-profile.png"));
        setIconImage(new ImageIcon(tool.getURL("/icons/icon.png")).getImage());
        jTab.addTab("Contact View", contactViewIcon, new WhatsAppMainCRUDContactPanel());
        jTab.addTab("Contact Search", contactSearchIcon, new WhatsAppMainContactSearchPanel());
        add(jTab);
        setResizable(false);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (changeFlag == true) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Do you want the changes made to the contacts?",
                            "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        WhatsAppSetup.saveAccounts();
                        System.exit(0);
                    }
                    if (result == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                    if (result == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        });

        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WhatsAppSetup.saveAccounts();
                changeFlag = false;
                JOptionPane.showMessageDialog(null, "File saved!",
                        "Save", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This is a mini-WhatsApp application\n"
                        + "with contact displaying, editing, and look-up\n"
                        + "features to show our understanding of GUIs\n"
                        + "using Swing library in Java.\n",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutItem);

        menu.add(fileMenu);
        menu.add(helpMenu);

        setJMenuBar(menu);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        mainFrame = this;
    }

}
