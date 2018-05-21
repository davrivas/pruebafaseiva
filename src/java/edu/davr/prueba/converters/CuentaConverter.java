/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.converters;

import edu.davr.prueba.modelo.dao.CuentaFacadeLocal;
import edu.davr.prueba.modelo.entidades.Cuenta;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author davrivas
 */
@FacesConverter(forClass = Cuenta.class)
public class CuentaConverter implements Converter<Cuenta>{
    
    private CuentaFacadeLocal cfl;

    public CuentaConverter() {
        cfl = CDI.current().select(CuentaFacadeLocal.class).get();
    }
    
    @Override
    public Cuenta getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer id = Integer.valueOf(value);
            return cfl.find(id);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Cuenta arg2) {
        if(arg2 != null){
            return arg2.getId().toString();
        }
        return "";
    }
    
}
