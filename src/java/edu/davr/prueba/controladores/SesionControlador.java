/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.controladores;

import edu.davr.prueba.modelo.dao.UsuarioFacadeLocal;
import edu.davr.prueba.modelo.entidades.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author davrivas
 */
@Named(value = "sesionControlador")
@SessionScoped
public class SesionControlador implements Serializable {

    @EJB
    private UsuarioFacadeLocal ufl;
    private Usuario usuario = new Usuario();
    private String correo;
    private String clave;

    /**
     * Creates a new instance of SesionControlador
     */
    public SesionControlador() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String iniciarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        usuario = ufl.findCorreoClave(correo, clave);

        if (usuario != null) {
            // Reviso el tipo de usuario
            switch (usuario.getTblTiposUsuariosId().getId()) {
                case 1:
                    return "administrador/index.xhtml?faces-redirect=true";
                case 2:
                    return "empleado/index.xhtml?faces-redirect=true";
                case 3:
                    return "cliente/index.xhtml?faces-redirect=true";
            }
        } else {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no  encontrado.", "Revise que haya digitado bien los campos"));
        }

        return "";
    }

    public void cerrarSesion() {
        usuario = new Usuario();
        correo = "";
        clave = "";
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(SesionControlador.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
