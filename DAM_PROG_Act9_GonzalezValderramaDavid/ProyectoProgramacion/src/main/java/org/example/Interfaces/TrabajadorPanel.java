package org.example.Interfaces;

import org.example.BBDD.Gestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TrabajadorPanel extends JPanel {
    private Image imgFondo;
    public JButton btnRealizarVenta;
    public JButton btnActualizarPrecio;
    public JButton btnActualizarStock;
    public JButton btnAgregarCliente;
    public JButton btnListarJuegos;
    public JButton btnInssertarJuego;
    public JButton btnBorrarJuego;

    public JButton btnCerrarSesion;
    private GameStoreFrame frame;
    Gestor gestor = new Gestor();
    private JLabel labelNombre;


    public TrabajadorPanel(GameStoreFrame frame) {
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

        labelNombre = new JLabel("Bienvenido");
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setFont(new Font("Serif", Font.BOLD, 40));
        labelNombre.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        cuerpo.add(labelNombre, gbc);

        btnAgregarCliente = new JButton("Agregar cliente");
        btnAgregarCliente.setForeground(Color.black);
        btnAgregarCliente.setFont(new Font("Arial", Font.PLAIN, 20));
        btnAgregarCliente.setFocusPainted(false);
        gbc.gridy = 1;
        cuerpo.add(btnAgregarCliente, gbc);

        btnRealizarVenta = new JButton("Realizar venta");
        btnRealizarVenta.setForeground(Color.black);
        btnRealizarVenta.setFont(new Font("Arial", Font.PLAIN, 20));
        btnRealizarVenta.setFocusPainted(false);
        gbc.gridy = 2;
        cuerpo.add(btnRealizarVenta, gbc);


        btnActualizarPrecio = new JButton("Actualizar precio");
        btnActualizarPrecio.setForeground(Color.black);
        btnActualizarPrecio.setFont(new Font("Arial", Font.PLAIN, 20));
        btnActualizarPrecio.setFocusPainted(false);
        gbc.gridy = 3;
        cuerpo.add(btnActualizarPrecio, gbc);


        btnActualizarStock = new JButton("Actualizar Stock");
        btnActualizarStock.setForeground(Color.black);
        btnActualizarStock.setFont(new Font("Arial", Font.PLAIN, 20));
        btnActualizarStock.setFocusPainted(false);
        gbc.gridy = 4;
        cuerpo.add(btnActualizarStock, gbc);

        btnListarJuegos = new JButton("Listar Juegos");
        btnListarJuegos.setForeground(Color.black);
        btnListarJuegos.setFont(new Font("Arial", Font.PLAIN, 20));
        btnListarJuegos.setFocusPainted(false);
        gbc.gridy = 5;
        cuerpo.add(btnListarJuegos, gbc);

        btnInssertarJuego = new JButton("Insertar Juegos");
        btnInssertarJuego.setForeground(Color.black);
        btnInssertarJuego.setFont(new Font("Arial", Font.PLAIN, 20));
        btnInssertarJuego.setFocusPainted(false);
        gbc.gridy = 6;
        cuerpo.add(btnInssertarJuego, gbc);

        btnBorrarJuego = new JButton("Eliminar Juegos");
        btnBorrarJuego.setForeground(Color.black);
        btnBorrarJuego.setFont(new Font("Arial", Font.PLAIN, 20));
        btnBorrarJuego.setFocusPainted(false);
        gbc.gridy = 7;
        cuerpo.add(btnBorrarJuego, gbc);

        add(cuerpo, BorderLayout.CENTER);

        // Acción del botón "Cerrar sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarInicio(); // Vuelve al panel de inicio
            }
        });

        btnRealizarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Diálogo de venta
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnRealizarVenta), "Realizar Venta", true);
                dialogo.setSize(400, 300);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Componentes
                JLabel labelJuego = new JLabel("Juego:");
                JComboBox<String> comboJuegos = new JComboBox<>();
                for (Object nombreJuego : gestor.obtenerListaJuegos()) {
                    comboJuegos.addItem((String) nombreJuego); // formato "1 - FIFA 24"
                }

                JLabel labelCliente = new JLabel("Cliente:");
                JComboBox<String> comboClientes = new JComboBox<>();
                for (Object nombreCliente : gestor.obtenerListaClientes()) {
                    comboClientes.addItem((String) nombreCliente); // formato "2 - Ana Torres"
                }

                JLabel labelCantidad = new JLabel("Cantidad:");
                JTextField campoCantidad = new JTextField();

                JButton btnConfirmarVenta = new JButton("Vender");

                // Añadir componentes al diálogo
                gbc.gridx = 0;
                gbc.gridy = 0;
                dialogo.add(labelJuego, gbc);
                gbc.gridx = 1;
                dialogo.add(comboJuegos, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dialogo.add(labelCliente, gbc);
                gbc.gridx = 1;
                dialogo.add(comboClientes, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                dialogo.add(labelCantidad, gbc);
                gbc.gridx = 1;
                dialogo.add(campoCantidad, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                dialogo.add(btnConfirmarVenta, gbc);

                // Acción del botón "Vender"
                btnConfirmarVenta.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String seleccionJuego = (String) comboJuegos.getSelectedItem();
                        String seleccionCliente = (String) comboClientes.getSelectedItem();
                        String cantidadTexto = campoCantidad.getText();

                        if (seleccionJuego == null || seleccionCliente == null || cantidadTexto.isEmpty()) {
                            JOptionPane.showMessageDialog(dialogo, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }


                        try {
                            int cantidad = Integer.parseInt(cantidadTexto);
                            if (cantidad <= 0) throw new NumberFormatException();
                            int idJuego = gestor.BuscarIDJuego(seleccionJuego);
                            int idCliente = seleccionCliente.split(" - ")[0].trim().isEmpty() ? 0 : Integer.parseInt(seleccionCliente.split(" - ")[0].trim());
                            int idTrabajador = gestor.BuscarIDTrabajador(labelNombre.getText().split(" ")[1]); // fijo o extraído si tienes login


                            LocalDate fecha = LocalDate.now();


                            // Aquí llamamos a insertarVenta con todos los parámetros
                            boolean resultado = gestor.insertarVenta(fecha, idJuego, idCliente, idTrabajador, cantidad);


                            if (resultado) {
                                JOptionPane.showMessageDialog(dialogo, "Venta realizada con éxito.");
                                dialogo.dispose();
                            } else {
                                JOptionPane.showMessageDialog(dialogo, "No hay suficiente stock o error al registrar venta.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(dialogo, "Introduce una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });


        btnAgregarCliente.addActionListener(e -> {
            // pasar frame para que esté centrado y encima de la ventana padre
            JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnAgregarCliente), "Agregar Cliente", true);
            dialogo.setSize(400, 300);
            dialogo.setLayout(new GridBagLayout());
            dialogo.setLocationRelativeTo(null);

            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.insets = new Insets(5, 5, 5, 5);
            gbc2.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblNombre = new JLabel("Nombre:");
            JTextField campoNombre = new JTextField(15);

            JLabel lblApellidos = new JLabel("Apellidos:");
            JTextField campoApellidos = new JTextField(15);

            JLabel lblCorreo = new JLabel("Correo:");
            JTextField campoCorreo = new JTextField(15);

            JLabel lblTelefono = new JLabel("Teléfono:");
            JTextField campoTelefono = new JTextField(15);

            JLabel lblSocio = new JLabel("Número de socio:");
            JTextField campoSocio = new JTextField(10);

            JButton btnInsertar = new JButton("Insertar");

            gbc2.gridx = 0;
            gbc2.gridy = 0;
            dialogo.add(lblNombre, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoNombre, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 1;
            dialogo.add(lblApellidos, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoApellidos, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 2;
            dialogo.add(lblCorreo, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoCorreo, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 3;
            dialogo.add(lblTelefono, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoTelefono, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 4;
            dialogo.add(lblSocio, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoSocio, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 5;
            gbc2.gridwidth = 2;
            dialogo.add(btnInsertar, gbc2);

            btnInsertar.addActionListener(ae -> {
                String nombre = campoNombre.getText().trim();
                String apellidos = campoApellidos.getText().trim();
                String correo = campoCorreo.getText().trim();
                String telefono = campoTelefono.getText().trim();
                String socio = campoSocio.getText().trim();

                if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || telefono.isEmpty() || socio.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Gestor gestor = new Gestor();
                    boolean exito = gestor.insertarCliente(nombre, apellidos, correo, telefono, socio);
                    if (exito) {
                        JOptionPane.showMessageDialog(dialogo, "Cliente insertado correctamente.");
                        dialogo.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialogo, "Error al insertar cliente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            dialogo.setVisible(true);
        });


        btnInssertarJuego.addActionListener(e -> {
            JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnAgregarCliente), "Agregar Juego", true);
            dialogo.setSize(400, 300);
            dialogo.setLayout(new GridBagLayout());
            dialogo.setLocationRelativeTo(null);

            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.insets = new Insets(5, 5, 5, 5);
            gbc2.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblNombre = new JLabel("Nombre:");
            JTextField campoNombre = new JTextField(15);

            JLabel lblApellidos = new JLabel("Genero:");
            JTextField campoApellidos = new JTextField(15);

            JLabel lblCorreo = new JLabel("Pegi:");
            JTextField campoCorreo = new JTextField(15);

            JLabel lblTelefono = new JLabel("Precio:");
            JTextField campoTelefono = new JTextField(15);

            JLabel lblSocio = new JLabel("Stock");
            JTextField campoSocio = new JTextField(10);

            JButton btnInsertar = new JButton("Insertar");

            gbc2.gridx = 0;
            gbc2.gridy = 0;
            dialogo.add(lblNombre, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoNombre, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 1;
            dialogo.add(lblApellidos, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoApellidos, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 2;
            dialogo.add(lblCorreo, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoCorreo, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 3;
            dialogo.add(lblTelefono, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoTelefono, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 4;
            dialogo.add(lblSocio, gbc2);
            gbc2.gridx = 1;
            dialogo.add(campoSocio, gbc2);

            gbc2.gridx = 0;
            gbc2.gridy = 5;
            gbc2.gridwidth = 2;
            dialogo.add(btnInsertar, gbc2);

            btnInsertar.addActionListener(ae -> {
                String nombre = campoNombre.getText().trim();
                String genero = campoApellidos.getText().trim();
                String pegi = campoCorreo.getText().trim();
                String precio = campoTelefono.getText().trim();
                String stock = campoSocio.getText().trim();

                if (nombre.isEmpty() || genero.isEmpty() || pegi.isEmpty() || precio.isEmpty() || stock.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Gestor gestor = new Gestor();
                    boolean exito = gestor.insertarJuego(nombre, genero, pegi, precio, stock);
                    if (exito) {
                        JOptionPane.showMessageDialog(dialogo, "Juego insertado correctamente.");
                        dialogo.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialogo, "Error al insertar Juego", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            dialogo.setVisible(true);
        });

        //BotonActualizar Precio
        btnActualizarPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana emergente
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnActualizarPrecio), "Modificar precio", true);
                dialogo.setSize(400, 200);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);


                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Campos
                JLabel labelJuego = new JLabel("Juego:");
                JComboBox<String> comboJuegos = new JComboBox<>();
                for (Object nombreJuego : gestor.obtenerListaJuegos()) {
                    comboJuegos.addItem((String) nombreJuego);
                }

                JLabel labelPrecio = new JLabel("Precio del juego:");
                JTextField campoPrecio = new JTextField(15);


                JButton btnConfirmar = new JButton("Modificar precio");

                // Añadir componentes
                gbc.gridx = 0;
                gbc.gridy = 0;
                dialogo.add(labelJuego, gbc);
                gbc.gridx = 1;
                gbc.weightx = 0.5;
                dialogo.add(comboJuegos, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 0;
                dialogo.add(labelPrecio, gbc);
                gbc.gridx = 1;
                gbc.weightx = 0.5;
                dialogo.add(campoPrecio, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weightx = 2;
                dialogo.add(btnConfirmar, gbc);

                // Acción del botón confirmar
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreJuego = (String) comboJuegos.getSelectedItem();
                        String precio = campoPrecio.getText();
                        // Positivo, cero o más digitos antes del punto, punto opcional, al menos un digitos despues del punto
                        if (!precio.matches(("[+]?\\d*\\.?\\d+"))) {
                            JOptionPane.showMessageDialog(dialogo, "Debes introducir solo números positivos", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (!nombreJuego.isEmpty() && !precio.isEmpty()) {
                            gestor.actualizarPrecio(nombreJuego, Float.parseFloat(precio));
                            JOptionPane.showMessageDialog(dialogo, "Precio modificado correctamente.");
                            dialogo.dispose();

                        } else {
                            JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });


        //BotonActualizar Stock
        btnActualizarStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana emergente
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnActualizarPrecio), "Modificar stock", true);
                dialogo.setSize(400, 200);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);


                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Campos
                JLabel labelJuego = new JLabel("Juego:");
                JComboBox<String> comboJuegos = new JComboBox<>();
                for (Object nombreJuego : gestor.obtenerListaJuegos()) {
                    comboJuegos.addItem((String) nombreJuego);
                }

                JLabel labelStock = new JLabel("Stock del juego:");
                JTextField campoStock = new JTextField(15);


                JButton btnConfirmar = new JButton("Modificar stock");

                // Añadir componentes
                gbc.gridx = 0;
                gbc.gridy = 0;
                dialogo.add(labelJuego, gbc);
                gbc.gridx = 1;
                gbc.weightx = 0.5;
                dialogo.add(comboJuegos, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 0;
                dialogo.add(labelStock, gbc);
                gbc.gridx = 1;
                gbc.weightx = 0.5;
                dialogo.add(campoStock, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weightx = 2;
                dialogo.add(btnConfirmar, gbc);

                // Acción del botón confirmar
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreJuego = (String) comboJuegos.getSelectedItem();
                        String stock = campoStock.getText();

                        // Positivo y entero
                        if (!stock.matches(("\\d+"))) {
                            JOptionPane.showMessageDialog(dialogo, "Debes introducir solo números positivos", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (!nombreJuego.isEmpty() && !stock.isEmpty()) {
                            gestor.actualizarStock(nombreJuego, Integer.parseInt(stock));
                            JOptionPane.showMessageDialog(dialogo, "Precio modificado correctamente.");
                            dialogo.dispose();

                        } else {
                            JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });

        //BotonActualizar Stock
        btnBorrarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear ventana emergente
                JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(btnActualizarPrecio), "Eliminar Juego", true);
                dialogo.setSize(400, 200);
                dialogo.setLayout(new GridBagLayout());
                dialogo.setLocationRelativeTo(null);


                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                // Campos
                JLabel labelJuego = new JLabel("Juego:");
                JComboBox<String> comboJuegos = new JComboBox<>();
                for (Object nombreJuego : gestor.obtenerListaJuegos()) {
                    comboJuegos.addItem((String) nombreJuego);
                }

                JButton btnConfirmar = new JButton("Eliminar");

                // Añadir componentes
                gbc.gridx = 0;
                gbc.gridy = 0;
                dialogo.add(labelJuego, gbc);
                gbc.gridx = 1;
                gbc.weightx = 0.5;
                dialogo.add(comboJuegos, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 2;
                dialogo.add(btnConfirmar, gbc);

                // Acción del botón confirmar
                btnConfirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreJuego = (String) comboJuegos.getSelectedItem();

                        // Positivo y entero
                        if (!nombreJuego.isEmpty()) {
                            gestor.borrarJuego(nombreJuego);
                            JOptionPane.showMessageDialog(dialogo, "Juego eliminado correctamente.");
                            dialogo.dispose();

                        } else {
                            JOptionPane.showMessageDialog(dialogo, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                dialogo.setVisible(true);
            }
        });


        btnListarJuegos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gestor.mostrarJuegosEnTabla();
            }
        });
    }

    //Pinta el background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imgFondo != null) {
            g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }


    //Actualiza el nombre del trabajador cada vez que lo ingresa de nuevo
    public void actualizarNombre(String nombre) {
        labelNombre.setText("Bienvenido " + nombre);
    }
}