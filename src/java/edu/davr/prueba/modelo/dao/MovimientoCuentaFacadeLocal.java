/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.modelo.dao;

import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author davrivas
 */
@Local
public interface MovimientoCuentaFacadeLocal {

    void create(MovimientoCuenta movimientoCuenta);

    void edit(MovimientoCuenta movimientoCuenta);

    void remove(MovimientoCuenta movimientoCuenta);

    MovimientoCuenta find(Object id);

    List<MovimientoCuenta> findAll();

    List<MovimientoCuenta> findRange(int[] range);

    int count();

    List<MovimientoCuenta> movimientosRecientes();

}
