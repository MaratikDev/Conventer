package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Front extends JFrame  implements ActionListener {


    Bd operations = new Bd();
    Back back = new Back();
    String[] Valutes = {"RUB", "USD", "CNY"};
    JComboBox<String>  valuteFrom;
    JComboBox<String>  valuteTo;


    JTextField nameField ;
    JTextField textCountFrom ;


    JButton submitNameButton;
    JButton submitTransactionButton;
    JButton refreshCourse;

    JLabel textCountTo ;
    JLabel enterNameLabel;
    JLabel transactionLabel;
    JLabel from;
    JLabel to;

    JLabel RUBLabel;
    JLabel RUBCount;


    JLabel USDLabel;
    JLabel USDCount;


    JLabel CNYLabel;
    JLabel CNYCount;




    JPanel panelNames;
    JPanel panelTransaction;
    JPanel panelWallet;


    Front() throws Exception {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                operations.closeConnection();
            }
        });
        operations.openConnection();
        operations.makeTableOperations();




        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250,450));
        nameField.setFont(new Font("Calibri",Font.PLAIN,50));
        nameField.setBounds(10,140,500,60);

        textCountFrom = new JTextField();
        textCountFrom.setPreferredSize(new Dimension(250,450));
        textCountFrom.setFont(new Font("Calibri",Font.PLAIN,50));
        textCountFrom.setBounds(10,160,500,70);

        Border border = BorderFactory.createLineBorder(Color.black,1);
        textCountTo = new JLabel();
        textCountTo.setText("0");
        textCountTo.setPreferredSize(new Dimension(250,450));
        textCountTo.setFont(new Font("Calibri",Font.PLAIN,50));
        textCountTo.setBounds(10,460,500,70);
        textCountTo.setBorder(border);

        valuteFrom = new JComboBox<>(Valutes);
        valuteFrom.setBounds(10,230,300,70);
        valuteFrom.setFont(new Font("Calibri",Font.PLAIN,30));
        valuteFrom.addActionListener(this);

        valuteTo = new JComboBox<>(Valutes);
        valuteTo.setBounds(10,530,300,70);
        valuteTo.setFont(new Font("Calibri",Font.PLAIN,30));
        valuteTo.addActionListener(this);


        submitNameButton = new JButton();
        submitNameButton.setText("Submit");
        submitNameButton.setFont(new Font("Calibri",Font.PLAIN,50));
        submitNameButton.setBounds(400,220,270,55);
        submitNameButton.addActionListener(this);

        submitTransactionButton = new JButton();
        submitTransactionButton.setText("Submit");
        submitTransactionButton.setEnabled(false);
        submitTransactionButton.setFont(new Font("Calibri",Font.PLAIN,50));
        submitTransactionButton.setBounds(550,650,270,55);
        submitTransactionButton.addActionListener(this);


        refreshCourse = new JButton();
        refreshCourse.setText("Обновить данные");
        refreshCourse.setEnabled(false);
        refreshCourse.setFont(new Font("Calibri",Font.PLAIN,20));
        refreshCourse.setBounds(510,170,250,50);
        refreshCourse.addActionListener(this);


        from = new JLabel();
        from.setText("Перевести из");
        from.setFont(new Font("Calibri",Font.PLAIN,30));
        from.setBounds(20,120,500,50);

        to = new JLabel();
        to.setText("Перевести в");
        to.setFont(new Font("Calibri",Font.PLAIN,30));
        to.setBounds(20,420,500,50);

        enterNameLabel = new JLabel();
        enterNameLabel.setText("Your Name");
        enterNameLabel.setBounds(20,20,1000,100);
        enterNameLabel.setFont(new Font("Calibri",Font.PLAIN,40));

        transactionLabel = new JLabel();
        transactionLabel.setText("Перевести деньги");
        transactionLabel.setBounds(20,20,1000,100);
        transactionLabel.setFont(new Font("Calibri",Font.PLAIN,50));



        RUBLabel = new JLabel();
        RUBLabel.setText("RUB");
        RUBLabel.setBounds(60,50,1000,100);
        RUBLabel.setFont(new Font("Calibri",Font.PLAIN,70));

        RUBCount = new JLabel();
        RUBCount.setText(back.getCurrentRUB().toString());
        RUBCount.setBounds(230,50,1000,100);
        RUBCount.setFont(new Font("Calibri",Font.PLAIN,70));



        USDLabel = new JLabel();
        USDLabel.setText("USD");
        USDLabel.setBounds(60,150,1000,100);
        USDLabel.setFont(new Font("Calibri",Font.PLAIN,70));

        USDCount = new JLabel();
        USDCount.setText(back.getCurrentUSD().toString());
        USDCount.setBounds(230,150,1000,100);
        USDCount.setFont(new Font("Calibri",Font.PLAIN,70));

        CNYLabel = new JLabel();
        CNYLabel.setText("CNY");
        CNYLabel.setBounds(60,250,1000,100);
        CNYLabel.setFont(new Font("Calibri",Font.PLAIN,70));

        CNYCount = new JLabel();
        CNYCount.setText(back.getCurrentCNY().toString());
        CNYCount.setBounds(230,250,1000,100);
        CNYCount.setFont(new Font("Calibri",Font.PLAIN,70));







        panelWallet = new JPanel();
        panelWallet.setBackground(Color.LIGHT_GRAY);
        panelWallet.setBounds(850,0,900,1070);
        panelWallet.setLayout(null);


        panelTransaction = new JPanel();
        panelTransaction.setBackground(Color.WHITE);
        panelTransaction.setBounds(0,300,850,770);
        panelTransaction.setLayout(null);


        panelNames = new JPanel();
        panelNames.setBackground(Color.gray);
        panelNames.setBounds(0,0,850,300);
        panelNames.setLayout(null);






        this.setTitle("Конвертер валют");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(500,200,1750,1070);
        this.setVisible(true);
        this.setResizable(false);




        this.add(panelNames);
        this.add(panelTransaction);
        this.add(panelWallet);

        panelNames.add(enterNameLabel);
        panelNames.add(nameField);
        panelNames.add(submitNameButton);


        panelTransaction.add(transactionLabel);
        panelTransaction.add(from);
        panelTransaction.add(valuteFrom);
        panelTransaction.add(textCountFrom);
        panelTransaction.add(refreshCourse);
        panelTransaction.add(to);
        panelTransaction.add(valuteTo);
        panelTransaction.add(textCountTo);
        panelTransaction.add(submitTransactionButton);

        panelWallet.add(RUBLabel);
        panelWallet.add(RUBCount);
        panelWallet.add(USDLabel);
        panelWallet.add(USDCount);
        panelWallet.add(CNYLabel);
        panelWallet.add(CNYCount);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== submitNameButton){
            back.submitName(nameField.getText());
            RUBCount.setText(back.RefrehRUB());
            USDCount.setText(back.RefrehUSD());
            CNYCount.setText(back.RefrehCNY());
            submitTransactionButton.setEnabled(false);
            if(back.getName().equals("")==false) {
                refreshCourse.setEnabled(true);
            }
        }
        if (e.getSource()== submitTransactionButton) {
            back.transaction();
            RUBCount.setText(back.RefrehRUB());
            USDCount.setText(back.RefrehUSD());
            CNYCount.setText(back.RefrehCNY());
            operations.insertNewOperation(back.getName(),back.getConvertfrom(),back.getConvertto(),back.getCountfrom(),back.getCountto());
            operations.showData();

        }
        if (e.getSource()==valuteFrom) {
            back.setValuteFrom((String) valuteFrom.getSelectedItem());
            submitTransactionButton.setEnabled(false);
        }
        if (e.getSource()==valuteTo) {
            back.setValuteTo((String) valuteTo.getSelectedItem());
            submitTransactionButton.setEnabled(false);
        }
        if (e.getSource()==refreshCourse) {
            textCountTo.setText(back.setCount(textCountFrom.getText()));
            if(back.getName().equals("")==false) {
                submitTransactionButton.setEnabled(true);
            }
        }

    }




}
