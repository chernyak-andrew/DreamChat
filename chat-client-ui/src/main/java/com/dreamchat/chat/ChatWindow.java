package com.dreamchat.chat;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
/**
 * Hello world!
 *
 */
public class ChatWindow extends JFrame
{
    private Client client = null;
    private final JTextArea chatOutput = new JTextArea(30, 37);
    private final JTextField nameInputField = new JTextField("", 37);
    private final JLabel messageTextLabel = new JLabel("Enter Message:");
    private final JLabel chatLabel = new JLabel("Chat:");
    private final JButton sendMessageButton = new JButton("Send");
    public String login;

    public ChatWindow(Client client) {
        super();
        setTitle("Chat");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initControls();
        this.client = client;
    }

    private void initControls() {
        JPanel panel = (JPanel) this.getContentPane();
        MigLayout layout = new MigLayout("fill", "[]15[]", "");
        panel.setLayout(layout);

        messageTextLabel.setLabelFor(nameInputField);
        panel.add(messageTextLabel, "cell 0 0, align right");
        panel.add(nameInputField, "cell 1 0, align left");

        chatLabel.setLabelFor(chatOutput);
        chatOutput.setEditable(false);
//        JScrollPane editorScrollPane = new JScrollPane(chatOutput);
//        editorScrollPane.setVerticalScrollBarPolicy(
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        editorScrollPane.setPreferredSize(new Dimension(250, 145));
//        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        panel.add(chatLabel, "cell 0 1, align right");
        panel.add(chatOutput, "cell 1 1, align left");


        panel.add(sendMessageButton);
        sendMessageButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message outMessage = new Message(nameInputField.getText(), client.getLogin());
                client.sendMessage(outMessage);
                nameInputField.setText("");
                chatOutput.append(outMessage.getDateCreated()+"   "+ outMessage.getSender() +":    "+ outMessage.getMessage()+"\n");
            }
        });
    }

//    public static void main( String[] args )
//    {
//        Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").get2DigitYearStart();
//    }
}
