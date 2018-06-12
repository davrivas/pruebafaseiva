/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.dao;

import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
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

//    @Override
//    public MovimientoCuenta cuentaConMasMovimientos() {
//        try {
//            Query q = getEntityManager().createNativeQuery("SELECT MAX(contado) FROM (\n"
//                    + "	SELECT COUNT(m.*) AS contado\n"
//                    + "    FROM tbl_movimientos_cuentas m\n"
//                    + "    WHERE MONTH(m.fecha) = MONTH(NOW())\n"
//                    + "    GROUP BY m.tbl_cuentas_id \n"
//                    + ") as max_mov", MovimientoCuenta.class);
//            TypedQuery<MovimientoCuenta> q;
//            q = getEntityManager().createNamedQuery("MovimientoCuenta.cuentaConMasMovimientos", MovimientoCuenta.class);
//            return (MovimientoCuenta) q.getSingleResult();
//        } catch (NoResultException nre) {
//            return null;
//        }
//    }

}
