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
import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import edu.davr.prueba.modelo.entidades.Sucursal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
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
    private Sucursal sucursalSeleccionada = new Sucursal();
    @EJB
    private CiudadFacadeLocal ciudadfl;
    private List<Ciudad> ciudades;
    @EJB
    private CuentaFacadeLocal cuentafl;
    private List<Cuenta> cuentas;
    private Cuenta cuentaConMasMovUltMes;
    @EJB
    private MovimientoCuentaFacadeLocal mcfl;
//    private Cuenta cuentaMasMovimientos;

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

    public Cuenta getCuentaConMasMovUltMes() {
        Integer mayor = Integer.MIN_VALUE;
        Calendar mes = Calendar.getInstance();
        mes.add(Calendar.MONTH, -1);

        for (Cuenta c : cuentafl.findAllNoCanceladas()) {
            int contador = 0;
            for (MovimientoCuenta mc : c.getMovimientoCuentaList()) {
                if (mc.getFecha().after(mes.getTime())) {
                    contador++;
                }
            }

            if (contador > mayor) {
                cuentaConMasMovUltMes = c;
            }
        }
        return cuentaConMasMovUltMes;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Sucursal getSucursalSeleccionada() {
        return sucursalSeleccionada;
    }

    public void setSucursalSeleccionada(Sucursal sucursalSeleccionada) {
        this.sucursalSeleccionada = sucursalSeleccionada;
    }

    public String registrarSucursal() {
        sfl.create(sucursal);
        sucursal = new Sucursal();
        return "index.xhtml?faces-redirect=true";
    }

    public void seleccionarSucursal(Sucursal s) {
        sucursalSeleccionada = s;
    }

    public String eliminarSucursal() {
        sfl.remove(sucursalSeleccionada);
        sucursalSeleccionada = new Sucursal();
        return "index.xhtml?faces-redirect=true";
    }

}
