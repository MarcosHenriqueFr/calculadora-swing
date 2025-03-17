package main.calc.visao;

import javax.swing.JFrame;
import java.awt.*;

//Essa é a tela principal
public class Calculadora extends JFrame {

    public Calculadora(){

        organizarLayout();

        setDefaultCloseOperation(EXIT_ON_CLOSE); //herdo de jframe
        setSize(232, 322);
        setUndecorated(true);
        setLocationRelativeTo(null); //No centro da tela
        setVisible(true);
    }

    private void organizarLayout() {
        //Só vai funcionar se for chamado
        setLayout(new BorderLayout());

        Barra barra = new Barra(new GridLayout(2, 1, 1, 1));
        Display display = new Display();
        Teclado teclado = new Teclado();

        display.setPreferredSize(new Dimension(233, 60));

        //Diferentes tipos de adição
        add(display, BorderLayout.NORTH);
        add(teclado, BorderLayout.CENTER);
        add(barra, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
