package screens;

import javax.swing.*;
import java.awt.*;

public class GameShowcase extends JFrame {

    public GameShowcase() {
        setTitle("Mostruário de Jogos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de rolagem para acomodar a lista de jogos
        JScrollPane scrollPane = new JScrollPane();
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        // Adiciona jogos ao painel
        // Nota: Você precisará substituir "imagePath" pelo caminho da imagem do jogo
        addGameToPanel(gamePanel, "Jogo 1", "Descrição do Jogo 1", "imagePath");
        addGameToPanel(gamePanel, "Jogo 2", "Descrição do Jogo 2", "imagePath");
        // Repita para mais jogos...

        scrollPane.setViewportView(gamePanel);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addGameToPanel(JPanel panel, String title, String description, String imagePath) {
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BorderLayout());

        // Adiciona a imagem do jogo
        ImageIcon gameIcon = new ImageIcon(imagePath);
        JLabel gameImageLabel = new JLabel(gameIcon);
        gameInfoPanel.add(gameImageLabel, BorderLayout.WEST);

        // Adiciona o título e a descrição do jogo
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel(title));
        textPanel.add(new JLabel(description));
        gameInfoPanel.add(textPanel, BorderLayout.CENTER);

        panel.add(gameInfoPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os jogos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameShowcase();
            }
        });
    }
}