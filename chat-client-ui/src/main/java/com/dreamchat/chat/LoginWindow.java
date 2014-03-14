package com.dreamchat.chat;

import java.awt.event.ActionEvent;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
/**
 * Hello world!
 *
 */
public class LoginWindow extends JFrame
{
    private final JTextField loginField = new JTextField("", 5);
    private final JTextField passField = new JTextField("", 5);
    private final JLabel EnterTextLabel = new JLabel("Set Login:");
    private final JLabel ChatLabel = new JLabel("Set Password:");
    private final JButton loginButton = new JButton("login");

    public LoginWindow() {
        super();
        setTitle("Login");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initControls();
    }

    private void initControls() {
        JPanel panel = (JPanel) this.getContentPane();
        MigLayout layout = new MigLayout("fill", "[]15[]", "");
        panel.setLayout(layout);

        EnterTextLabel.setLabelFor(loginField);
        panel.add(EnterTextLabel, "cell 0 0, align right");
        panel.add(loginField, "cell 1 0, align left");

        ChatLabel.setLabelFor(passField);
        panel.add(ChatLabel, "cell 0 1, align right");
        panel.add(passField, "cell 1 1, align left");


        panel.add(loginButton, "cell 0 2, align right");
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client("localhost", 1234, loginField.getText());
                MyWindow window = new MyWindow(client);
                window.setVisible(true);
                LoginWindow.this.setVisible(false);
//                loginwindow.dispose();
            }
        });
    }

    public static void main(String[] args )
    {
        LoginWindow loginwindow = new LoginWindow();
        loginwindow.setVisible(true);
    }
}
