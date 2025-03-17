package main.calc.visao;

import javax.swing.JFrame;

public class Calculadora extends JFrame {

    public Calculadora(){

        setDefaultCloseOperation(EXIT_ON_CLOSE); //herdo de jframe
        setSize(232, 322);
        setLocationRelativeTo(null); //No centro da tela
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
