package main.calc.visao;

import main.calc.modelo.Memoria;
import main.calc.modelo.MemoriaObserver;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Display extends JPanel implements MemoriaObserver {

    private final JLabel label;

    public Display(){
        //teste de texto
        Memoria.getInstancia().adicionarObserver(this);

        label = new JLabel(Memoria.getInstancia().getTextoAtual());

        setBackground(new Color(46, 49, 50));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("courier", Font.PLAIN, 30));

        //Passa as constantes, e testa os gaps entre os elementos
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 20));
        add(label);
    }

    //Afeta o novo valor do meu display
    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }
}
