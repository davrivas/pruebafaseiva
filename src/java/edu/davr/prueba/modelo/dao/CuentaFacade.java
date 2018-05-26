/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.dao;

import edu.davr.prueba.modelo.entidades.Cuenta;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author davrivas
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> implements CuentaFacadeLocal {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }

    @Override
    public List<Cuenta> findAllOrderByDate() {
        try {
            TypedQuery tq = getEntityManager().createQuery("SELECT c FROM Cuenta c ORDER BY c.fechaApertura DESC", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findCuentasAhorrosCorriente() {
        try {
            TypedQuery tq = getEntityManager().createQuery("SELECT c FROM Cuenta c INNER JOIN c.tblTiposCuentasId t WHERE t.id = 1 OR t.id = 2", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findAllNoCanceladas() {
        try {
            TypedQuery tq = getEntityManager().createQuery("SELECT c FROM Cuenta c WHERE c.estado = 'Abierta'", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findCuentasAhorrosAbiertas() {
        try {
            TypedQuery tq = getEntityManager().createQuery("SELECT c FROM Cuenta c INNER JOIN c.tblTiposCuentasId t WHERE (t.id = 1 OR t.id = 2) AND c.estado = 'Abierta' ORDER By c.fechaApertura DESC", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> CDTMasUnYear() {
        try {
            Calendar hoy = Calendar.getInstance();
            hoy.add(Calendar.YEAR, -1);
            Date year = hoy.getTime();
            TypedQuery tq;
            tq = getEntityManager().createQuery("SELECT c FROM Cuenta c WHERE t.id = 3 AND c.fechaApertura < :year ORDER BY c.fechaApertura DESC", Cuenta.class);
            tq.setParameter("year", year);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Cuenta CuentaMasMovimientosUnMes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
