package es.masanz.tiendaVirtual;

import es.masanz.tiendaVirtual.dao.UsuarioDAO;
import es.masanz.tiendaVirtual.db.TransactionManager;


public class Main {
    public static void main(String[] args) {

        TransactionManager transactionManager = new TransactionManager("tiendaVirtual");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.crearUsuario(transactionManager.name);
        transactionManager.close();

    }
}