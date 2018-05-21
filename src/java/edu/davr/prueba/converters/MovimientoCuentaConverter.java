/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.converters;

import edu.davr.prueba.modelo.dao.MovimientoCuentaFacadeLocal;
import edu.davr.prueba.modelo.entidades.MovimientoCuenta;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author davrivas
 */
@FacesConverter(forClass = MovimientoCuenta.class)
public class MovimientoCuentaConverter implements Converter<MovimientoCuenta>{
    
    private MovimientoCuentaFacadeLocal mcfl;

    public MovimientoCuentaConverter() {
        mcfl = CDI.current().select(MovimientoCuentaFacadeLocal.class).get();
    }
    
    @Override
    public MovimientoCuenta getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer id = Integer.valueOf(value);
            return mcfl.find(id);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, MovimientoCuenta arg2) {
        if(arg2 != null){
            return arg2.getId().toString();
        }
        return "";
    }
    
}
