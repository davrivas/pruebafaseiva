/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.dao;

import edu.davr.prueba.modelo.entidades.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findCorreoClave(String correo, String clave) {
        try {
            TypedQuery<Usuario> tq = getEntityManager().createNamedQuery("Usuario.findByCorreoClave", Usuario.class);
            tq.setParameter("correo", correo);
            tq.setParameter("clave", clave);
            
            return tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
