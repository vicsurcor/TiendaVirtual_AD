package es.masanz.tiendaVirtual.dao;

import es.masanz.tiendaVirtual.db.TransactionManager;
import es.masanz.tiendaVirtual.dto.UsuarioDTO;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDAO {

    public UsuarioDAO(){

    }


    public boolean crearUsuario(String database, File file){
        TransactionManager transactionManager = new TransactionManager(database);
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String sqlUpdate = fileReader.lines().collect(Collectors.joining("\n"));
            return transactionManager.executeSqlUpdate(sqlUpdate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
