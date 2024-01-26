package es.masanz.tiendaVirtual;

import es.masanz.tiendaVirtual.dao.UsuarioDAO;
import es.masanz.tiendaVirtual.db.TransactionManager;

import java.io.Console;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {

        TransactionManager transactionManager = new TransactionManager("tiendavirtual");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        URL url = Main.class.getResource("/sql/insertBlueprint.sql");
        File file;
        try {
            assert url != null;
            file = Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(usuarioDAO.crearUsuario(transactionManager.name,file));
        transactionManager.close();

    }
}