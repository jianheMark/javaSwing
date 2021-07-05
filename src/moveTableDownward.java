import javax.swing.*;
import java.awt.*;
//https://stackoverflow.com/questions/59369704/how-can-i-move-a-table-downward-in-java/59370224#59370224
public class moveTableDownward extends JPanel {


    moveTableDownward(){
        super(new BorderLayout());
        JLabel label = new JLabel("Enter proper data: ");
        label.setBounds(0, 0, 120, 300);
        label.setForeground(Color.RED);

        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;
        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));


        add(label, BorderLayout.PAGE_START);
        JButton btn = new JButton("Click");
        btn.setBounds(100, 300, 80, 50);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btn, new FlowLayout());
        String data [][] = new String [6][4];
        String column[]={"No.","Player 1","Player 2","Strategy"};
        JTable table = new JTable(data, column);
        JScrollPane sp=new JScrollPane(table);
        add(sp, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.PAGE_END);

    }
    public static void createAndShowGUI(){
        try { for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { if ("Nimbus".equals(info.getName())) { UIManager.setLookAndFeel(info.getClassName());break; } }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        JFrame frame = new JFrame("demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        moveTableDownward content = new moveTableDownward ();
        content.setOpaque(true);
        frame.getContentPane().add(content);
        frame.pack();
        frame.setVisible(true);
    }


}
