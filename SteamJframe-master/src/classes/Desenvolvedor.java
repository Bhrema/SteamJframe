package classes;

import java.util.ArrayList;
import java.util.List;

public class Desenvolvedor extends Usuario {
    List<String> jogosPublicados;

    public Desenvolvedor(String nome, String email, CarrinhoDeCompras carrinho, Biblioteca biblioteca) {
        super(nome, email, carrinho, biblioteca);
        this.jogosPublicados = new ArrayList<>();
    }

    @Override
    public void enviarEmail() {
        System.out.println("Enviando email para " + nome + " (" + email + ")...");
    }

    void publicarJogo(String jogo) {
        jogosPublicados.add(jogo);
        System.out.println(nome + " publicou o jogo: " + jogo);
    }
}
