package main.calc.visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Barra extends JPanel {
    JButton btnFechar = new BotaoRedondo("X");

    public Barra(GridLayout gridLayout){
        //TODO Implementação da barra

        setSize(232, 30);
        setBackground(new Color(46, 49, 50));

        add(btnFechar);
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        //O listener que detecta o evento padrão de quando ele é clicado
        btnFechar.addActionListener((ActionEvent e) -> System.exit(0));
    }
}
