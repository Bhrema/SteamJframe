package classes;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Jogo> jogos;

    public Biblioteca() {
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) {
        jogos.add(jogo);
    }

    public void removerJogo(Jogo jogo) {
        jogos.remove(jogo);
    }
}

