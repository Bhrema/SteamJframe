package screens;

import classes.*;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class GameShowcase extends JFrame {
    private Usuario usuarioLogado; // Substitua pelo usuário logado atual

    public GameShowcase(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        setTitle("Mostruário de Jogos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        // Lê o arquivo JSON e cria componentes para cada jogo
        try {
            String content = new String(Files.readAllBytes(Paths.get("games.json")));
            JSONArray gamesArray = new JSONArray(content);

            for (int i = 0; i < gamesArray.length(); i++) {
                JSONObject gameJson = gamesArray.getJSONObject(i);
                Jogo game = createGameFromJson(gameJson);
                addGameToPanel(gamePanel, game);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scrollPane.setViewportView(gamePanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private Jogo createGameFromJson(JSONObject gameJson) {
        String titulo = gameJson.getString("titulo");
        Desenvolvedor desenvolvedor = new Desenvolvedor(gameJson.getString("desenvolvedor"), "", null, null); // Substitua pelos valores corretos
        int ano = gameJson.getInt("ano");
        double valor = gameJson.getDouble("valor");
        JSONArray categoriasJson = gameJson.getJSONArray("categorias");
        List<String> categorias = categoriasJson.toList().stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return new Jogo(titulo, desenvolvedor, ano, valor, categorias) {
            @Override
            public void jogar() {
                System.out.println("Você está jogando!");
            }
        };
    }

    private void addGameToPanel(JPanel panel, Jogo game) {
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BorderLayout());

        // Adiciona o título e outras informações do jogo
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel("Título: " + game.getTitulo()));
        textPanel.add(new JLabel("Desenvolvedor: " + game.getDesenvolvedor().getNome()));
        textPanel.add(new JLabel("Ano: " + game.getAno()));
        textPanel.add(new JLabel("Preço: R$" + game.getValor()));
        textPanel.add(new JLabel("Categorias: " + String.join(", ", game.getCategorias())));
        gameInfoPanel.add(textPanel, BorderLayout.CENTER);

        // Botão de compra
        JButton buyButton = new JButton("Comprar");
        buyButton.addActionListener(e -> comprarJogo(game));
        gameInfoPanel.add(buyButton, BorderLayout.SOUTH);

        // Botão de adicionar à wishlist
        JButton wishlistButton = new JButton("Adicionar à Wishlist");
        wishlistButton.addActionListener(e -> adicionarAWishlist(game));
        gameInfoPanel.add(wishlistButton, BorderLayout.SOUTH);

        panel.add(gameInfoPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os jogos
    }

    private void comprarJogo(Jogo game) {
        if (usuarioLogado instanceof Jogador) {
            ((Jogador) usuarioLogado).compraJogo(game);
        }
        JOptionPane.showMessageDialog(this, "Jogo comprado e adicionado à biblioteca!");
    }

    private void adicionarAWishlist(Jogo game) {
        if (usuarioLogado instanceof Jogador) {
            ((Jogador) usuarioLogado).adicionaWishlist(game);
        }
        JOptionPane.showMessageDialog(this, "Jogo adicionado à wishlist!");
    }

    public static Usuario obterUsuarioLogado() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("registrationData.json")));
            JSONObject jsonObject = new JSONObject(content);
            return new Jogador(
                    jsonObject.getString("nome"),
                    jsonObject.getString("email"),
                    new CarrinhoDeCompras(),
                    new Biblioteca()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario usuarioLogado = obterUsuarioLogado();
            if (usuarioLogado != null) {
                new GameShowcase(usuarioLogado).setVisible(true);
            } else {
                System.out.println("Não foi possível recuperar as informações do usuário logado.");
            }
        });
    }
}