package Vision;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String label, Color color){
        setText(label);
        setBackground(color);
        setForeground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("sans serif",Font.PLAIN,25));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
