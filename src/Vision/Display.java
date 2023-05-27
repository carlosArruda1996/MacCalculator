package Vision;

import Model.Memory;
import Model.ObserverMemory;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel implements ObserverMemory {

    private final JLabel label = new JLabel(Memory.getInstance().getCurrentText());

    Display(){
        Memory.getInstance().addObserver(this);
        setBackground(new Color(46,49,50));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("sans serif",Font.PLAIN,35));

        setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 18));

        add(label);
    }

    @Override
    public void newValue(String newValue) {
        label.setText(newValue);
    }
}
