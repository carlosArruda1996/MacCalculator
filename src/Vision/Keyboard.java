package Vision;

import Model.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard extends JPanel implements ActionListener {
    private final Color COLOR_DARK_GRAY = new Color(68,69,68);
    private final Color COLOR_LIGHT_GRAY= new Color(97,100,99);
    private final Color COLOR_ORANGE = new Color(242,163,60);

    public Keyboard(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints position = new GridBagConstraints();

        setLayout(layout);

        position.weightx = 1;
        position.weighty = 1;

        position.fill = GridBagConstraints.BOTH;

        //linha 1
        addButton("AC", COLOR_DARK_GRAY,position,0,0);
        addButton("+/-", COLOR_DARK_GRAY,position,1,0);
        addButton("%", COLOR_DARK_GRAY,position,2,0);
        addButton("/", COLOR_ORANGE,position,3,0);

        //linha 2
        addButton("7",COLOR_LIGHT_GRAY,position,0,1);
        addButton("8",COLOR_LIGHT_GRAY,position,1,1);
        addButton("9",COLOR_LIGHT_GRAY,position,2,1);
        addButton("X",COLOR_ORANGE,position,3,1);

        //linha 3
        addButton("4",COLOR_LIGHT_GRAY,position,0,2);
        addButton("5",COLOR_LIGHT_GRAY,position,1,2);
        addButton("6",COLOR_LIGHT_GRAY,position,2,2);
        addButton("-",COLOR_ORANGE,position,3,2);

        //linha 4
        addButton("1",COLOR_LIGHT_GRAY,position,0,3);
        addButton("2",COLOR_LIGHT_GRAY,position,1,3);
        addButton("3",COLOR_LIGHT_GRAY,position,2,3);
        addButton("+",COLOR_ORANGE,position,3,3);

        //linha 5
        position.gridwidth = 2;
        addButton("0",COLOR_LIGHT_GRAY,position,0,4);
        position.gridwidth = 1;
        addButton(",",COLOR_LIGHT_GRAY,position,2,4);
        addButton("=",COLOR_ORANGE,position,3,4);
    }

    private void addButton(String text,Color color, GridBagConstraints position, int x, int y) {

        position.gridx = x;
        position.gridy = y;
        Button button = new Button(text, color);
        add(button,position);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            JButton button = (JButton) e.getSource();
            Memory.getInstance().comand(button.getText());
        }
    }

}
