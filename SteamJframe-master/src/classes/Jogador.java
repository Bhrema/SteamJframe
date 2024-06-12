package classes;

import java.util.ArrayList;
import java.util.List;

public class Jogador extends Usuario {
    List<Jogo> jogosComprados;
    List<Jogo> wishList;

    public Jogador(String nome, String email, CarrinhoDeCompras carrinho, Biblioteca biblioteca) {
        super(nome, email, carrinho, biblioteca);
        this.jogosComprados = new ArrayList<>();
        this.wishList = new ArrayList<>();

    }

    @Override
    public void enviarEmail() {
        System.out.println("Enviando email para " + nome + " (" + email + ")...");
    }

    public void compraJogo(Jogo jogo) {
        jogosComprados.add(jogo);
        System.out.println(nome + " comprou o jogo: " + jogo);
    }

    public void adicionaWishlist(Jogo jogo) {
        wishList.add(jogo);
        System.out.println(nome + " Adicionou a wishlist: " + jogo);
    }

    void compraCredito(boolean valor) {
        if (valor) {
            System.out.println(nome + " comprou crédito.");
        } else {
            System.out.println("Compra de crédito falhou.");
        }
    }
}