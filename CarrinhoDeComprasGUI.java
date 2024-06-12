package screens;

import classes.CarrinhoDeCompras;
import classes.CarrinhoDeComprasException;
import classes.Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarrinhoDeComprasGUI extends JFrame {
    private CarrinhoDeCompras carrinho;
    private DefaultListModel<Jogo> listModel;
    private JList<Jogo> jogoJList;

    public CarrinhoDeComprasGUI() {
        carrinho = new CarrinhoDeCompras();
        listModel = new DefaultListModel<>();
        jogoJList = new JList<>(listModel);

        setTitle("Carrinho de Compras");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField tituloField = new JTextField(20);
        JTextField valorField = new JTextField(10);
        JButton addButton = new JButton("Adicionar Produto");
        JButton removeButton = new JButton("Remover Produto");
        JButton totalButton = new JButton("Calcular Total");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Título:"));
        inputPanel.add(tituloField);
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(valorField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(totalButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(jogoJList), BorderLayout.CENTER);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText();
                String valorText = valorField.getText();
                if (!titulo.isEmpty() && !valorText.isEmpty()) {
                    try {
                        double valor = Double.parseDouble(valorText);
                        Jogo jogo = new Jogo(titulo, valor) {
                            @Override
                            public void jogar() {

                            }
                        };
                        carrinho.adicionarProduto(jogo);
                        listModel.addElement(jogo);
                        tituloField.setText("");
                        valorField.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "O valor deve ser um número", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (CarrinhoDeComprasException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Título e valor não podem ser vazios", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jogo jogoSelecionado = jogoJList.getSelectedValue();
                if (jogoSelecionado != null) {
                    try {
                        carrinho.removerProduto(jogoSelecionado);
                        listModel.removeElement(jogoSelecionado);
                    } catch (CarrinhoDeComprasException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para remover", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = carrinho.calcularTotal();
                JOptionPane.showMessageDialog(null, "Total: R$" + total, "Total", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CarrinhoDeComprasGUI().setVisible(true);
            }
        });
    }
}
