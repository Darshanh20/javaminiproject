package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class addcustomer extends JFrame implements ActionListener {

    JTextField uname , unationality , uaadhar , uphone ,uaddress,uemail;
    JRadioButton male ,female;
    public addcustomer(){
        setLocation(500, 150);
        setSize(800, 600);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);


        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(180, 20, 220, 20);
        heading.setFont(new Font("Tahoma" , Font.PLAIN , 22));
        add(heading);

        JLabel name = new JLabel("Customer Name");
        name.setBounds(60, 80, 220, 20);
        name.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(name);

        uname = new JTextField();
        uname.setBounds(220, 80, 150, 18);
        add(uname); 

        JLabel nationality = new JLabel("Nationality");
        nationality.setBounds(60, 140, 220, 20);
        nationality.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(nationality);

        unationality = new JTextField();
        unationality.setBounds(220, 140, 150, 18);
        add(unationality); 

        JLabel aadhar = new JLabel("Aadhar no.");
        aadhar.setBounds(60, 200, 220, 20);
        aadhar.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(aadhar);

        uaadhar = new JTextField();
        uaadhar.setBounds(220, 200, 150, 18);
        add(uaadhar); 

        JLabel address = new JLabel("Address");
        address.setBounds(60, 260, 220, 20);
        address.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(address);

        uaddress = new JTextField();
        uaddress.setBounds(220, 260, 150, 18);
        add(uaddress); 

        ButtonGroup gendergroup = new ButtonGroup();
        JLabel gender = new JLabel("Gender");
        gender.setBounds(60, 320, 220, 20);
        gender.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(gender);
        male = new JRadioButton("Male");
        male.setBounds(200, 320, 80, 18);
        add(male);
        male.setBackground(Color.lightGray);
        female = new JRadioButton("Female");
        female.setBounds(300, 320, 80, 18);
        female.setBackground(Color.lightGray);
        add(female);
        gendergroup.add(male);
        gendergroup.add(female);
        
        JLabel phone = new JLabel("Phone Number");
        phone.setBounds(60, 380, 220, 20);
        phone.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(phone);

        uphone = new JTextField();
        uphone.setBounds(220, 380, 150, 18);
        add(uphone); 

        JLabel email = new JLabel("Email ID");
        email.setBounds(60, 440, 220, 20);
        email.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(email);

        uemail = new JTextField();
        uemail.setBounds(220, 440, 150, 18);
        add(uemail); 

        JButton save = new JButton("Save");
        save.setBounds(220, 500, 80, 22);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.white);
        save.addActionListener(this);
        add(save);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/Icons/emp.png"));
        JLabel img = new JLabel(i1);
        img.setBounds(50,0,1200,600);
        add(img);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {

        String name = uname.getText();
        String nationality = unationality.getText();
        String phone = uphone.getText();
        String email = uemail.getText();
        if(phone.length() < 10){
            JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digit");
        }
        else{
            String address = uaddress.getText();
            String aadhar = uaadhar.getText();
            if(aadhar.length() <12){
                JOptionPane.showMessageDialog(null, "Aadhar number must be exactly 12 digit");
            }else{
                String gender = null;
                if(male.isSelected()){
                    gender="male";
                }else{
                    gender = "female";
                }
                try{
                    conn conn = new conn();

                    String query = "Insert into passenger values('"+name+"','"+nationality+"','"+phone+"','"+address+"','"+aadhar+"','"+gender+"','"+email+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Done!! Customer Added!!");
                    setVisible(false);
                }catch(Exception e){
                e.printStackTrace();
                }
        }
        }
        
        
    }
    public static void main(String[] args){
        new addcustomer();
        
    }
}
