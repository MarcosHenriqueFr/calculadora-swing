package main.calc.modelo;

import java.util.List;
import java.util.ArrayList;

//Representação lógica
public class Memoria {

    //Controlo dentro da classe
    private static Memoria instancia = new Memoria();
    private String textoAtual = "";
    private final List<MemoriaObserver> observers = new ArrayList<>();

    private Memoria(){

    }

    public static Memoria getInstancia(){
        return instancia;
    }

    //Sempre que tiver uma mudança
    public void adicionarObserver(MemoriaObserver o){
        observers.add(o);
    }

    public String getTextoAtual(){
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void processarComando(String valor){
        if(valor.equals("AC")) {
            textoAtual = "";
        } else {
            textoAtual += valor;
        }

        observers.forEach(o -> o.valorAlterado(getTextoAtual()));
    }
}
