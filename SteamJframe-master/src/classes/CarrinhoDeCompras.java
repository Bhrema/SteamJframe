package classes;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    private List<Jogo> jogos;

    public CarrinhoDeCompras() {
        jogos = new ArrayList<>();
    }

    public void adicionarProduto(Jogo jogo) throws CarrinhoDeComprasException {
        if (jogo == null) {
            throw new CarrinhoDeComprasException("O jogo não pode ser nulo.");
        }
        jogos.add(jogo);
    }

    public void removerProduto(Jogo jogo) throws CarrinhoDeComprasException {
        if (!jogos.remove(jogo)) {
            throw new CarrinhoDeComprasException("O jogo não está no carrinho.");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Jogo jogo : jogos) {
            total += jogo.getValor();
        }
        return total;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }
}