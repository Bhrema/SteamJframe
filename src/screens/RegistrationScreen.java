package screens;

import classes.*;
import org.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class RegistrationScreen extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField confirmEmailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> countryComboBox;
    private JCheckBox developerCheckBox;
    private JCheckBox termsCheckBox;

    public RegistrationScreen() {
        setTitle("Tela de Cadastro");
        setSize(350, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Campo para nome
        add(new JLabel("Nome:"), gbc);
        nomeField = new JTextField(20);
        add(nomeField, gbc);

        // Campo para email
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Campo para confirmação de email
        add(new JLabel("Confirmação Email:"), gbc);
        confirmEmailField = new JTextField(20);
        add(confirmEmailField, gbc);

        // Campo para senha
        add(new JLabel("Senha:"), gbc);
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // Campo para confirmação de senha
        add(new JLabel("Confirmação de Senha:"), gbc);
        confirmPasswordField = new JPasswordField(20);
        add(confirmPasswordField, gbc);

        // Dropdown para escolha de país
        add(new JLabel("Escolha de país:"), gbc);
        String[] countries = {"Brasil", "EUA", "Italia", "França"};
        countryComboBox = new JComboBox<>(countries);
        add(countryComboBox, gbc);

        // Checkbox para desenvolvedor
        developerCheckBox = new JCheckBox("Desenvolvedor");
        add(developerCheckBox, gbc);

        // Checkbox para termos e condições
        termsCheckBox = new JCheckBox("Concordo com todos os termos");
        add(termsCheckBox, gbc);

        // Botão de registro
        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveRegistrationData();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(registerButton, gbc);

        setVisible(true);
    }

    private void saveRegistrationData() throws IOException {
        // Verifica se todos os campos estão preenchidos
        if (nomeField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                confirmEmailField.getText().trim().isEmpty() ||
                passwordField.getPassword().length == 0 ||
                confirmPasswordField.getPassword().length == 0 ||
                countryComboBox.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se os campos de email e senha coincidem
        if (!emailField.getText().equals(confirmEmailField.getText())) {
            JOptionPane.showMessageDialog(this, "Os emails não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria instâncias de CarrinhoDeCompras e Biblioteca
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        Biblioteca biblioteca = new Biblioteca();

        // Cria um Jogador ou Desenvolvedor com base na seleção do usuário
        Usuario usuario;
        if (developerCheckBox.isSelected()) {
            usuario = new Desenvolvedor(nomeField.getText(), emailField.getText(), carrinho, biblioteca);
        } else {
            usuario = new Jogador(nomeField.getText(), emailField.getText(), carrinho, biblioteca);
        }

        // Chama o método enviarEmail do usuário
        usuario.enviarEmail();

        // Cria um objeto JSON com os dados de cadastro
        JSONObject registrationData = new JSONObject();
        registrationData.put("nome", nomeField.getText());
        registrationData.put("email", emailField.getText());
        registrationData.put("password", new String(passwordField.getPassword()));
        registrationData.put("country", countryComboBox.getSelectedItem().toString());
        registrationData.put("isDeveloper", developerCheckBox.isSelected());
        registrationData.put("agreedToTerms", termsCheckBox.isSelected());

        // Escreve o objeto JSON em um arquivo
        try (FileWriter file = new FileWriter("registrationData.json")) {
            file.write(registrationData.toString(4)); // Indenta o JSON para facilitar a leitura
            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Fecha a janela de cadastro
            new LoginScreen().setVisible(true); // Abre a tela de login
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistrationScreen();
            }
        });
    }
}
