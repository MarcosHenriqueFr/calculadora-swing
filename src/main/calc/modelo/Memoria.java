package main.calc.modelo;

import java.util.List;
import java.util.ArrayList;

//Representação lógica
public class Memoria {

    //Enum
    private enum TipoComando {
        ZERAR, NUM, DIV, MULT, SOMA, SUB, IGUAL, VIRGULA, TROCAR
    }

    //Controlo dentro da classe
    private final static Memoria instancia = new Memoria();
    private String textoAtual = "";
    private TipoComando ultimaOperacao = null;
    private String textoBuffer = "";
    private boolean substituir = false;
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

    public void processarComando(String texto){
        TipoComando tipocomando = detectarTipoComando(texto);

        //Execuções
        if(tipocomando == null){
            return;
        } else if(tipocomando == TipoComando.TROCAR && textoAtual.contains("-")){
            textoAtual = textoAtual.substring(1);
        } else if(tipocomando == TipoComando.TROCAR && !textoAtual.contains("-")){
            textoAtual = "-"+textoAtual;
        }else if(tipocomando == TipoComando.ZERAR){
            textoAtual = "";
            textoBuffer = "";
            substituir = false;
            ultimaOperacao = null;
        } else if(tipocomando == TipoComando.NUM || tipocomando == TipoComando.VIRGULA){
            //repoe ou concatena
            textoAtual = substituir ? texto : textoAtual + texto;
            substituir = false;
        } else { //Nas operações
            //Muda o display
            substituir = true;

            //Calculo valor anterior e guardo dps a operação atual
            textoAtual = obterResultadoOperacao();
            textoBuffer = textoAtual; // Possui o valor anterior
            ultimaOperacao = tipocomando;
        }

        observers.forEach(o -> o.valorAlterado(getTextoAtual()));
    }

    private String obterResultadoOperacao() {
        if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL){
            return textoAtual;
        }

        double numBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
        double numAtual = Double.parseDouble(textoAtual.replace(",", "."));
        double resultado = 0;

        if(ultimaOperacao == TipoComando.SOMA){
            resultado = numBuffer + numAtual;
        } else if (ultimaOperacao == TipoComando.SUB) {
            resultado = numBuffer - numAtual;
        } else if (ultimaOperacao == TipoComando.MULT) {
            resultado = numBuffer * numAtual;
        } else if (ultimaOperacao == TipoComando.DIV) {
            resultado = numBuffer / numAtual;
        }

        String resString = String.format("%.2f", resultado).replace(".", ",");
        boolean inteiro = resString.endsWith("0");

        return inteiro ? resString.replace(",0", "") : resString;

    }

    private TipoComando detectarTipoComando(String texto) {
        if(textoAtual.isEmpty() && texto.equals("0")){
            return null;
        }

        try {
            Integer.parseInt(texto);
            return TipoComando.NUM;
        } catch (NumberFormatException e) {
            // Quando n for num

            if(texto.equals("AC")){
                return TipoComando.ZERAR;
            } else if(texto.equals("/")){
                return TipoComando.DIV;
            } else if(texto.equals("*")){
                return TipoComando.MULT;
            } else if(texto.equals("-")){
                return TipoComando.SUB;
            } else if(texto.equals("+")){
                return TipoComando.SOMA;
            } else if(texto.equals(",") && !textoAtual.contains(",")){
                return TipoComando.VIRGULA;
            } else if(texto.equals("=")){
                return TipoComando.IGUAL;
            } else if(texto.equals("±")){
                return TipoComando.TROCAR;
            }

        }
        return null;
    }
}
