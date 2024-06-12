package screens;

import classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaGUI extends JFrame {
    private Biblioteca biblioteca;
    private DefaultListModel<Jogo> listModel;
    private JList<Jogo> jogoJList;

    public BibliotecaGUI() {
        biblioteca = new Biblioteca();
        listModel = new DefaultListModel<>();
        jogoJList = new JList<>(listModel);

        setTitle("Biblioteca de Jogos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField nomeField = new JTextField(20);
        JButton addButton = new JButton("Adicionar Jogo");
        JButton removeButton = new JButton("Remover Jogo");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nome do Jogo:"));
        inputPanel.add(nomeField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(jogoJList), BorderLayout.CENTER);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                if (!nome.isEmpty()) {
                    try {
                        Jogo jogo = new Jogo(nome, null, 0, 0, null) {
                            @Override
                            public void jogar() {
                                System.out.println("Você está jogando " + nome + "!");
                            }
                        };
                        biblioteca.adicionarJogo(jogo);
                        listModel.addElement(jogo);
                        nomeField.setText("");
                    } catch (BibliotecaException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "O nome do jogo não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jogo jogoSelecionado = jogoJList.getSelectedValue();
                if (jogoSelecionado != null) {
                    try {
                        biblioteca.removerJogo(jogoSelecionado);
                        listModel.removeElement(jogoSelecionado);
                    } catch (BibliotecaException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um jogo para remover", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BibliotecaGUI().setVisible(true);
            }
        });
    }
}
