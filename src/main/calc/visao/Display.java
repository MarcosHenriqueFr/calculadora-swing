package main.calc.visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Display extends JPanel {

    private final JLabel label;

    public Display(){
        //teste de texto
        label = new JLabel("1233");

        setBackground(new Color(46, 49, 50));
        //Um pedaço de texto
        label.setForeground(Color.WHITE);
        label.setFont(new Font("courier", Font.PLAIN, 30));

        //Passa as constantes, e testa os gaps entre os elementos
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
        add(label);
    }
}
