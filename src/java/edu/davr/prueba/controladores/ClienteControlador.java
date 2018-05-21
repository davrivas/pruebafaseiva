/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author davrivas
 */
@Named(value = "clienteControlador")
@SessionScoped
public class ClienteControlador implements Serializable {
    
    /**
     * Creates a new instance of ClienteControlador
     */
    public ClienteControlador() {
    }
    
}
