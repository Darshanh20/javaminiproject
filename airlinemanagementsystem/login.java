package airlinemanagementsystem;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;

    public login() {
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 60, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 60, 150, 20);
        add(tfpassword);

        submit = new JButton("SUBMIT");
        submit.setBounds(120, 100, 100, 20);
        submit.addActionListener(this);
        add(submit);

        reset = new JButton("RESET");
        reset.setBounds(120, 140, 100, 20);
        reset.addActionListener(this);
        add(reset);

        close = new JButton("CLOSE");
        close.setBounds(120, 180, 100, 20);
        close.addActionListener(this);
        add(close);

        setLocation(800, 250);
        setSize(400, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        if(a.getSource() == submit){
            String username = tfusername.getText();
            String password = tfpassword.getText();

            try{
                conn c = new conn();
                String query = "select * from login where username ='" + username +"'and password ='" + password+"'";
                ResultSet rs = c.s.executeQuery(query);

                if(rs.next()){
                    new home();
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "INVALID! Username or password");
                    setVisible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if (a.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        } else if (a.getSource() == close) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
