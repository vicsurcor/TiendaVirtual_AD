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



        transactionManager.close();

    }
}