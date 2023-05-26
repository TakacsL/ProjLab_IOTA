package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Window extends JFrame{
    private JPanel wholePanel;
    private JPanel sidePanel;
    public JPanel gameArea;
    private JPanel menuPanel;
    private JPanel directoryPanel;
    private JPanel pointsPanel;
    private JButton newGameButton;
    private JButton exitButton;
    private JToolBar menuToolBar;
    private JTable directoryTable;
    private JScrollPane pointsScrollPane;
    private JTable pointsTable;
    private JScrollPane directoryScrollPane;
    private JPanel activityPanel;
    private JButton MoveButton;
    private JButton sabotageButton;
    private JButton repairButton;
    private JButton stickyButton;
    private JButton slimyButton;
    private JButton setButton;

    public String[][] directoryData = {};
    private final String[] directoryColumnNames = {"Type of PC", "ID of field"};

    public Window() {
        setTitle("IOTA_GUI");
        setContentPane(wholePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setVisible(true);
        /*
         *Makes gameArea into absoluteLayout, so we can place stuff to exact coordinates
         */
        gameArea.setLayout(null);
        var that = this;
        exitButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(new WindowEvent(that, WindowEvent.WINDOW_CLOSING));
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        MoveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDialog mwd = new moveDialog();
                boolean response = mwd.showDialog();
                if (response) JOptionPane.showMessageDialog(null, "Moved");
                else JOptionPane.showMessageDialog(null, "Didn't move");
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        gameArea = new JPanel();
        String[][] pointsData = {{"0","0"}};
        String[] columnNames = {"Saboteur Points", "Repairman points"};
        pointsTable = new JTable(pointsData, columnNames);
        pointsTable.setBounds(0,0,15,15);
        directoryTable = new JTable(directoryData, directoryColumnNames);
        directoryTable.setBounds(0,0,15,15);
    }
}
