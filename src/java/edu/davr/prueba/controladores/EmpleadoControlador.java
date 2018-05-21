/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.controladores;

import edu.davr.prueba.modelo.dao.CuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.MovimientoCuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.TipoCuentaFacadeLocal;
import edu.davr.prueba.modelo.entidades.Cuenta;
import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import edu.davr.prueba.modelo.entidades.TipoCuenta;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author davrivas
 */
@Named(value = "empleadoControlador")
@SessionScoped
public class EmpleadoControlador implements Serializable {

    @EJB
    private MovimientoCuentaFacadeLocal mcfl;
    private MovimientoCuenta movimientoCuenta = new MovimientoCuenta();
    @EJB
    private CuentaFacadeLocal cfl;
    private List<Cuenta> cuentas;
    // Para las cuentas de ahorro y corriente
    private List<Cuenta> cuentasAC;
    @EJB
    private TipoCuentaFacadeLocal tcfl;
    private List<TipoCuenta> tiposCuenta;

    /**
     * Creates a new instance of EmpleadoControlador
     */
    public EmpleadoControlador() {
    }
    
    public List<Cuenta> getCuentas() {
        return cfl.findAll();
    }

    public List<Cuenta> getCuentasAC() {
        return cfl.findCuentasAhorrosCorriente();
    }

    public List<TipoCuenta> getTiposCuenta() {
        return tcfl.findAll();
    }

    public MovimientoCuenta getMovimientoCuenta() {
        return movimientoCuenta;
    }

    public void setMovimientoCuenta(MovimientoCuenta movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }

    public String registrarConsignacion() {
        Date fecha = new Date();
        movimientoCuenta.setFecha(fecha);
        movimientoCuenta.setTipoMovimiento("Consignación");
        
        Double saldoAntiguo = movimientoCuenta.getTblCuentasId().getSaldo();
        Double saldoNuevo = movimientoCuenta.getValor();
        movimientoCuenta.getTblCuentasId().setSaldo(saldoAntiguo + saldoNuevo);

        mcfl.create(movimientoCuenta);

        movimientoCuenta = new MovimientoCuenta();

        return "index.xhtml?faces-redirect=true";
    }

    public String registrarRetiro() {
        Date fecha = new Date();
        movimientoCuenta.setFecha(fecha);
        movimientoCuenta.setTipoMovimiento("Retiro");
        
        Double saldoAntiguo = movimientoCuenta.getTblCuentasId().getSaldo();
        Double saldoNuevo = movimientoCuenta.getValor();
        movimientoCuenta.getTblCuentasId().setSaldo(saldoAntiguo - saldoNuevo);

        mcfl.create(movimientoCuenta);

        movimientoCuenta = new MovimientoCuenta();

        return "index.xhtml?faces-redirect=true";
    }

}
