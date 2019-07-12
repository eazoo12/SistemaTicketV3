package com.example.sistematicketv2;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable{

    private int idTicket;
    private String Contacto;
    private String nombreIndicente;
    private String ServicioAfectado;
    private String Urgencia;
    private String descripcionIndi;
    private String Imagen;
    private String fechacreacion;
    private String fechaUpdate;
    private int estado;
    private int usuarioID;
    private String TipoTicket;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public String getNombreIndicente() {
        return nombreIndicente;
    }

    public void setNombreIndicente(String nombreIndicente) {
        this.nombreIndicente = nombreIndicente;
    }

    public String getServicioAfectado() {
        return ServicioAfectado;
    }

    public void setServicioAfectado(String servicioAfectado) {
        ServicioAfectado = servicioAfectado;
    }

    public String getUrgencia() {
        return Urgencia;
    }

    public void setUrgencia(String urgencia) {
        Urgencia = urgencia;
    }

    public String getDescripcionIndi() {
        return descripcionIndi;
    }

    public void setDescripcionIndi(String descripcionIndi) {
        this.descripcionIndi = descripcionIndi;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(String fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getTipoTicket() {
        return TipoTicket;
    }

    public void setTipoTicket(String tipoTicket) {
        TipoTicket = tipoTicket;
    }
}
