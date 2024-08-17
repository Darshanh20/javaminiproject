package airlinemanagementsystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.*;


public class cancelticket extends JFrame implements ActionListener {
    JTextField upnr;
    JButton fetch,cancelbutton;
    JLabel uname,cid,fcode,date,fname;
    public cancelticket(){
        setLocation(200, 10);
        setSize(800, 600);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        Random random = new Random();

        JLabel heading = new JLabel("Cancellation");
        heading.setBounds(180, 20, 220, 25);
        heading.setFont(new Font("Tahoma" , Font.PLAIN , 25));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/Icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(400, 80, 500, 410);
        add(lblimage);

        JLabel pnr = new JLabel("PNR");
        pnr.setBounds(60, 60, 220, 20);
        pnr.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(pnr);

        upnr = new JTextField();
        upnr.setBounds(220, 60, 150, 18);
        add(upnr); 

        fetch = new JButton("Show Deatils");
        fetch.setBounds(400, 60, 130, 18);
        add(fetch);
        fetch.setBackground(Color.BLACK);
        fetch.setForeground(Color.white);
        fetch.addActionListener(this);

        JLabel name = new JLabel("Name");
        name.setBounds(60, 120, 220, 20);
        name.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(name);
        uname = new JLabel();
        uname.setBounds(220, 120, 150, 18);
        add(uname);

        JLabel CancelID = new JLabel("CANCELLATION ID");
        CancelID.setBounds(60, 180, 220, 20);
        CancelID.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(CancelID);
        cid = new JLabel(""+random.nextInt(1000000));
        cid.setBounds(220, 180, 150, 18);
        cid.setFont(new Font("Tahoma" , Font.PLAIN , 14));
        add(cid);

        JLabel flightcode = new JLabel("Flight Code");
        flightcode.setBounds(60, 240, 220, 20);
        flightcode.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(flightcode);

        fcode = new JLabel();
        fcode.setBounds(220, 240, 150, 18);
        add(fcode);

        JLabel flightname = new JLabel("Flight Name");
        flightname.setBounds(60, 300, 220, 20);
        flightname.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(flightname);

        fname = new JLabel();
        fname.setBounds(220, 300, 150, 18);
        add(fname);

        JLabel ddate = new JLabel("Date Of Travel");
        ddate.setBounds(60, 360, 220, 20);
        ddate.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(ddate);
        date = new JLabel();
        date.setBounds(220, 360, 150, 18);
        add(date);

        cancelbutton = new JButton("CANCEL FLIGHT");
        cancelbutton.setBounds(220, 420, 130, 18);
        add(cancelbutton);
        cancelbutton.setBackground(Color.BLACK);
        cancelbutton.setForeground(Color.white);
        cancelbutton.addActionListener(this);


        setVisible(true);


    }


    public void actionPerformed(ActionEvent a){
        if(a.getSource() == fetch){
            String pnr = upnr.getText();

            try {
                conn conn = new conn();
                String query = "select * from reservation where PNR = '"+pnr+"'";

                ResultSet rs = conn.s.executeQuery(query);

                if(rs.next()){
                    uname.setText(rs.getString("name")); 
                    fcode.setText(rs.getString("flightcode"));
                    fname.setText(rs.getString("flightname"));
                    date.setText(rs.getString("ddate")); 
                }
                else{
                    JOptionPane.showMessageDialog(null, "PNR does not exist!!. Please enter correct PNR.");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(a.getSource() == cancelbutton){
            String name = uname.getText();
            String pnr = upnr.getText();
            String cno = cid.getText();
            String flicode = fcode.getText();
            String dot = date.getText();

            try {
                conn conn = new conn();

                String query = "insert into cancel values('"+pnr+"','"+name+"','"+cno+"','"+flicode+"','"+dot+"')";

                conn.s.executeUpdate(query);

                conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");

                JOptionPane.showMessageDialog(null, "Flight Cancelled!!!");
                setVisible(false);
            } catch(Exception e) {
                e.printStackTrace();
            }

        }


    }

    public static void main(String[] args) {
        new cancelticket();
    }
}
