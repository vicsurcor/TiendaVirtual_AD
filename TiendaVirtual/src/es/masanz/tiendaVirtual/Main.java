package es.masanz.tiendaVirtual;

import es.masanz.tiendaVirtual.dao.UsuarioDAO;
import es.masanz.tiendaVirtual.db.TransactionManager;
import es.masanz.tiendaVirtual.dto.UsuarioDTO;

import java.io.Console;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {

        TransactionManager transactionManager = new TransactionManager("tiendavirtual");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println(usuarioDAO.crearUsuario(transactionManager.name,"insertBlueprint"));
        System.out.println(usuarioDAO.actualizarUsuario(new UsuarioDTO("Antonio","AA212","rrevreq@gmail.com", "12ff43"),"Erre","eeee@gmail.com","13rr12",transactionManager.name,"updateBlueprint"));
        System.out.println(usuarioDAO.borrarUsuario(transactionManager.name, "deleteBlueprint"));

        transactionManager.close();

    }
}