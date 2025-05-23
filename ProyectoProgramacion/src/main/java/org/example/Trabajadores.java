package org.example;

public class Trabajadores {

int id_trabajador;
String usuario;
String contrasena;

public Trabajadores(String usuario, String contrasena) {
   setUsuario(usuario);
   setContrasena(contrasena);
}
    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
