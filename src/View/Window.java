package View;

import Controller.Game;
import Model.PlayableCharacter;
import Model.Saboteur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Window extends JFrame{
    private List<Drawable> components = new ArrayList<>();

    private List<PlayableCharacter> players = new ArrayList<>();
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
        //helper variable, so ActionListeners can use window's this, naming convention from js
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
                Game.getInstance().StartGame(that);
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
        sabotageButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("drawing " + components.size() + " components");
                drawComponents();
                System.out.println("GameArea has " + gameArea.getComponentCount() + " children");
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
        Object[] directoryColumnNames = new Object[]{"Type of PC", "ID of field"};
        directoryTable = new JTable(new DefaultTableModel(directoryColumnNames,0));
        directoryTable.setBounds(0,0,15,15);
    }

    public void drawComponents(){
        gameArea.removeAll();
        for (var item:components) {
            gameArea.add(item.draw());
        }
        DefaultTableModel dtm = (DefaultTableModel) directoryTable.getModel();
        dtm.setRowCount(0);
        for (var item:players) {
            //dtm.setValueAt("Don't know yet", players.indexOf(item), 0);
            dtm.addRow(new Object[]{item.getViewString(), item.GetArea().getID()});
            //directoryTable.getModel().setValueAt(item.GetArea().getID(), players.indexOf(item), 0);
        }
        revalidate();
        repaint();
    }

    public void addComponent(Drawable component){
        components.add(component);
    }

    public void addPlayableCharacter(PlayableCharacter pc){
        players.add(pc);
    }
}
