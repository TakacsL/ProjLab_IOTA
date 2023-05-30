package View;

import Controller.Game;
import Model.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Window is responsible for showing everything there is to be shown, the current state of the game,
 * the game map, the points for each team, and lets the user choose from
 * a set of actions.
 */

public class Window extends JFrame{

    /**
     * JPanel that covers the whole window
     */
    private JPanel wholePanel;
    /**
     * Holds tables about the game's state
     */
    private JPanel sidePanel;
    /**
     * This panel will hold all the game's objects
     */
    public JPanel gameArea;
    /**
     * Holds the menu toolbar
     */
    private JPanel menuPanel;
    /**
     * Holds the directory table
     */
    private JPanel directoryPanel;
    /**
     * Holds the points table
     */
    private JPanel pointsPanel;
    /**
     * Button for starting a new game
     */
    private JButton newGameButton;
    /**
     * Button for exiting the program
     */
    private JButton exitButton;
    /**
     * Toolbar for the available options
     */
    private JToolBar menuToolBar;
    /**
     * Shows information about areas and players
     */
    private JTable directoryTable;
    /**
     * Helps to make the points table scrollable
     */
    private JScrollPane pointsScrollPane;
    /**
     * Shows the points for each team
     */
    private JTable pointsTable;
    /**
     * Helps to make the points table scrollable
     */
    private JScrollPane directoryScrollPane;
    /**
     * Holds the action buttons for controlling the characters
     */
    private JPanel activityPanel;
    /**
     * Button for moving a character
     */
    private JButton MoveButton;
    /**
     * Button for sabotaging with a character
     */
    private JButton sabotageButton;
    /**
     * Button for repairing with a character
     */
    private JButton repairButton;
    /**
     * Button for making an area sticky with a character
     */
    private JButton stickyButton;
    /**
     * Button for making an area slippery with a character
     */
    private JButton slimyButton;
    /**
     * Button for setting a Pumps input and output
     */
    private JButton setButton;
    /**
     * Button for saving the game
     */
    private JButton saveButton;
    /**
     * Button for loading the last saved game
     */
    private JButton loadButton;

    /**
     * Holds information to be shown in directoryTable
     */
    public String[][] directoryData = {};

    /**
     * Constructs the window object
     *
     * Sets up all the necessary action callbacks to operate the game correctly
     */
    public Window() {
        setTitle("IOTA_GUI");
        setContentPane(wholePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setVisible(true);

        /**
         *Makes gameArea into absoluteLayout, so we can place stuff to exact coordinates
         */
        gameArea.setLayout(null);
        //helper variable, so ActionListeners can use window's this, naming convention from js
        var that = this;
        exitButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Exits the program
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(new WindowEvent(that, WindowEvent.WINDOW_CLOSING));
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Starts a new game
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().StartGame(that);
                drawComponents();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Saves the current game's state
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
             * Loads the last saved game
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.getInstance().LoadGame(that);
                drawComponents();
            }
        });
        MoveButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Shows the move dialog, then calls the PlayableCharacter::MoveTo function based on the dialog's return data
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDialog mwd = new moveDialog();
                dialogResponse response = mwd.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).MoveTo(Game.getInstance().map.getAreabyID(response.areaID));
                drawComponents();
            }
        });
        sabotageButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Shows the action dialog, then calls the PlayableCharacter::BreakArea function based on the dialog's return data
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).BreakArea();
                drawComponents();
            }
        });
        repairButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Shows the action dialog, then calls the PlayableCharacter::BreakArea function based on the dialog's return data
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).FixArea();
                drawComponents();
            }
        });
        stickyButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Shows the action dialog, then calls the PlayableCharacter::makeSticky function based on the dialog's return data
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).makeSticky();
                drawComponents();
            }
        });
        slimyButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * Shows the action dialog, then calls the PlayableCharacter::makeSlippery function based on the dialog's return data
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDialog dialog = new actionDialog();
                dialogResponse response = dialog.showDialog();
                if (response.OK) Game.getInstance().map.getPlayerbyID(response.playerID).makeSlippery();
                drawComponents();
            }
        });
    }

    /**
     * Initializes the UI elements of the window
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
        gameArea = new JPanel();
        String[] columnNames = {"Saboteur Points", "Repairman points"};
        pointsTable = new JTable(new DefaultTableModel(columnNames, 0));
        pointsTable.setBounds(0,0,15,15);
        String[] directoryColumnNames = new String[]{"Type of PC", "ID of field", "ID of PC"};
        directoryTable = new JTable(new DefaultTableModel(directoryColumnNames,0));
        directoryTable.setBounds(0,0,15,15);
        drawComponents();
    }

    /**
     * Draws all map components and updates the points
     */
    public void drawComponents(){
        if(Game.getInstance().map == null) return;
        gameArea.removeAll();
        for (var area:Game.getInstance().map.areas) {
            gameArea.add(area.draw());
        }
        for (var pc:Game.getInstance().map.playableCharacters) {
            gameArea.add(pc.draw());
        }
        //data model lekérés directoryTable-höz
        DefaultTableModel dtm = (DefaultTableModel) directoryTable.getModel();
        //table törlése
        dtm.setRowCount(0);
        //helyes adatok betöltése
        for (var item:Game.getInstance().map.playableCharacters) {
            dtm.addRow(new Object[]{item.getViewString(), item.GetArea().getID(), item.getID()});
        }
        //ugyanez a pointstTable-el
        DefaultTableModel pdtm = (DefaultTableModel) pointsTable.getModel();
        pdtm.setRowCount(0);
        pdtm.addRow(new Object[]{Game.saboteurPoints, Game.repairmanPoints});
        revalidate();
        repaint();
    }

    /**
     * Shows a message dialog which indicates that something went wrong
     */
    public void showFailedMessage(){
        final JOptionPane pane = new JOptionPane("It failed");
        final JDialog d = pane.createDialog((JFrame)null, "Sorry");
        d.setBounds(250,250, 500, 200);
        d.setVisible(true);
    }
}
