/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.davr.prueba.converters;

import edu.davr.prueba.modelo.dao.UsuarioFacadeLocal;
import edu.davr.prueba.modelo.entidades.Usuario;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author davrivas
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario>{
    
    private UsuarioFacadeLocal cfl;

    public UsuarioConverter() {
        cfl = CDI.current().select(UsuarioFacadeLocal.class).get();
    }
    
    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Integer id = Integer.valueOf(value);
            return cfl.find(id);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Usuario arg2) {
        if(arg2 != null){
            return arg2.getId().toString();
        }
        return "";
    }
    
}
