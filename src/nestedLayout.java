import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
/*
TopLevel: a panel will contain: 1. plafComponent: BorderLayout.NORTH,
2. dynamicPane: BorderLayout.WEST, JSplitPanel: BorderLayout.CENTER.
3. plafComponent can be NORTH or SOUTH.

JSplitPanel must be in the center.
Horizontally,there are two component. So either {CENTER, WEST} or {CENTER,  EAST}.

plafComponent including: 1. plafChoose, an ComboBox use to udpate the UI. 2. a pack, CheckBox used for pack.
plafComponent Style: FlowLayout

dynamicPanel: 1. an JButton used for add new Jlabel: BorderLayout.NORTH,
2. an JScrollPane stored all the new added JLabels : BorderLayout.CENTER
dynamicPanel Layout: BorderLayout.

tableScrollPane: a JScrollPanel used to contain a JTable. from data&header to DefaultTableModel to JTable.
imagePanel: used to contain a image. GridBagLayout style.
JSplitPane: used to contain tableScrollPane and imagePanel. Splitted in vertical way.

 */



public class nestedLayout extends JPanel {
    public static JFrame frame;

    public nestedLayout(){
        super(new BorderLayout(5,5));
        setBorder(new TitledBorder("BorderLayout(5,5)"));
        JPanel plafComponent = new JPanel(new FlowLayout(FlowLayout.RIGHT,3,3));
        plafComponent.setBorder(new TitledBorder("new FlowLayout(Flowlayout.RIGHT,3,3)"));
        final UIManager.LookAndFeelInfo[] plafinfo = UIManager.getInstalledLookAndFeels();
        String[] plafNames = new String[plafinfo.length];

        for(int ii=0; ii<plafinfo.length; ii++){plafNames[ii] = plafinfo[ii].getName();}
        final JComboBox plafChooser = new JComboBox(plafNames);
        plafComponent.add(plafChooser);
        final JCheckBox pack = new JCheckBox("Pack on PLAF change", true);
        plafComponent.add(pack);

        plafChooser.addActionListener(e -> {
            int index = plafChooser.getSelectedIndex();
            try {
                UIManager.setLookAndFeel(plafinfo[index].getClassName());
                SwingUtilities.updateComponentTreeUI(frame);
                if(pack.isSelected()){frame.pack();frame.setMaximumSize(frame.getSize());}
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (InstantiationException instantiationException) {
                instantiationException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                unsupportedLookAndFeelException.printStackTrace();
            }

        });
        add(plafComponent,BorderLayout.SOUTH);
        JPanel dynamicPanel = new JPanel(new BorderLayout(4,4));
        dynamicPanel.setBorder(new TitledBorder("BorderLayout(4,4)"));
        add(dynamicPanel,BorderLayout.EAST);
        final JPanel labels = new JPanel(new GridLayout(0,2,3,3));
        labels.setBorder(new TitledBorder("GridLayout(0,2,3,3)"));
        JButton addNew = new JButton("Add Another Button");
        dynamicPanel.add(addNew,BorderLayout.NORTH);
        dynamicPanel.add(new JScrollPane(labels),BorderLayout.CENTER);
        addNew.addActionListener(new ActionListener() {
            private int labelcount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                labels.add(new JLabel("Label"+ ++labelcount));
                frame.validate();
            }
        });
        String[] header = {"name","value"};
        String[] a = new String[0];
        String[] names = System.getProperties().stringPropertyNames().toArray(a);
        String[][]data = new String[names.length][2];

        for(int i=0; i<names.length;i++){
            data[i][0] = names[i];
            data[i][1] = System.getProperty(names[i]);
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, header);
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        Dimension tablePreferredSize =  tableScrollPane.getPreferredSize();
        tableScrollPane.setPreferredSize(new Dimension(tablePreferredSize.width, tablePreferredSize.height/3));

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBorder(new TitledBorder("GridBagLayout()"));
        BufferedImage bi = new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        GradientPaint gp = new GradientPaint(20f,20f,Color.RED,180f,180f,Color.yellow);
        g.setPaint(gp);
        g.fillRect(0,0,200,200);
        ImageIcon image = new ImageIcon(bi);
        JLabel imagelabel = new JLabel(image);
        imagePanel.add(imagelabel,null);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tableScrollPane, imagePanel);
        //splitPane.setResizeWeight(1);
        add(splitPane,BorderLayout.CENTER);
    }




    public static void createAndShowGUI(){
        frame = new JFrame("nestedLayout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nestedLayout content = new nestedLayout();
        content.setOpaque(true);
        frame.getContentPane().add(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        try{frame.setLocationByPlatform(true);frame.setMaximumSize(frame.getSize());} catch (Exception e){}
        frame.setVisible(true);
    }
}
