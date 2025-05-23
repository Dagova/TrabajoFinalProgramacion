package org.example.BBDD;


import org.example.Trabajadores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;


public class Gestor {

    private static final String URL = "jdbc:mysql://localhost:3306/GameStop";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    public static String nombre;

    // Intentar establecer una conexión con la base de datos
    public static Connection conectar() {
        try {
            return getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            //muestra error por consola
            e.printStackTrace();
            return null;
        }
    }

    public void mostrarJuegosEnTabla() {
        // codigo sql
        String sql = "SELECT * FROM juegos";

        // se establece la conexión
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            // Crear modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Genero");
            modelo.addColumn("PEGI");
            modelo.addColumn("Precio");
            modelo.addColumn("Stock");

            // sirve para verificar que hayan más datos
            while (rs.next()) {
                // coge los atributos de juego
                int id_juego = rs.getInt("id_juego");
                String nombre = rs.getString("nombre");
                String genero = rs.getString("genero");
                int pegi = rs.getInt("pegi");
                float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");

                //añade la fila al modelo de la tabla
                modelo.addRow(new Object[]{id_juego, nombre, genero, pegi, precio, stock});
            }

            // Crear tabla con el modelo

            // modelo es un objeto que contiene los datos y la estructura de la tabla.
            // Al pasar modelo al constructor, le dices a la tabla qué datos mostrar y cómo organizarlos.
            JTable tabla = new JTable(modelo);

            // para que se pueda desplazar
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Crear ventana para mostrar la tabla
            JDialog dialogo = new JDialog((Frame) null, "Lista de Juegos", true);
            dialogo.setUndecorated(true);
            dialogo.setSize(400, 300);
            dialogo.setLocationRelativeTo(null);
            dialogo.setLayout(new BorderLayout());
            dialogo.add(scrollPane, BorderLayout.CENTER);

            // Botón cerrar
            JButton btnCerrar = new JButton("Cerrar");
            // cierra ventana
            btnCerrar.addActionListener(e -> dialogo.dispose());
            dialogo.add(btnCerrar, BorderLayout.SOUTH);

            dialogo.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar juegos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //Panel Administrador
    public void insertarTrabajador(Trabajadores a) {
        // codigo sql para insertar
        String sql = "INSERT INTO trabajadores (usuario, contrasena) VALUES (?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            // sirve para decir que es la interrogacion
            pst.setString(1, a.getUsuario());
            pst.setString(2, a.getContrasena());

            // ejecuta el sql
            pst.executeUpdate();
            System.out.println("Trabajador insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTrabajador(String usuario) {
        // codigo sql para eliminar
        String sql = "DELETE FROM trabajadores WHERE usuario = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, usuario);

            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Trabajador eliminado correctamente.");
            } else {
                System.out.println("No se encontró un trabajador con ese usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarTrabajador(String usuarioActual, String nuevoUsuario, String nuevaContrasena) {
        String sql = "UPDATE trabajadores SET usuario = ?, contrasena = ? WHERE usuario = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nuevoUsuario);
            pst.setString(2, nuevaContrasena);
            pst.setString(3, usuarioActual);

            int filasAfectadas = pst.executeUpdate();
            // mira si se han hecho cambios
            if (filasAfectadas > 0) {
                System.out.println("Trabajador modificado correctamente.");
            } else {
                System.out.println("No se encontró un trabajador con el usuario proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeUsuario(String nombreUsuario) {
        String sql = "SELECT usuario FROM trabajadores WHERE usuario = ?";

        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombreUsuario);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); // Si hay al menos una fila, igual a true
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void mostrarTrabajadoresEnTabla() {
        String sql = "SELECT id_trabajador, usuario, contrasena FROM trabajadores";

        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            // Crear modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Usuario");
            modelo.addColumn("Contraseña");

            // Llenar modelo con los datos de la base de datos
            while (rs.next()) {
                int id_trabajador = rs.getInt("id_trabajador");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                modelo.addRow(new Object[]{id_trabajador, usuario, contrasena});
            }

            // Crear tabla con el modelo
            JTable tabla = new JTable(modelo);
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Crear ventana para mostrar la tabla
            JDialog dialogo = new JDialog((Frame) null, "Lista de Trabajadores", true);
            dialogo.setSize(400, 300);
            dialogo.setLocationRelativeTo(null);
            dialogo.setLayout(new BorderLayout());
            dialogo.add(scrollPane, BorderLayout.CENTER);

            // Botón cerrar
            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(e -> dialogo.dispose());
            dialogo.add(btnCerrar, BorderLayout.SOUTH);

            dialogo.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar trabajadores", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Panel trabajador
    public ArrayList<Object> obtenerListaJuegos() {
        ArrayList<Object> lista = new ArrayList<>();
        try (Connection con = conectar(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT nombre FROM juegos")) {
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Object> obtenerListaTrabajadores() {
        ArrayList<Object> lista = new ArrayList<>();
        try (Connection con = conectar(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT usuario FROM trabajadores")) {
            while (rs.next()) {
                lista.add(rs.getString("usuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Object> obtenerListaClientes() {
        ArrayList<Object> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nombre, apellidos, numerosocio FROM clientes";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String numeroSocio = rs.getString("numerosocio");
                lista.add(id + " - " + nombre + " " + apellidos + " - " + numeroSocio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }






    //Tabla juego
    public boolean insertarVenta(LocalDate fecha, int idJuego, int idCliente, int idTrabajador, int cantidad) {
        String sql = "INSERT INTO ventas (fecha, id_juego, id_cliente, id_trabajador, cantidad) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(fecha));
            pstmt.setInt(2, idJuego);
            pstmt.setInt(3, idCliente);
            pstmt.setInt(4, idTrabajador);
            pstmt.setInt(5, cantidad);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public void borrarJuego(String nombreJuego) {
        String sql = "DELETE FROM juegos WHERE nombre = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombreJuego);
            int filas = pst.executeUpdate();
            if (filas > 0) {
                System.out.println("Juego eliminado correctamente.");
            } else {
                System.out.println("No se encontró el juego con nombre: " + nombreJuego);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarStock(String nombreJuego, int nuevoStock) {
        String sql = "UPDATE juegos SET stock = ? WHERE nombre = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, nuevoStock);
            pst.setString(2, nombreJuego);
            int filas = pst.executeUpdate();
            if (filas > 0) {
                System.out.println("Stock actualizado correctamente.");
            } else {
                System.out.println("No se encontró el juego con ID " + nombreJuego);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPrecio(String nombreJuego, float nuevoPrecio) {
        String sql = "UPDATE juegos SET precio = ? WHERE nombre = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDouble(1, nuevoPrecio);
            pst.setString(2, nombreJuego);
            int filas = pst.executeUpdate();
            if (filas > 0) {
                System.out.println("Precio actualizado correctamente.");
            } else {
                System.out.println("No se encontró el juego titulado: " + nombreJuego);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertarCliente(String nombre, String apellidos, String correo, String telefono, String numeroSocio) {
        String sql = "INSERT INTO clientes (nombre, apellidos, correo, telefono, numerosocio) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, correo);
            ps.setString(4, telefono);
            ps.setString(5, numeroSocio);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertarJuego(String nombre, String genero, String pegi, String precio, String stock) {
        String sql = "INSERT INTO juegos (nombre, genero, pegi, precio, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, genero);
            ps.setString(3, pegi);
            ps.setString(4, precio);
            ps.setString(5, stock);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int BuscarIDJuego(String nombreJuego) {
        String sql = "SELECT id_juego FROM juegos WHERE nombre = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombreJuego);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_juego");
            } else {
                System.out.println("No se encontró un juego con ese nombre.");
                return -1; // Valor para indicar que no se encontró el juego
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Valor para indicar error
        }
    }

    public int BuscarIDTrabajador(String nombreUsuario) {
        String sql = "SELECT id_trabajador FROM trabajadores WHERE usuario = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombreUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_trabajador");
            } else {
                System.out.println("No se encontró un trabajador con ese nombre.");
                return -1; // Valor para indicar que no se encontró el juego
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Valor para indicar error
        }
    }



    //Logueo inicial
    public boolean login(String usuario, String contrasena) {
        String sql = "SELECT usuario, contrasena FROM trabajadores WHERE usuario = ? AND contrasena = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, usuario);
            pst.setString(2, contrasena);
            ResultSet rs = pst.executeQuery();

            nombre = usuario;

            if (rs.next()) {
                System.out.println("Login correcto. Bienvenido, " + rs.getString("usuario"));
                return true;
            } else {
                System.out.println("Login fallido. Usuario o contraseña incorrectos.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
