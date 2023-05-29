package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class moveDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField charIDField;
    private JTextField areaIDField;
    private JLabel charIDLabel;
    private JLabel areaIDLabel;

    private dialogResponse response = new dialogResponse(false, -1, -1);

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

    private void onOK() {
        // add your code here
        response.OK = true;
        response.playerID = Integer.parseInt(charIDField.getText());
        response.areaID = Integer.parseInt(areaIDField.getText());
        setVisible(false);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public dialogResponse showDialog(){
        setVisible(true);
        return response;
    }
}