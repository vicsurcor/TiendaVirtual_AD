package es.masanz.tiendaVirtual.dao;

import es.masanz.tiendaVirtual.Main;
import es.masanz.tiendaVirtual.db.TransactionManager;
import es.masanz.tiendaVirtual.dto.UsuarioDTO;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDAO {

    private String urlBase = "/sql/";
    private TransactionManager transactionManager;
    private final String extension = ".sql";
    public UsuarioDAO(){

    }
    public boolean crearUsuario(String database, String filename){
        return getTransactionInstructions(database, filename);
    }
    public boolean borrarUsuario(String database, String filename){
        return getTransactionInstructions(database, filename);
    }

    public UsuarioDTO actualizarUsuario(UsuarioDTO usuario, String fullName, String userName, String email, String password){

        UsuarioDTO usuarioAct = usuario;
        usuarioAct.setModificationDate();


        return usuarioAct;

    }
    private boolean getTransactionInstructions(String database, String filename) {
        transactionManager = new TransactionManager(database);

        try {
            File file = (Paths.get(UsuarioDAO.class.getResource(urlBase + filename + extension).toURI()).toFile());
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String sqlUpdate = fileReader.lines().collect(Collectors.joining("\n"));
            return transactionManager.executeSqlUpdate(sqlUpdate);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

//    public List<UsuarioDTO> obtenerUsuario(){
//
//    }

//    public boolean autenticar(String usuario, String contrasena) {
//
//    }

}
