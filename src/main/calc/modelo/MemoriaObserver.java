package main.calc.modelo;

@FunctionalInterface
public interface MemoriaObserver {

    void valorAlterado(String novoValor);
}
