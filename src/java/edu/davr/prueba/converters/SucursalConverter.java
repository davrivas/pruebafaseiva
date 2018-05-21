/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.converters;

import edu.davr.prueba.modelo.dao.SucursalFacadeLocal;
import edu.davr.prueba.modelo.entidades.Sucursal;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author davrivas
 */
@FacesConverter(forClass = Sucursal.class)
public class SucursalConverter implements Converter<Sucursal>{
    
    private SucursalFacadeLocal cfl;

    public SucursalConverter() {
        cfl = CDI.current().select(SucursalFacadeLocal.class).get();
    }
    
    @Override
    public Sucursal getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer id = Integer.valueOf(value);
            return cfl.find(id);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Sucursal arg2) {
        if(arg2 != null){
            return arg2.getId().toString();
        }
        return "";
    }
    
}
