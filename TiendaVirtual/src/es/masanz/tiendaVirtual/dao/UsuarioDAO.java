package es.masanz.tiendaVirtual.dao;

import es.masanz.tiendaVirtual.dto.UsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public UsuarioDAO(){

    }


    public UsuarioDTO crearUsuario(String fullName, String userName, String email, String password){

        UsuarioDTO usuario = new UsuarioDTO(fullName,userName,email,password);

        return usuario;
    }

    public boolean borrarUsuario(UsuarioDTO usuario){


        return true;
    }

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuario, String fullName, String userName, String email, String password){

        UsuarioDTO usuarioAct = usuario;
        usuarioAct.setModificationDate();


        return usuarioAct;

    }

//    public List<UsuarioDTO> obtenerUsuario(){
//
//    }

//    public boolean autenticar(String usuario, String contrasena) {
//
//    }

}
