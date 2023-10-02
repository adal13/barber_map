package com.example.barbershop.Api.entity;

import java.sql.Timestamp;

public class Users {
    private Integer idUser;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String nombreUsuario;
    private String contrasena;
    private Timestamp emailVerified;
    private String rememberToken;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Users {" +
                "idUser =" + idUser +
                ", nombre ='" + nombre + '\'' +
                ", apellido ='" + apellido + '\'' +
                ", telefono ='" + telefono + '\'' +
                ", correo =" + correo +
                ", nombreUsuario ='" + nombreUsuario + '\'' +
                ", contraseña ='" + contrasena + '\'' +
                ", emailVerified =" + emailVerified + '\'' +
                ", rememberToken =" + rememberToken + '\'' +
                ", createdAt =" + createdAt + '\'' +
                ", updatedAt =" + updatedAt +
                '}';
    }

}
