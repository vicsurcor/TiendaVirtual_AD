package es.masanz.tiendaVirtual;

import es.masanz.tiendaVirtual.db.transactionManager;


public class Main {
    public static void main(String[] args) {

        transactionManager transactionManager = new transactionManager("tiendaVirtual");
        transactionManager.close();

    }
}