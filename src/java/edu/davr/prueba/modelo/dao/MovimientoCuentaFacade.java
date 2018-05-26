/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.dao;

import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author davrivas
 */
@Stateless
public class MovimientoCuentaFacade extends AbstractFacade<MovimientoCuenta> implements MovimientoCuentaFacadeLocal {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoCuentaFacade() {
        super(MovimientoCuenta.class);
    }

    @Override
    public List<MovimientoCuenta> movimientosRecientes() {
        try {
            TypedQuery<MovimientoCuenta> tq = getEntityManager().createQuery("SELECT m FROM MovimientoCuenta m ORDER BY m.fecha DESC", MovimientoCuenta.class);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
