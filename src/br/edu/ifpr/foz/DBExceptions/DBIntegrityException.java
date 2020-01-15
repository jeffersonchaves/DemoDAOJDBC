package br.edu.ifpr.foz.DBExceptions;

public class DBIntegrityException extends RuntimeException {

    public DBIntegrityException(String msg){
        super(msg);
    }

}
