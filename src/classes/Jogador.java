package classes;

import java.util.ArrayList;
import java.util.List;

public class Jogador extends Usuario {
    List<String> jogosComprados;

    public Jogador(String nome, String email, CarrinhoDeCompras carrinho, Biblioteca biblioteca) {
        super(nome, email, carrinho, biblioteca);
        this.jogosComprados = new ArrayList<>();
    }

    @Override
    public void enviarEmail() {
        System.out.println("Enviando email para " + nome + " (" + email + ")...");
    }

    public void compraJogo(String jogo) {
        jogosComprados.add(jogo);
        System.out.println(nome + " comprou o jogo: " + jogo);
    }

    void compraCredito(boolean valor) {
        if (valor) {
            System.out.println(nome + " comprou crédito.");
        } else {
            System.out.println("Compra de crédito falhou.");
        }
    }
}