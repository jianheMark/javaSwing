import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
/*
1. Using the DecimalFormatRenderer  to make the Number have thousand Seperator.  https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
2. Using the MyTableModel to have an highly customized TableModel.
3.  public String getColumnName(int col) {return columnNames[col];}  using this to make the column appears in the correct data type.
4. createAndShowGUI first line of Code is for making Nimbus UI.
5. setAutoCreateRowSorter make an sorter automatically.
 */


public class Car extends JPanel {
    public Car(){
        JTable table = new JTable(new MyTableModel());
        table.getColumnModel().getColumn(2).setCellRenderer(new DecimalFormatRenderer());
        table.setRowHeight(25);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"No.", "User Name", "Total Score"};
        private Object[][] data = {{"101", "Amit", (double)(670000)},
        {"100", "Jai",  (double)(80000)},
        {"101", "Sachin", (double)(700000)}};
                
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    
    }

    static class DecimalFormatRenderer extends DefaultTableCellRenderer{
        private static final DecimalFormat formatter = new DecimalFormat("#,###.00");
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
            value = formatter.format(value);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, col);
        }
    }


    public static void createAndShowGUI(){
        try { for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { if ("Nimbus".equals(info.getName())) { UIManager.setLookAndFeel(info.getClassName());break; } }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

       JFrame frame = new JFrame("demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Car content = new Car();
        content.setOpaque(true);
        frame.getContentPane().add(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
