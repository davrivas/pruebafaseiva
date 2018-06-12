/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.dao;

import edu.davr.prueba.modelo.entidades.Cuenta;
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
            TypedQuery tq = getEntityManager().createNamedQuery("Cuenta.findAll", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findCuentasAhorrosCorriente() {
        try {
            TypedQuery tq = getEntityManager().createNamedQuery("Cuenta.findCuentasAhorrosCorriente", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findAllNoCanceladas() {
        try {
            TypedQuery tq = getEntityManager().createNamedQuery("Cuenta.findAllNoCanceladas", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findCuentasAhorrosAbiertas() {
        try {
            TypedQuery tq = getEntityManager().createNamedQuery("Cuenta.findCuentasAhorrosCorrienteAbiertas", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Cuenta> findCDTAbiertas() {
        try {
            TypedQuery tq = getEntityManager().createQuery("SELECT c FROM Cuenta c INNER JOIN c.tblTiposCuentasId t WHERE t.id = 3 AND c.estado = 'Abierta' ORDER BY c.fechaApertura DESC", Cuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
