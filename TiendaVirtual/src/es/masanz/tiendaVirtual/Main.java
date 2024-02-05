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
        System.out.println(usuarioDAO.crearUsuario(transactionManager.name,"insertBlueprint", new UsuarioDTO("Eh","Ehh","ehhh@gmail.com", "ehh21")));
        System.out.println(usuarioDAO.crearUsuarios(transactionManager.name, "insertBlueprint", "insertsUsuarios"));


        transactionManager.close();

    }
}