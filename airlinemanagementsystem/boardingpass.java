package airlinemanagementsystem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class boardingpass extends JFrame implements ActionListener {
    JTextField upnr;
    JButton enter;
    JLabel uname,unationality,src,dest,fcode,fname,ddate;
    public boardingpass(){
        setLocation(200, 10);
        setSize(1200, 520);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        JLabel heading = new JLabel("DARSHAN AIR");
        heading.setBounds(180, 20, 220, 25);
        heading.setFont(new Font("Tahoma" , Font.PLAIN , 30));
        add(heading);

        JLabel heading2 = new JLabel("Boarding Pass");
        heading2.setBounds(180, 60, 220, 25);
        heading2.setFont(new Font("Tahoma" , Font.PLAIN , 20));
        add(heading2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/images-removebg-preview.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(600, 80, 500, 410);
        add(lblimage);

        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 120, 220, 20);
        lblpnr.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(lblpnr);
        upnr = new JTextField();
        upnr.setBounds(220, 120, 150, 18);
        add(upnr); 

        enter = new JButton("Enter");
        enter.setBounds(400, 120, 100, 22);
        enter.setBackground(Color.BLACK);
        enter.setForeground(Color.white);
        enter.addActionListener(this);
        add(enter);

        JLabel name = new JLabel("Name");
        name.setBounds(60, 180, 220, 20);
        name.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(name);
        uname = new JLabel();
        uname.setBounds(220, 180, 150, 18);
        add(uname);

        JLabel nationality = new JLabel("Nationality");
        nationality.setBounds(60, 240, 220, 20);
        nationality.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(nationality);
        unationality = new JLabel();
        unationality.setBounds(220, 240, 150, 18);
        add(unationality);

        JLabel source = new JLabel("Source");
        source.setBounds(60, 300, 180, 20);
        source.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(source);

        src = new JLabel();
        src.setBounds(180, 300, 150, 18);
        add(src);

        JLabel destination = new JLabel("Destination");
        destination.setBounds(280, 300, 180, 20);
        destination.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(destination);

        dest = new JLabel();
        dest.setBounds(400, 300, 150, 18);
        add(dest);

        JLabel flightname = new JLabel("Flight Name");
        flightname.setBounds(60, 360, 180, 20);
        flightname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(flightname);

        fname = new JLabel();
        fname.setBounds(180, 360, 150, 18);
        add(fname);

        JLabel flightcode = new JLabel("Flight Code");
        flightcode.setBounds(280, 360, 180, 20);
        flightcode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(flightcode);

        fcode = new JLabel();
        fcode.setBounds(400, 360, 150, 18);
        add(fcode);

        JLabel date = new JLabel("Date of Journey");
        date.setBounds(60, 430, 180, 20);
        date.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(date);

        ddate = new JLabel();
        ddate.setBounds(220, 430, 150, 18);
        add(ddate);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent a){

        if(a.getSource() == enter){
            String pnr = upnr.getText();

        try {
            conn conn = new conn();

            String query = "select * from reservation where pnr = '"+pnr+"'";

            ResultSet rs = conn.s.executeQuery(query);

            if(rs.next()){
                uname.setText(rs.getString("name")); 
                unationality.setText(rs.getString("nationality"));
                src.setText(rs.getString("src"));
                dest.setText(rs.getString("des"));
                fname.setText(rs.getString("flightname")); 
                fcode.setText(rs.getString("flightcode"));
                ddate.setText(rs.getString("ddate"));
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter correct PNR!!");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        }
    }
    public static void main(String[] args) {
        new boardingpass();
    }
}
