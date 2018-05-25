/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.controladores;

import edu.davr.prueba.modelo.dao.CiudadFacadeLocal;
import edu.davr.prueba.modelo.dao.CuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.MovimientoCuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.TipoCuentaFacadeLocal;
import edu.davr.prueba.modelo.dao.TipoDocumentoFacadeLocal;
import edu.davr.prueba.modelo.dao.TipoUsuarioFacadeLocal;
import edu.davr.prueba.modelo.dao.UsuarioFacadeLocal;
import edu.davr.prueba.modelo.entidades.Ciudad;
import edu.davr.prueba.modelo.entidades.Cuenta;
import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import edu.davr.prueba.modelo.entidades.TipoCuenta;
import edu.davr.prueba.modelo.entidades.TipoDocumento;
import edu.davr.prueba.modelo.entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    private UsuarioFacadeLocal ufl;
    private List<Usuario> clientes;
    private Usuario cliente = new Usuario();
    @EJB
    private MovimientoCuentaFacadeLocal mcfl;
    private List<MovimientoCuenta> movimientos;
    private MovimientoCuenta movimientoCuenta = new MovimientoCuenta();
    @EJB
    private CuentaFacadeLocal cfl;
    private List<Cuenta> cuentas;
    private List<Cuenta> cuentasAbiertas;
    // Para las cuentas de ahorro y corriente
    private List<Cuenta> cuentasAC;
    private List<Cuenta> cuentasACAbiertas;
    private List<Cuenta> cuentasCDTMasUnAño;
    private Cuenta cuentaSeleccionada;
    private Cuenta cuentaConMasMovUltMes;
    @EJB
    private TipoCuentaFacadeLocal tcfl;
    private List<TipoCuenta> tiposCuenta;
    @EJB
    private TipoUsuarioFacadeLocal tufl;
    @EJB
    private TipoDocumentoFacadeLocal tdfl;
    private List<TipoDocumento> tiposDocumento;
    @EJB
    private CiudadFacadeLocal cdfl;
    private List<Ciudad> ciudades;

    /**
     * Creates a new instance of EmpleadoControlador
     */
    public EmpleadoControlador() {
    }

    public List<Cuenta> getCuentas() {
        return cfl.findAll();
    }

    public List<Cuenta> getCuentasAbiertas() {
        return cfl.findAllNoCanceladas();
    }

    public List<Cuenta> getCuentasAC() {
        return cfl.findCuentasAhorrosCorriente();
    }

    public List<Cuenta> getCuentasACAbiertas() {
        return cfl.findCuentasAhorrosAbiertas();
    }

    public List<Cuenta> getCuentasCDTMasUnAño() {
        return cuentasCDTMasUnAño;
    }

    public Cuenta getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }

    public List<MovimientoCuenta> getMovimientos() {
        return mcfl.findAll();
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

    public Cuenta getCuentaConMasMovUltMes() {
        return cuentaConMasMovUltMes;
    }

    public List<Usuario> getClientes() {
        return ufl.findClientes();
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public List<TipoDocumento> getTiposDocumento() {
        return tdfl.findAll();
    }

    public List<Ciudad> getCiudades() {
        return cdfl.findAll();
    }

    public String registrarConsignacion() {
        Date fecha = new Date();
        movimientoCuenta.setFecha(fecha);
        movimientoCuenta.setTipoMovimiento("Consignación");

        Cuenta cuentaActual = cfl.find(movimientoCuenta.getTblCuentasId().getId());
        Double saldoAntiguo = cuentaActual.getSaldo();
        Double saldoNuevo = movimientoCuenta.getValor();
        Double saldo = saldoAntiguo + saldoNuevo;
        cuentaActual.setSaldo(saldo);

        mcfl.create(movimientoCuenta);

        movimientoCuenta = new MovimientoCuenta();

        return "movimientos.xhtml?faces-redirect=true";
    }

    public String registrarRetiro() {
        Date fecha = new Date();
        movimientoCuenta.setFecha(fecha);
        movimientoCuenta.setTipoMovimiento("Retiro");

        Cuenta cuentaActual = cfl.find(movimientoCuenta.getTblCuentasId().getId());
        Double saldoAntiguo = cuentaActual.getSaldo();
        Double saldoNuevo = movimientoCuenta.getValor();
        Double saldo = saldoAntiguo - saldoNuevo;
        cuentaActual.setSaldo(saldo);

        mcfl.create(movimientoCuenta);

        movimientoCuenta = new MovimientoCuenta();

        return "movimientos.xhtml?faces-redirect=true";
    }

    public String registrarCliente() {
        cliente.setTblTiposUsuariosId(tufl.find(3));
        ufl.create(cliente);

        cliente = new Usuario();
        return "index.xhtml?faces-redirect=true";
    }

    public void seleccionarCuenta(Cuenta c) {
        cuentaSeleccionada = c;
    }

    public String cancelarCuentaAC() {
        cuentaSeleccionada.setEstado("Cancelada");
        cfl.edit(cuentaSeleccionada);
        cuentaSeleccionada = new Cuenta();
        return "cuentas.xhtml?faces-redirect=true";
    }

    public String cancelarCuentaCDT() {
        cuentaSeleccionada.setEstado("Cerrada");
        cuentaSeleccionada.setSaldo(0);
        cfl.edit(cuentaSeleccionada);
        cuentaSeleccionada = new Cuenta();
        return "cuentas.xhtml?faces-redirect=true";
    }

}
