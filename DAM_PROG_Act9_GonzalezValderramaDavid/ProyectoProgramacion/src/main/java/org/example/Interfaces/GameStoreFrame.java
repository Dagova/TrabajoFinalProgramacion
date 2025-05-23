package org.example.Interfaces;

import javax.swing.*;

public class GameStoreFrame extends JFrame {
    private JFrame frame;
    private InicioPanel panelInicio;
    private AdminPanel panelAdmin;
    private TrabajadorPanel panelTrabajador;


    public GameStoreFrame() {
        //Estetica
        setTitle("Tienda de juegos");
        setSize(400, 800);
        setLocationRelativeTo(null);
        setUndecorated(true);


        //Definimos paneles que estarn incluidos en el frame
        panelInicio = new InicioPanel(this);
        panelAdmin = new AdminPanel(this);
        panelTrabajador = new TrabajadorPanel(this);
        add(panelInicio);

        mostrarInicio();

        setVisible(true);
    }


    //Metodos para pintar los paneles en el frame

    public void mostrarInicio() {
        setContentPane(panelInicio);
        revalidate();
        repaint();
    }

    public void mostrarAdmin() {
        setContentPane(panelAdmin);
        revalidate();
        repaint();
    }

    public void mostrarTrabajador(String nombreUsuario) {
        panelTrabajador.actualizarNombre(nombreUsuario);
        setContentPane(panelTrabajador);
        revalidate();
        repaint();
    }
}
