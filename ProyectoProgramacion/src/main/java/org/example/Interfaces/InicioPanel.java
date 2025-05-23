package org.example.Interfaces;

import org.example.BBDD.Gestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioPanel extends JPanel {
    private Image imgFondo;
    public JButton btnInicio;
    public JButton btnCerrar;
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    GameStoreFrame frame;
    public InicioPanel(GameStoreFrame frame) {
        this.frame = frame;
        imgFondo = new ImageIcon(getClass().getResource("/img/fondo.jpg")).getImage();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Inicio de sesión");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label, gbc);

        // Usuario
        JLabel labelUsuario = new JLabel("Usuario:");
        labelUsuario.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(labelUsuario, gbc);

        campoUsuario = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoUsuario, gbc);

        // Contraseña
        JLabel labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelContraseña, gbc);

        campoContraseña = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoContraseña, gbc);

        // Botones
        btnInicio = new JButton("Entrar");
        btnInicio.setForeground(Color.black);
        btnInicio.setFont(new Font("Arial", Font.PLAIN, 30));
        btnInicio.setFocusPainted(false);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setForeground(Color.black);
        btnCerrar.setFont(new Font("Arial", Font.PLAIN, 30));
        btnCerrar.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnInicio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnCerrar, gbc);

        setOpaque(false);

        // ACCIÓN DEL BOTÓN ENTRAR
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contrasena = campoContraseña.getText();
                Gestor gestor = new Gestor();
                boolean loginCorrecto = gestor.login(usuario, contrasena);


                if (loginCorrecto) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión correcto. ¡Bienvenido! " + usuario);

                    if (usuario.equalsIgnoreCase("admin")) {
                        frame.mostrarAdmin();
                    } else if(!usuario.equalsIgnoreCase("admin")) {
                        frame.mostrarTrabajador(usuario);
                    }
                    campoUsuario.setText("");
                    campoContraseña.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imgFondo != null) {
            g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
