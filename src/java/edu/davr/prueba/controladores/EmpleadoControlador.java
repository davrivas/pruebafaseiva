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
    private UsuarioFacadeLocal ufl;
    private List<Usuario> clientes;
    private Usuario cliente = new Usuario();
    @EJB
    private MovimientoCuentaFacadeLocal mcfl;
    private MovimientoCuenta movimientoCuenta = new MovimientoCuenta(); // Diferenciarlos
    @EJB
    private CuentaFacadeLocal cfl;
    private List<Cuenta> cuentas;
    // Para las cuentas de ahorro y corriente
    private List<Cuenta> cuentasAC;
    private Cuenta cuentaSeleccionada;
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

    public List<Cuenta> getCuentasAC() {
        return cfl.findCuentasAhorrosCorriente();
    }

    public Cuenta getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    public void setCuentaSeleccionada(Cuenta cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
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

    public String registrarCliente() {
        cliente.setTblTiposUsuariosId(tufl.find(3));
        ufl.create(cliente);

        cliente = new Usuario();
        return "index.xhtml?faces-redirect=true";
    }

    public void seleccionarCuenta(Cuenta c) {
        cuentaSeleccionada = c;
    }

    public String mostrarIconoCancelarCuenta(Cuenta c) {
        String icono = "";

        if ((c.getTblTiposCuentasId().getId() == 1 || c.getTblTiposCuentasId().getId() == 2) && c.getEstado().equals("Abierta")) {
            icono = "<h:commandLink class=\"btn btn-success\" actionListener=\"#{empleadoControlador.seleccionarCuenta(c)}\" a:data-toggle=\"modal\" a:data-target=\"#modalCancelar\">\n"
                    + "<f:ajax render=\":contenidoModalCancelar\"/>\n"
                    + "<span class=\"fa fa-window-close\"></span>\n"
                    + "</h:commandLink>";
        }

        return icono;
    }

    public boolean findCAC(Cuenta c) {
        return (c.getTblTiposCuentasId().getId() == 1 || c.getTblTiposCuentasId().getId() == 2) && c.getEstado().equals("Abierta");
    }

    public String cancelarCuenta() {
        cuentaSeleccionada.setEstado("Cancelada");
        cfl.edit(cuentaSeleccionada);
        cuentaSeleccionada = new Cuenta();
        return "index.xhtml?faces-redirect=true";
    }

}
