package View;

import javax.swing.*;
import java.awt.event.*;

/**
 * Egy bõvített dialógusablak amely felelõs egy ID
 * bekéréséért amin a játkos majd el fog végezni valamilyen akciót
 */

public class actionDialog extends JDialog {
    /**
     * Holds the content of the dialog window
     */
    private JPanel contentPane;
    /**
     * button for submitting the inputs
     */
    private JButton buttonOK;
    /**
     * button for cancelling the dialog
     */
    private JButton buttonCancel;
    /**
     * Input for the ID
     */
    private JTextField charIDField;
    /**
     * Label for the input
     */
    private JLabel charIDLabel;

    /**
     * Response object which will be returned to the caller of this dialog
     */
    dialogResponse response = new dialogResponse(false, -1, -1);

    /**
     * Constructs the dialog, initializes the components with action listeners
     */
    public actionDialog() {
        setContentPane(contentPane);
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
