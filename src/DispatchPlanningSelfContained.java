
/*The problem starts here, with the assumptions underlying it:
        DefaultTableModel dtm = (DefaultTableModel) tableResults.getModel();
        The code seems to be treating dtm as a copy of the initial table model, whereas it now simply refers to it.
        The easiest fix is to initialize the table model with the raw data each time it's changed:
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class DispatchPlanningSelfContained extends JFrame {

    Object[][] data = new Object[][]{};
    String[] titles = {
            "Code", "Description", "Rulling", "Page",
            "MRP", "QPC", "Total Order", "Stock", "Shortage"
    };

    public DispatchPlanningSelfContained() {
        initComponents();
        partLists.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                DefaultTableModel dtm = new DefaultTableModel(data, titles);

                int selection[] = partLists.getSelectedIndices();
                System.out.println("Selection Length = " + selection.length);
                for (int i = 0; i < selection.length; i++) {
                    dtm.addColumn(partLists.getModel().getElementAt(selection[i]));
                    System.out.println("selection [" + i + "] = " +
                            partLists.getModel().getElementAt(selection[i]));
                }
                tableResults.setModel(dtm);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        partyLabel = new JLabel();
        listScrolPane = new JScrollPane();
        partLists = new JList<>();
        planningResult = new JLabel();
        tableScrolPane = new JScrollPane();
        tableResults = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        partyLabel.setText("Pending Party Names");

        partLists.setModel(new AbstractListModel<String>() {
            String[] strings = {
                    "Party 1", "Party 2", "Party 3", "Party 4",
                    "Party 5", "Party 6", "Party 7", "Party 8", "Party 9"
            };

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }
        });
        listScrolPane.setViewportView(partLists);

        planningResult.setText("Planning Results:");

        tableResults.setAutoCreateRowSorter(true);
        tableResults.setModel(new DefaultTableModel(data, titles));
        tableScrolPane.setViewportView(tableResults);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 967, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(tableScrolPane)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(268, 268, 268)
                                                                        .addComponent(partyLabel))
                                                                .addComponent(planningResult))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(listScrolPane, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)))
                                        .addContainerGap()))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 585, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(partyLabel)
                                                        .addGap(96, 96, 96)
                                                        .addComponent(planningResult))
                                                .addComponent(listScrolPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tableScrolPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DispatchPlanningSelfContained().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel partyLabel;
    private JLabel planningResult;
    private JList<String> partLists;
    private JScrollPane tableScrolPane;
    private JScrollPane listScrolPane;
    private JTable tableResults;
    // End of variables declaration//GEN-END:variables
}

