package View;

import Controller.Game;
import Model.PlayableCharacter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

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
    private JButton saveButton;
    private JButton loadButton;

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
        saveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().SaveGame();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().LoadGame(that);
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
                dialogResponse response = mwd.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).MoveTo(Game.getInstance().map.getAreabyID(response.areaID));
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
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).BreakArea();
            }
        });
        repairButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).FixArea();
            }
        });
        stickyButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).makeSticky();

            }
        });
        slimyButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).makeSlippery();
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        gameArea = new JPanel();
        String[] columnNames = {"Saboteur Points", "Repairman points"};
        pointsTable = new JTable(new DefaultTableModel(columnNames, 0));
        pointsTable.setBounds(0,0,15,15);
        String[] directoryColumnNames = new String[]{"Type of PC", "ID of field", "ID of PC"};
        directoryTable = new JTable(new DefaultTableModel(directoryColumnNames,0));
        directoryTable.setBounds(0,0,15,15);
    }

    public void drawComponents(){
        gameArea.removeAll();
        for (var item:components) {
            gameArea.add(item.draw());
        }
        //data model lekérés directoryTable-höz
        DefaultTableModel dtm = (DefaultTableModel) directoryTable.getModel();
        //table törlése
        dtm.setRowCount(0);
        //helyes adatok betöltése
        for (var item:players) {
            dtm.addRow(new Object[]{item.getViewString(), item.GetArea().getID(), item.getID()});
        }
        //ugyanez a pointstTable-el
        DefaultTableModel pdtm = (DefaultTableModel) pointsTable.getModel();
        pdtm.setRowCount(0);
        pdtm.addRow(new Object[]{Game.saboteurPoints, Game.repairmanPoints});
        revalidate();
        repaint();
    }

    public void addComponent(Drawable component){
        components.add(component);
    }

    public void addPlayableCharacter(PlayableCharacter pc){
        players.add(pc);
    }

    public void showFailedMessage(){
        final JOptionPane pane = new JOptionPane("It failed");
        final JDialog d = pane.createDialog((JFrame)null, "Sorry");
        d.setBounds(250,250, 500, 200);
        d.setVisible(true);
    }
}
