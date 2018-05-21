/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.controladores;

import edu.davr.prueba.modelo.dao.CiudadFacadeLocal;
import edu.davr.prueba.modelo.dao.CuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.MovimientoCuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.SucursalFacadeLocal;
import edu.davr.prueba.modelo.entidades.Ciudad;
import edu.davr.prueba.modelo.entidades.Cuenta;
//import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import edu.davr.prueba.modelo.entidades.Sucursal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author davrivas
 */
@Named(value = "administradorControlador")
@SessionScoped
public class AdministradorControlador implements Serializable {
    
    @EJB
    private SucursalFacadeLocal sfl;
    private List<Sucursal> sucursales;
    private Sucursal sucursal = new Sucursal();
    @EJB
    private CiudadFacadeLocal ciudadfl;
    private List<Ciudad> ciudades;
    @EJB
    private CuentaFacadeLocal cuentafl;
    private List<Cuenta> cuentas;
    @EJB
    private MovimientoCuentaFacadeLocal mcfl;
//    private MovimientoCuenta cuentaMasMovimientos;

    /**
     * Creates a new instance of AdministradorControlador
     */
    public AdministradorControlador() {
    }

    public List<Sucursal> getSucursales() {
        return sfl.findAll();
    }

    public List<Ciudad> getCiudades() {
            return ciudadfl.findAll();
    }

    public List<Cuenta> getCuentas() {
        return cuentafl.findAll();
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

//    public MovimientoCuenta getCuentaMasMovimientos() {
//        return mcfl.cuentaConMasMovimientos();
//    }
    
    public String registrarSucursal() {
        sfl.create(sucursal);
        sucursal = new Sucursal();
        return "index.xhtml?faces-redirect=true";
    }
    
}
