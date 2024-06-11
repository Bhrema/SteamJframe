package screens;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Tela de Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Campo para email
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Campo para senha
        add(new JLabel("Senha:"), gbc);
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // Botão de login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validateCredentials(emailField.getText(), new String(passwordField.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                        // Aqui você pode redirecionar o usuário para a próxima tela após o login bem-sucedido
                    } else {
                        JOptionPane.showMessageDialog(null, "Email ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao verificar as credenciais.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loginButton, gbc);

        // Texto clicável "Cadastre-se"
        JLabel registerLabel = new JLabel("Cadastre-se");
        registerLabel.setForeground(Color.BLUE.darker());
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Abre a tela de cadastro
                new RegistrationScreen().setVisible(true);
            }
        });
        add(registerLabel, gbc);

        setVisible(true);
    }

    private boolean validateCredentials(String email, String password) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("registrationData.json")));
        JSONObject jsonObject = new JSONObject(content);

        String storedEmail = jsonObject.getString("email");
        String storedPassword = jsonObject.getString("password");

        return email.equals(storedEmail) && password.equals(storedPassword);
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
