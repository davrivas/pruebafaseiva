/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author davrivas
 */
@Entity
@Table(name = "tbl_movimientos_cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovimientoCuenta.findAll", query = "SELECT m FROM MovimientoCuenta m")
    , @NamedQuery(name = "MovimientoCuenta.findById", query = "SELECT m FROM MovimientoCuenta m WHERE m.id = :id")
    , @NamedQuery(name = "MovimientoCuenta.findByFecha", query = "SELECT m FROM MovimientoCuenta m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "MovimientoCuenta.findByTipoMovimiento", query = "SELECT m FROM MovimientoCuenta m WHERE m.tipoMovimiento = :tipoMovimiento")
    , @NamedQuery(name = "MovimientoCuenta.findByValor", query = "SELECT m FROM MovimientoCuenta m WHERE m.valor = :valor")
    /*, @NamedQuery(name = "MovimientoCuenta.cuentaConMasMovimientos",
            query = "SELECT m FROM MovimientoCuenta m WHERE m.id.size = ("
                    + "SELECT MAX(m2.id.size) FROM MovimientoCuenta m2"
                    + ")")
*/})
public class MovimientoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @JoinColumn(name = "tbl_cuentas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cuenta tblCuentasId;

    public MovimientoCuenta() {
    }

    public MovimientoCuenta(Integer id) {
        this.id = id;
    }

    public MovimientoCuenta(Integer id, Date fecha, String tipoMovimiento, double valor) {
        this.id = id;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cuenta getTblCuentasId() {
        return tblCuentasId;
    }

    public void setTblCuentasId(Cuenta tblCuentasId) {
        this.tblCuentasId = tblCuentasId;
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
        if (!(object instanceof MovimientoCuenta)) {
            return false;
        }
        MovimientoCuenta other = (MovimientoCuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.davr.prueba.modelo.entidades.MovimientoCuenta[ id=" + id + " ]";
    }

}
