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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDAO {

    private String urlBase = "/sql/";
    private TransactionManager transactionManager;
    private final String extension = ".sql";
    public UsuarioDAO(){

    }
    public boolean crearUsuario(String database, String filename){
        String sql = getTransactionInstructions(database, filename);
        return transactionManager.executeSqlUpdate(sql);
    }
    public boolean borrarUsuario(String database, String filename){
        String sql;
        sql = getTransactionInstructions(database, filename);
        transactionManager.executeSqlUpdate(sql);
        sql = getTransactionInstructions(database, "resetIncrement");
        return transactionManager.executeSqlUpdate(sql);
    }

    public boolean actualizarUsuario(UsuarioDTO usuario, String fullName, String email, String password, String database, String filename){

        String sql;
        int count = 0;
        String[] strings = new String[]{fullName,email,password, usuario.getFullName()};
        sql = getTransactionInstructions(database, filename);
        for (int i = 0; i < sql.length(); i++)
        
        return transactionManager.executeSqlUpdate(sql);



    }
    private String getTransactionInstructions(String database, String filename) {
        transactionManager = new TransactionManager(database);

        try {
            File file = (Paths.get(UsuarioDAO.class.getResource(urlBase + filename + extension).toURI()).toFile());
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            return fileReader.lines().collect(Collectors.joining("\n"));
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
