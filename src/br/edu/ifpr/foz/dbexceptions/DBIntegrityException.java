package br.edu.ifpr.foz.dbexceptions;

public class DBIntegrityException extends RuntimeException {

    public DBIntegrityException(String msg){
        super(msg);
    }

}
