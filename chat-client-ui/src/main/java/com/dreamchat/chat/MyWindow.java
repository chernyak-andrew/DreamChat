package com.dreamchat.chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ClientInfoStatus;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
/**
 * Hello world!
 *
 */
public class MyWindow extends JFrame
{
    private Client client = null;
    private final JTextArea chatOutput = new JTextArea(30, 37);
    private final JTextField nameInput = new JTextField("", 15);
    private final JLabel EnterTextLabel = new JLabel("Enter Message:");
    private final JLabel ChatLabel = new JLabel("Chat:");
    private final JButton sendMessageButton = new JButton("Send");
    public String login;

    public MyWindow(Client client) {
        super();
        setTitle("Client");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initControls();
        this.client = client;
    }

    private void initControls() {
        JPanel panel = (JPanel) this.getContentPane();
        MigLayout layout = new MigLayout("fill", "[]15[]", "");
        panel.setLayout(layout);

        EnterTextLabel.setLabelFor(nameInput);
        panel.add(EnterTextLabel, "cell 0 0, align right");
        panel.add(nameInput, "cell 1 0, w 50%:65%:70%");

        ChatLabel.setLabelFor(chatOutput);
        chatOutput.setEditable(false);
//        JScrollPane editorScrollPane = new JScrollPane(chatOutput);
//        editorScrollPane.setVerticalScrollBarPolicy(
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        editorScrollPane.setPreferredSize(new Dimension(250, 145));
//        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        panel.add(ChatLabel, "cell 0 1, align right");
        panel.add(chatOutput, "cell 1 1, align left");


        panel.add(sendMessageButton);
        sendMessageButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessage(nameInput.getText());
                nameInput.setText("");
            }
        });
    }

//    public static void main( String[] args )
//    {
//        MyWindow window = new MyWindow();
//        window.setVisible(true);
//    }
}
