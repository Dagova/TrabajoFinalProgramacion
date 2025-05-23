package org.example.Interfaces;

import org.example.BBDD.Gestor;
import org.example.Trabajadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private Image imgFondo;
    public JButton btnInsertarTrabajador;
    public JButton btnEliminarTrabajador;
    public JButton btnmodificarTrabajador;
    public JButton btnListarTrabajadores;
    public JButton btnVentaTrabajador;
    public JButton btnCerrarSesion;
    private GameStoreFrame frame;

    public AdminPanel(GameStoreFrame frame) {
        this.frame = frame;
        imgFondo = new ImageIcon(getClass().getResource("/img/fondo.jpg")).getImage();
        setLayout(new BorderLayout());

        // NAVBAR SUPERIOR
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navBar.setOpaque(false);

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setForeground(Color.black);
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCerrarSesion.setFocusPainted(false);

        navBar.add(btnCerrarSesion);
        add(navBar, BorderLayout.NORTH);

        // CUERPO PRINCIPAL
        JPanel cuerpo = new JPanel(new GridBagLayout());
        cuerpo.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.VERTICAL;

        JLabel label = new JLabel("Bienvenido Administrador");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, 35));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        cuerpo.add(label, gbc);

        btnInsertarTrabajador = new JButton("Insertar Trabajador");
        btnInsertarTrabajador.setForeground(Color.black);
        btnInsertarTrabajador.setFont(new Font("Arial", Font.PLAIN, 20));
        btnInsertarTrabajador.setFocusPainted(false);
        gbc.gridy = 1;
        cuerpo.add(btnInsertarTrabajador, gbc);

        btnEliminarTrabajador = new JButton("Eliminar Trabajador");
        btnEliminarTrabajador.setForeground(Color.black);
        btnEliminarTrabajador.setFont(new Font("Arial", Font.PLAIN, 20));
        btnEliminarTrabajador.setFocusPainted(false);
        gbc.gridy = 2;
        cuerpo.add(btnEliminarTrabajador, gbc);

        btnmodificarTrabajador = new JButton("Modificar Trabajador");
        btnmodificarTrabajador.setForeground(Color.black);
        btnmodificarTrabajador.setFont(new Font("Arial", Font.PLAIN, 20));
        btnmodificarTrabajador.setFocusPainted(false);
        gbc.gridy = 3;
        cuerpo.add(btnmodificarTrabajador, gbc);


        btnListarTrabajadores = new JButton("Listar Trabajadores");
        btnListarTrabajadores.setForeground(Color.black);
        btnListarTrabajadores.setFont(new Font("Arial", Font.PLAIN, 20));
        btnListarTrabajadores.setFocusPainted(false);
        gbc.gridy = 4;
        cuerpo.add(btnListarTrabajadores, gbc);

        add(cuerpo, BorderLayout.CENTER);

        // Acción del botón "Cerrar sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarInicio(); // Vuelve al panel de inicio
            }
        });

        btnInsertarTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana emergente
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnInsertarTrabajador), "Insertar Trabajador", true);
                dialogo.setSize(300, 200);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Campos
                JLabel labelUsuario = new JLabel("Usuario:");
                JTextField campoUsuario = new JTextField(15);

                JLabel labelContrasena = new JLabel("Contraseña:");
                JPasswordField campoContrasena = new JPasswordField(15);

                JButton btnConfirmar = new JButton("Insertar");

                // Añadir componentes
                gbc.gridx = 0; gbc.gridy = 0;
                dialogo.add(labelUsuario, gbc);

                gbc.gridx = 1;
                dialogo.add(campoUsuario, gbc);

                gbc.gridx = 0; gbc.gridy = 1;
                dialogo.add(labelContrasena, gbc);

                gbc.gridx = 1;
                dialogo.add(campoContrasena, gbc);

                gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
                dialogo.add(btnConfirmar, gbc);

                // Acción del botón confirmar
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String usuario = campoUsuario.getText();
                        String contrasena = campoContrasena.getText();

                            if (!usuario.isEmpty() && !contrasena.isEmpty() && !usuario.equals("admin")) {
                            Trabajadores nuevo = new Trabajadores(usuario, contrasena); // Sin encargado
                            Gestor gestor = new Gestor();
                            if (gestor.existeUsuario(nuevo.getUsuario())) {
                                JOptionPane.showMessageDialog(dialogo, "Ese usuario ya está en uso", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                gestor.insertarTrabajador(nuevo);
                                JOptionPane.showMessageDialog(dialogo, "Trabajador insertado correctamente.");
                                dialogo.dispose();
                            }
                        } else if (usuario.equals("admin")) {
                             JOptionPane.showMessageDialog(dialogo, "No puedes insertar un administrador", "Error", JOptionPane.ERROR_MESSAGE);
                         } else {
                            JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });



        btnEliminarTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana emergente
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnEliminarTrabajador), "Eliminar Juego", true);
                dialogo.setSize(400, 200);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);



                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Campos
                JLabel labelTrabajador = new JLabel("Trabajador:");
                JComboBox<String> comboTrabajadores = new JComboBox<>();
                Gestor gestor = new Gestor();
                for (Object nombreTrabajador : gestor.obtenerListaTrabajadores()) {
                    if (!nombreTrabajador.equals("admin")) {
                        comboTrabajadores.addItem((String) nombreTrabajador);
                    }
                }

                JButton btnConfirmar = new JButton("Eliminar");

                // Añadir componentes
                gbc.gridx = 0; gbc.gridy = 0;
                dialogo.add(labelTrabajador, gbc);
                gbc.gridx = 1; gbc.weightx = 0.5;
                dialogo.add(comboTrabajadores, gbc);

                gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 2;
                dialogo.add(btnConfirmar, gbc);

                // Acción del botón confirmar
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreTrabajador = (String) comboTrabajadores.getSelectedItem();
                        // Positivo y entero
                        if (nombreTrabajador == null) {
                            JOptionPane.showMessageDialog(dialogo, "Ningun trabajador seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (!nombreTrabajador.isEmpty()) {
                            Gestor gestor = new Gestor();
                            gestor.eliminarTrabajador(nombreTrabajador);
                            JOptionPane.showMessageDialog(dialogo, "Trabajador eliminado correctamente.");
                            dialogo.dispose();
                        } else {
                            JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });

        btnmodificarTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana emergente
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnInsertarTrabajador), "Insertar Trabajador", true);
                dialogo.setSize(400, 200);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);



                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Campos
                JLabel labelTrabajador = new JLabel("Trabajador:");
                JComboBox<String> comboTrabajadores = new JComboBox<>();
                Gestor gestor = new Gestor();
                for (Object nombreTrabajador : gestor.obtenerListaTrabajadores()) {
                    comboTrabajadores.addItem((String) nombreTrabajador);
                }

                JLabel labelUsuarioNew = new JLabel("Nombre nuevo:");
                JTextField campoUsuarioNew = new JTextField(15);

                JLabel labelContrasena = new JLabel("Contraseña nueva:");
                JPasswordField campoContrasena = new JPasswordField(15);

                JButton btnConfirmar = new JButton("Insertar");

                // Añadir componentes
                gbc.gridx = 0; gbc.gridy = 0;
                dialogo.add(labelTrabajador, gbc);
                gbc.gridx = 1; gbc.weightx = 0.5;
                dialogo.add(comboTrabajadores, gbc);

                gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
                dialogo.add(labelUsuarioNew, gbc);
                gbc.gridx = 1; gbc.weightx = 0.5;
                dialogo.add(campoUsuarioNew, gbc);

                gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
                dialogo.add(labelContrasena, gbc);
                gbc.gridx = 1; gbc.weightx = 0.5;
                dialogo.add(campoContrasena, gbc);

                gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 2;
                dialogo.add(btnConfirmar, gbc);

                // Acción del botón confirmar
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String usuario = (String) comboTrabajadores.getSelectedItem();
                        String usuarioNew = campoUsuarioNew.getText();
                        String contrasena = campoContrasena.getText();

                        if (!usuario.isEmpty() && !contrasena.isEmpty() && !usuarioNew.isEmpty() &&  usuario.equals("admin")) {
                            Gestor gestor = new Gestor();
                            gestor.modificarTrabajador(usuario, "admin", contrasena);
                            JOptionPane.showMessageDialog(dialogo, "Trabajador insertado correctamente. \n Recuerda que el nombre de admin se mantendra siempre");
                            dialogo.dispose();

                        } else if (!usuario.isEmpty() && !contrasena.isEmpty() && !usuarioNew.isEmpty() && gestor.existeUsuario(usuarioNew)) {
                            JOptionPane.showMessageDialog(dialogo, "Nombre de usuario en uso", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (!usuario.isEmpty() && !contrasena.isEmpty() && !usuarioNew.isEmpty()) {
                            Gestor gestor = new Gestor();
                            gestor.modificarTrabajador(usuario, usuarioNew, contrasena);
                            JOptionPane.showMessageDialog(dialogo, "Trabajador modificado correctamente.");
                            dialogo.dispose();

                        } else {
                            JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });

        btnListarTrabajadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gestor gestor = new Gestor();
                gestor.mostrarTrabajadoresEnTabla();
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