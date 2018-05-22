/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author davrivas
 */
@Entity
@Table(name = "tbl_cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c ORDER BY c.fechaApertura DESC")
    , @NamedQuery(name = "Cuenta.findById", query = "SELECT c FROM Cuenta c WHERE c.id = :id")
    , @NamedQuery(name = "Cuenta.findByNumero", query = "SELECT c FROM Cuenta c WHERE c.numero = :numero")
    , @NamedQuery(name = "Cuenta.findByFechaApertura", query = "SELECT c FROM Cuenta c WHERE c.fechaApertura = :fechaApertura")
    , @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo")
    , @NamedQuery(name = "Cuenta.findByEstado", query = "SELECT c FROM Cuenta c WHERE c.estado = :estado")
    , @NamedQuery(name = "Cuenta.findCuentasAhorrosCorriente", query = "SELECT c FROM Cuenta c INNER JOIN c.tblTiposCuentasId t WHERE t.id = 1 OR t.id = 2")
    , @NamedQuery(name = "Cuenta.findCuentaMasMovimientosUltimoMes", query = "SELECT c FROM Cuenta c INNER JOIN c.movimientoCuentaList m")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "tbl_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario tblUsuariosId;
    @JoinColumn(name = "tbl_sucursal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sucursal tblSucursalId;
    @JoinColumn(name = "tbl_tipos_cuentas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoCuenta tblTiposCuentasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCuentasId")
    private List<MovimientoCuenta> movimientoCuentaList;

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.id = id;
    }

    public Cuenta(Integer id, int numero, Date fechaApertura, double saldo) {
        this.id = id;
        this.numero = numero;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getTblUsuariosId() {
        return tblUsuariosId;
    }

    public void setTblUsuariosId(Usuario tblUsuariosId) {
        this.tblUsuariosId = tblUsuariosId;
    }

    public Sucursal getTblSucursalId() {
        return tblSucursalId;
    }

    public void setTblSucursalId(Sucursal tblSucursalId) {
        this.tblSucursalId = tblSucursalId;
    }

    public TipoCuenta getTblTiposCuentasId() {
        return tblTiposCuentasId;
    }

    public void setTblTiposCuentasId(TipoCuenta tblTiposCuentasId) {
        this.tblTiposCuentasId = tblTiposCuentasId;
    }

    @XmlTransient
    public List<MovimientoCuenta> getMovimientoCuentaList() {
        return movimientoCuentaList;
    }

    public void setMovimientoCuentaList(List<MovimientoCuenta> movimientoCuentaList) {
        this.movimientoCuentaList = movimientoCuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.davr.prueba.modelo.entidades.Cuenta[ id=" + id + " ]";
    }

}
