package ui;

import model.user;
import repository.usersRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn extends JFrame {
    private final usersRepo repo;

    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public SignIn() {
        repo = new usersRepo();
        initUI();
    }

    private void initUI() {
        setTitle("Login - Inventory Apotek Roxy");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(100, 149, 180)); // Steel Blue

        // Panel atas untuk logo dan welcome message
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // atas, kiri, bawah, kanan

        // Logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("src/main/java/assets/roxy.png");
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH)));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // mainPanel.add(logoLabel, BorderLayout.NORTH);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Inventory Apotek Roxy");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(logoLabel);    
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(welcomeLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel form dengan GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 5, 15, 5); // atas, kiri, bawah, kanan

        // Baris 1: Label Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Email         :"), gbc);

        // Baris 1: Field Email
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(250, 30)); // lebar 200px, tinggi 25px
        formPanel.add(emailField, gbc);

        // Baris 2: Label Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password :"), gbc);

        // Baris 2: Field Password
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30));
        formPanel.add(passwordField, gbc);

        // Baris 3: Show password
        gbc.gridx = 1;
        gbc.gridy = 2;
        JCheckBox showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setOpaque(false);
        showPasswordCheckbox.setForeground(Color.BLACK);
        formPanel.add(showPasswordCheckbox, gbc);

        showPasswordCheckbox.addActionListener(e -> {
            if (showPasswordCheckbox.isSelected()){
                passwordField.setEchoChar((char)0);
            }else{
                passwordField.setEchoChar('•');
            }
        });

        // Baris 4: Tombol Login (dengan efek hover)
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 51, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 102, 204));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 51, 102));
            }
        });
        formPanel.add(loginButton, gbc);

        // Baris 4: Pesan kesalahan
        gbc.gridx = 1;
        gbc.gridy = 4;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.BLACK);
        formPanel.add(messageLabel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Aksi Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        user result = repo.signIn(email, password);

        if (result != null) {
            JOptionPane.showMessageDialog(this,
                "Login succes. Welcome, " + result.getUserName() + "!");
            // TODO: lanjut ke halaman utama
        } else {
            JOptionPane.showMessageDialog(this, "Email and password doesn't match!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignIn().setVisible(true);
        });
    }
}
