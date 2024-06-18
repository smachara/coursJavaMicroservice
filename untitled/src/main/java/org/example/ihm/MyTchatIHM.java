package org.example.ihm;


import org.example.api.TchatAPI;
import org.example.model.MessageBean;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;

import static org.example.api.MyAPI.sendPost;

public class MyTchatIHM extends JPanel {
    private JButton btSend;
    private JTextArea jtMessages;
    private JLabel lblTitle;
    private JTextArea jtSendMessage;
    private JButton btRefresh;
    private JScrollPane scrollMessages, scrollMessage;
    private JLabel lblError;
    private JProgressBar progressBar;

    private JLabel lblPseudo;
    private JTextField jtfPseudo;

    public MyTchatIHM() {

        //construct components
        lblTitle = new JLabel("Mon Super Tchat");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblTitle.setForeground(new Color(24, 134, 75));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 20, 792, 40);

        lblPseudo = new JLabel ("Pseudo : ");
        lblPseudo.setBounds (665, 0, 100, 25);

        jtfPseudo = new JTextField (5);
        jtfPseudo.setText("Toto");
        jtfPseudo.setBounds (665, 25, 100, 25);

        jtMessages = new JTextArea(1, 1);
        jtMessages.setEditable(false);
        scrollMessages = new JScrollPane(jtMessages);
        scrollMessages.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMessages.setBounds(30, 75, 735, 380);

        jtSendMessage = new JTextArea(1, 1);
        scrollMessage = new JScrollPane(jtSendMessage);
        scrollMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMessage.setBounds(30, 470, 600, 65);

        btRefresh = new JButton("Actualiser");
        btRefresh.addActionListener(this::clicOnBtRefresh);
        btRefresh.setBounds(645, 465, 100, 20);

        btSend = new JButton("Envoyer");
        btSend.addActionListener(this::clicOnBtSend);
        btSend.setBounds(645, 490, 100, 20);

        progressBar = new JProgressBar();
        progressBar.setBounds(645, 515, 100, 20);
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);

        lblError = new JLabel("");
        lblError.setForeground(new Color(184, 4, 35));
        lblError.setBounds(30, 540, 715, 25);

        //adjust size and set layout
        setPreferredSize(new Dimension(792, 574));
        setLayout(null);

        //add components
        add(lblTitle);
        add (lblPseudo);
        add (jtfPseudo);
        add(scrollMessage);
        add(scrollMessages);
        add(btSend);
        add(btRefresh);
        add(progressBar);
        add(lblError);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MyTchatIHM());
        frame.pack();
        frame.setVisible(true);
    }

    //CLic sur le bouton envoyer
    private void clicOnBtSend(ActionEvent actionEvent) {

        progressBar.setVisible(true);
        new Thread(() -> {
            try {
                lblError.setText("");
                if(jtfPseudo.getText().trim().length() < 3) {
                    throw new Exception("Il faut un pseudo avec au moins 3 caractères");
                }
                else if(jtSendMessage.getText().trim().length() < 3) {
                    throw new Exception("Il faut un message avec au moins 3 caractères");
                }

                //On envoie le message
                TchatAPI.sendMessage(new MessageBean(jtfPseudo.getText(), jtSendMessage.getText()));
                //Efface le message à envoyer
                jtSendMessage.setText("");
                //On actualise
                clicOnBtRefresh(null);
            }
            catch(Exception e){
                e.printStackTrace();
                lblError.setText("Une erreur est survenue : " + e.getMessage());
            }

            progressBar.setVisible(false);
        }).start();
    }

    //CLic sur le bouton Actualiser
    private void clicOnBtRefresh(ActionEvent actionEvent) {
        progressBar.setVisible(true);
        new Thread(() -> {

            try {
                lblError.setText("");

                String texte = "";
                for(MessageBean message : TchatAPI.getAllMessages()) {
                    texte += "-" + message.getPseudo() + " : " + message.getMessage() + "\n";
                }
                jtMessages.setText(texte);
            }
            catch(Exception e){
                e.printStackTrace();
                lblError.setText("Une erreur est survenue : " + e.getMessage());
            }

            progressBar.setVisible(false);
        }).start();
    }
}