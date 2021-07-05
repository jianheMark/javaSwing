import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class imageinJtable {
    //https://stackoverflow.com/questions/68117284/how-to-show-icon-instead-of-number-in-jtable/68120037#68120037
    //add image to the JTable
    //The idea is still the same. One method for createAndShowGUI, one method for set an Panel.
    //and Have something in Panel.

    private JComponent ui = null;
    String prefix = "https://i.stack.imgur.com/";
    String[] urlStrings = {"wCF8S.png", "5v2TX.png", "F0JHK.png", "4EVv1.png", "xj49g.png"};
    ImageIcon[] imageIcons =  new ImageIcon[5];

    public imageinJtable() throws Exception { initUI(); }


    final void initUI() throws  Exception {
        if (ui!= null){return;}
        for(int i=0; i<urlStrings.length; i++){
            URL url = new URL(prefix+urlStrings[i]);
            imageIcons[i] = new ImageIcon(url);}

        Random rand = new Random();
        Integer[][]values =new Integer[5][5];
        for(int xx=0; xx<values.length; xx++){
            for(int yy=0; yy<values.length; yy++){
                values[xx][yy] = rand.nextInt(5);
            }
        }
        String[] columns = {"Col 1","Col 2", "Col 3","Col 4","Col 5"};
        DefaultTableModel tableModel = new DefaultTableModel(values, columns);
        ui = new JPanel(new GridLayout(0,2,0,0));
        ui.setBorder(new EmptyBorder(4,4,4,4));

        JTable table1 = new JTable(tableModel);
        table1.setRowHeight(36);
        JTable table2 = new JTable(tableModel);
        table2.setRowHeight(36);
        table2.setDefaultRenderer(Object.class, new icontableCellRenderer());
        ui.add(new JScrollPane(table1));
        ui.add(new JScrollPane(table2));
    }

    public JComponent getUI(){return ui;}

    public static void createAndShowGUI() throws Exception {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        //UIManager.setLookAndFeel();
        imageinJtable o = new imageinJtable();
        JFrame frame = new JFrame("Image in JTabel Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.getContentPane().add(o.getUI());
        frame.pack();
        frame.setVisible(true);
    }

    class icontableCellRenderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row, int col){
            Component component = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,col);
            JLabel label = (JLabel) component;
            final Integer val = (Integer) value;
            label.setIcon(imageIcons[val.intValue()]);
            label.setText("");
            return label;
        }
    }
}
