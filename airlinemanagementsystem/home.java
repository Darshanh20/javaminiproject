package airlinemanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class home extends JFrame implements ActionListener {

    public home() {
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/Icons/MOUNT1.jpg"));
        JLabel img = new JLabel(i1);
        img.setBounds(0,0,1600,800);
        add(img);

        JLabel heading = new JLabel("DARSHAN AIR WELCOMES YOU ! ");
        img.add(heading);
        heading.setBounds(500,50,800,40);
        heading.setForeground(Color.red);
        heading.setFont(new Font("Tahoma" , Font.PLAIN , 50));

        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        JMenu details = new JMenu("Details");
        menu.add(details);

        JMenuItem customerdetails = new JMenuItem("Add Customer Details");
        details.add(customerdetails);
        customerdetails.addActionListener(this);
        JMenuItem Flightdetails = new JMenuItem("Flight Details");
        details.add(Flightdetails);
        Flightdetails.addActionListener(this);
        JMenuItem BookFlight = new JMenuItem("Book Flight");
        details.add(BookFlight);
        BookFlight.addActionListener(this);
        JMenuItem CancelFlight = new JMenuItem("Cancel Flight");
        JMenuItem JourneyDetails = new JMenuItem("Journey Details");
        details.add(JourneyDetails);
        JourneyDetails.addActionListener(this);
        details.add(CancelFlight);
        CancelFlight.addActionListener(this);
        details.add(CancelFlight);


        JMenu Ticket = new JMenu("Ticket");
        menu.add(Ticket);
        JMenuItem BoardingPass = new JMenuItem("Boarding Pass");
        BoardingPass.addActionListener(this);
        Ticket.add(BoardingPass);


        setLocation(800, 250);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
       String text = a.getActionCommand();

       if(text.equals("Add Customer Details")){
        new addcustomer();
       }
       if(text.equals("Flight Details")){
        new flightinfo();
       }
       if(text.equals("Book Flight")){
        new bookflight();
       }
       if(text.equals("Journey Details")){
        new journeydetails();
       }
       if(text.equals("Cancel Flight")){
        new cancelticket();
       }
       if(text.equals("Boarding Pass")){
        new boardingpass();
       }
    }

    public static void main(String[] args) {
        new home();
    }
}
