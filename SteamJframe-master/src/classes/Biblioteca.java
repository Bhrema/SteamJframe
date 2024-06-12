package classes;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Jogo> jogos;

    public Biblioteca() {
        this.jogos = new ArrayList<>();
    }

    public void adicionarJogo(Jogo jogo) throws BibliotecaException {
        if (jogo == null) {
            throw new BibliotecaException("O jogo não pode ser nulo.");
        }
        jogos.add(jogo);
    }

    public void removerJogo(Jogo jogo) throws BibliotecaException {
        if (!jogos.remove(jogo)) {
            throw new BibliotecaException("O jogo não está na biblioteca.");
        }
    }

    public List<Jogo> getJogos() {
        return jogos;
    }
}
