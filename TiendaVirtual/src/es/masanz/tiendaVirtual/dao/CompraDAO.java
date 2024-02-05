package es.masanz.tiendaVirtual.dao;

import es.masanz.tiendaVirtual.db.TransactionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class CompraDAO {

    private String urlBase = "/sql/";
    private TransactionManager transactionManager;
    private final String extension = ".sql";

    public boolean crearCompras(String database, String filename){
        String sql = getTransactionInstructions(database, filename);
        return transactionManager.executeSqlUpdate(sql);
    }

    public boolean borrarCompras(String database, String filename){
        String sql;
        sql = getTransactionInstructions(database, filename);
        transactionManager.executeSqlUpdate(sql);
        sql = getTransactionInstructions(database, "resetIncrement");
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

}
