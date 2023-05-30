package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A dialog that takes a player ID, and an area ID
 * The player will try to move to this area
 */
public class moveDialog extends JDialog {
    /**
     * Holds the whole dialog content
     */
    private JPanel contentPane;
    /**
     * button for accepting the input
     */
    private JButton buttonOK;
    /**
     * button for cancelling the dialog
     */
    private JButton buttonCancel;
    /**
     * input for character ID
     */
    private JTextField charIDField;
    /**
     * input for area ID
     */
    private JTextField areaIDField;
    /**
     * label for character ID
     */
    private JLabel charIDLabel;
    /**
     * label for area ID
     */
    private JLabel areaIDLabel;
    /**
     * response which will be returned to the caller of this dialog
     */
    private dialogResponse response = new dialogResponse(false, -1, -1);

    /**
     * Constructs the dialog, sets up action listeners for interaction
     */
    public moveDialog() {
        setContentPane(contentPane);
        setTitle("Trying to move somewhere");
        setBounds(250,250,500,200);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Set the response data according to the entered value
     */
    private void onOK() {
        // add your code here
        response.OK = true;
        response.playerID = Integer.parseInt(charIDField.getText());
        response.areaID = Integer.parseInt(areaIDField.getText());
        setVisible(false);
    }
    /**
     * Disposes the dialog window
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    /**
     * Shows the dialog
     *
     * @return the response data object
     */
    public dialogResponse showDialog(){
        setVisible(true);
        return response;
    }
}
