package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        // Configuração do JFrame
        setTitle("Tela de Login");
        setSize(300, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adicionando a imagem local
        ImageIcon imageIcon = new ImageIcon("assets/images/Steam_icon_logo.svg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 20, 200, 50); // Ajuste as dimensões conforme necessário
        add(imageLabel);

        // Criação dos labels
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 80, 25);
        add(emailLabel);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(50, 150, 80, 25);
        add(passwordLabel);

        // Criação dos campos de texto
        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(150, 100, 100, 25);
        add(emailTextField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 100, 25);
        add(passwordField);

        // Criação do texto clicável para cadastro
        JLabel registerLabel = new JLabel("Cadastre-se");
        registerLabel.setBounds(150, 200, 100, 25);
        registerLabel.setForeground(Color.BLUE.darker());
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegistrationScreen();
            }
        });
        add(registerLabel);

        // Torna a tela visível
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen();
            }
        });
    }
}
