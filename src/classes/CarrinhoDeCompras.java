package classes;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Jogo> jogos;

    public CarrinhoDeCompras() {
        jogos = new ArrayList<>();
    }

    public void adicionarProduto(Jogo jogo) {
        jogos.add(jogo);
    }

    public void removerProduto(Jogo jogo) {
        jogos.remove(jogo);
    }

    public double calcularTotal() {
        double total = 0;
        for (Jogo jogos : jogos) {
            total += jogos.getValor();
        }
        return total;
    }

    public void exibirProdutos() {
        System.out.println("Produtos no carrinho:");
        for (Jogo jogo : jogos) {
            System.out.println(jogo.getTitulo() + " - R$" + jogo.getValor());
        }
    }
}
