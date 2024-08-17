package airlinemanagementsystem;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class flightinfo extends JFrame {

    public flightinfo() {

        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        String[][] data = null; // 2D array for table data
        String[] columnNames = null; // Array for column names

        try {
            conn conn = new conn();
            ResultSet rs = conn.s.executeQuery("select * from flight");

            // Get metadata to determine the number of columns
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Initialize the columnNames array
            columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }

            // ArrayList to store rows temporarily
            ArrayList<String[]> rows = new ArrayList<>();

            // Fetching data from the result set
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getString(i + 1);
                }
                rows.add(row);
            }

            // Converting the ArrayList to a 2D array
            data = new String[rows.size()][columnCount];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the table with the data and column names
        JTable table = new JTable(data, columnNames);

        // Adding the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 740, 400);
        add(scrollPane);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new flightinfo();
    }
}
