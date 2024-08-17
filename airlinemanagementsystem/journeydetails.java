package airlinemanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class journeydetails extends JFrame implements ActionListener {
    JTable table;
    JTextField upnr;
    JButton show;
    public journeydetails(){
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 60, 220, 20);
        lblpnr.setFont(new Font("Tahoma" , Font.PLAIN , 18));
        add(lblpnr);
        upnr = new JTextField();
        upnr.setBounds(220, 60, 150, 18);
        add(upnr); 

        show = new JButton("Show");
        show.setBounds(400, 60, 100, 22);
        show.setBackground(Color.BLACK);
        show.setForeground(Color.white);
        show.addActionListener(this);
        add(show);

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 740, 400);
        scrollPane.setBackground(Color.darkGray);
        add(scrollPane);

        
        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }  

    public void actionPerformed(ActionEvent a) {
    try {
        conn conn = new conn();
        ResultSet rs = conn.s.executeQuery("Select * from reservation where PNR = '" + upnr.getText() + "'");

        if (!rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "No Information Found!!");
            return;
        }

        // Get metadata to determine the number of columns
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // Prepare column names
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i);
        }

        // Collect all rows
        ArrayList<String[]> dataList = new ArrayList<>();
        while (rs.next()) {
            String[] row = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getString(i);
            }
            dataList.add(row);
        }

        // Convert ArrayList to a 2D array
        String[][] data = new String[dataList.size()][columnCount];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        // Set the data and column names to the JTable
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    

    public static void main(String[] args){
        new journeydetails();
    }
}
