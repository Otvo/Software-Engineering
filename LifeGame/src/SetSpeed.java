import java.awt.event.*;

import javax.swing.*;

public class SetSpeed {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JTextField speedText;
    JLabel note;
    JButton confirm;
    JButton cancel;
    JOptionPane op1 = new JOptionPane("Input Out of Bound!", JOptionPane.ERROR_MESSAGE);
    JDialog dialog1 = op1.createDialog("Wrong");
    JOptionPane op2 = new JOptionPane("Input Error!", JOptionPane.ERROR_MESSAGE);
    JDialog dialog2 = op2.createDialog("Wrong");

    int time = 1000;

    public SetSpeed() {
        frame = new JFrame("Speed");
        frame.setSize(250, 140);
        frame.setLocationRelativeTo(SetFrame.iFrame);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Speed:");
        label.setBounds(65, 15, 80, 25);
        panel.add(label);

        speedText = new JTextField(10);
        speedText.setBounds(110, 15, 70, 25);
        panel.add(speedText);

        note = new JLabel("(0<speed≤10000)");
        note.setBounds(65, 45, 150, 25);
        panel.add(note);

        cancel = new JButton("cancel");
        cancel.setBounds(40, 80, 80, 25);
        panel.add(cancel);
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
            }
        });

        confirm = new JButton("confirm");
        confirm.setBounds(135, 80, 80, 25);
        panel.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    time = Integer.parseInt(speedText.getText());
                    if (time <= 0 || time > 10000) {
                        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog1.setAlwaysOnTop(true);
                        dialog1.setModal(false);
                        dialog1.setVisible(true);
                    } else
                        SetFrame.speed = time;
                } catch (Exception e1) {
                    dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog2.setAlwaysOnTop(true);
                    dialog2.setModal(false);
                    dialog2.setVisible(true);
                }
                frame.dispose();
            }
        });
        frame.add(panel);
    }
}
