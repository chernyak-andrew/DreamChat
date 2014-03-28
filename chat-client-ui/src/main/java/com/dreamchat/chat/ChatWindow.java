package com.dreamchat.chat;

import java.awt.event.ActionEvent;
import java.util.List;

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
    private int indexOfLastPrintedMessage=0;

    public ChatWindow(Client client) {
        super();
        setTitle("Chat");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initControls();
        this.client = client;
        sendRequestForMessages();
        getMessagesFromServerAndPrint();
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
                SendedMessage sendedMessage = new SendedMessage(nameInputField.getText(), client.getLogin());
                client.sendMessage(sendedMessage);
                nameInputField.setText("");
                chatOutput.append(sendedMessage.getDateCreated()+"   "+ sendedMessage.getUserName() +":    "+ sendedMessage.getText()+"\n");
            }
        });
    }

    private void sendRequestForMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GetMessages request = new GetMessages(indexOfLastPrintedMessage);
                client.sendMessage(request);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void getMessagesFromServerAndPrint(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReturnedMessages returnedMessages = client.getReturnedMessages();
                List<SendedMessage> listOfMessage = returnedMessages.getMessages();
                printMessages(listOfMessage);
            }
        }).start();
    }

    private void printMessages(List<SendedMessage> listOfMessages){
        for (SendedMessage sm: listOfMessages){
            chatOutput.append(sm.getDateCreated()+"   "+ sm.getUserName() +":    "+ sm.getText()+"\n");
        }
    }

}
