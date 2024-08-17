package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.SpinnerDateModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.util.Random;

public class bookflight extends JFrame implements ActionListener {

    JTextField uaadhar;
    JLabel uname, unationality, ugender, uaddress, flightname, flightid;
    JButton fetch, check, book;
    Choice source1, destination1;
    JSpinner dateofj;  // JSpinner for date selection

    public bookflight() {
        setLocation(200, 10);
        setSize(1200, 800);
        setMinimumSize(new Dimension(1200, 800));
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(180, 20, 220, 25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(heading);

        JLabel aadhar = new JLabel("Aadhar no.");
        aadhar.setBounds(60, 60, 220, 20);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(aadhar);

        uaadhar = new JTextField();
        uaadhar.setBounds(220, 60, 150, 18);
        add(uaadhar);

        fetch = new JButton("Fetch");
        fetch.setBounds(400, 60, 90, 18);
        fetch.setBackground(Color.BLACK);
        fetch.setForeground(Color.white);
        fetch.addActionListener(this);
        add(fetch);

        JLabel name = new JLabel("Name");
        name.setBounds(60, 120, 220, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(name);
        uname = new JLabel();
        uname.setBounds(220, 120, 150, 18);
        add(uname);

        JLabel nationality = new JLabel("Nationality");
        nationality.setBounds(60, 180, 220, 20);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nationality);
        unationality = new JLabel();
        unationality.setBounds(220, 180, 150, 18);
        add(unationality);

        JLabel address = new JLabel("Address");
        address.setBounds(60, 240, 220, 20);
        address.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(address);

        uaddress = new JLabel();
        uaddress.setBounds(220, 240, 150, 18);
        add(uaddress);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(60, 300, 220, 20);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(gender);
        ugender = new JLabel();
        ugender.setBounds(220, 300, 150, 18);
        add(ugender);

        JLabel source = new JLabel("Source");
        source.setBounds(60, 360, 180, 20);
        source.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(source);

        source1 = new Choice();
        source1.setBounds(240, 360, 220, 30);
        source1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(source1);

        JLabel destination = new JLabel("Destination");
        destination.setBounds(60, 420, 180, 20);
        destination.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(destination);

        destination1 = new Choice();
        destination1.setBounds(240, 420, 220, 30);
        destination1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(destination1);

        try {
            conn c = new conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                source1.add(rs.getString("source"));
                destination1.add(rs.getString("destination"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        check = new JButton("Check Flights");
        check.setBounds(435, 420, 150, 22);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.white);
        check.addActionListener(this);
        add(check);

        JLabel fname = new JLabel("Flight name");
        fname.setBounds(60, 480, 220, 20);
        fname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(fname);
        flightname = new JLabel();
        flightname.setBounds(220, 480, 150, 18);
        add(flightname);

        JLabel fid = new JLabel("Flight Code");
        fid.setBounds(60, 540, 220, 20);
        fid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(fid);
        flightid = new JLabel();
        flightid.setBounds(220, 540, 150, 18);
        add(flightid);

        JLabel date = new JLabel("Date Of Journey");
        date.setBounds(60, 600, 220, 20);
        date.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(date);

        // Initialize the JSpinner for date selection
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(new JSpinner(model), "yyyy-MM-dd");
        dateofj = new JSpinner(model);
        dateofj.setEditor(editor);
        dateofj.setBounds(220, 600, 150, 20);
        add(dateofj);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(600, 80, 500, 410);
        add(lblimage);

        book = new JButton("Book Flight");
        book.setBounds(220, 660, 150, 22);
        book.setBackground(Color.BLACK);
        book.setForeground(Color.white);
        book.addActionListener(this);
        add(book);

        revalidate();
        repaint();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == fetch) {
            String aadhar = uaadhar.getText();
            try {
                conn conn = new conn();

                String query = "select * from passenger where aadhar ='" + aadhar + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    uname.setText(rs.getString("name"));
                    unationality.setText(rs.getString("nationality"));
                    ugender.setText(rs.getString("gender"));
                    uaddress.setText(rs.getString("address"));
                } else {
                    JOptionPane.showMessageDialog(null, "User does not exist!! Please enter correct aadhar.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (a.getSource() == check) {
            String src = source1.getSelectedItem();
            String dest = destination1.getSelectedItem();

            try {
                conn conn = new conn();

                String query = "select * from flight where source ='" + src + "' and destination = '" + dest + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    flightname.setText(rs.getString("f_name"));
                    flightid.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No flights available!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Random random = new Random();
            String aadhar = uaadhar.getText();
            String name = uname.getText();
            String nationality = unationality.getText();
            String fname = flightname.getText();
            String fid = flightid.getText();
            String src = source1.getSelectedItem();
            String dest = destination1.getSelectedItem();
            String date = new SimpleDateFormat("yyyy-MM-dd").format((java.util.Date) dateofj.getValue());

            try {
                conn conn = new conn();
                String pnr = "" + random.nextInt(1000000);
                String tic = "" + random.nextInt(1000);
                String query = "insert into reservation values('PNR-" + pnr + "','TIC-" + tic + "','" + aadhar + "' , '" + name + "' , '" + nationality + "' , '" + fname + "' ,'" + fid + "' ,'" + src + "','" + dest + "','" + date + "')";

                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "TICKET BOOKED SUCCESSFULLY!!! YOUR PNR NUMBER:PNR-" + pnr + " AND TICKET ID:TIC-" + tic);
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new bookflight();
    }
}