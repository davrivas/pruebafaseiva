/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.converters;

import edu.davr.prueba.modelo.dao.TipoUsuarioFacadeLocal;
import edu.davr.prueba.modelo.entidades.TipoUsuario;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author davrivas
 */
@FacesConverter(forClass = TipoUsuario.class)
public class TipoUsuarioConverter implements Converter<TipoUsuario>{
    
    private TipoUsuarioFacadeLocal cfl;

    public TipoUsuarioConverter() {
        cfl = CDI.current().select(TipoUsuarioFacadeLocal.class).get();
    }
    
    @Override
    public TipoUsuario getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer id = Integer.valueOf(value);
            return cfl.find(id);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, TipoUsuario arg2) {
        if(arg2 != null){
            return arg2.getId().toString();
        }
        return "";
    }
    
}
