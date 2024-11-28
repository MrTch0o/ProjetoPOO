package database;

/**
 *
 * @author DEVENG
 */
public class DbException extends RuntimeException {
    public DbException (String msg){
        super(msg);
        System.out.println("Erro de Conexao");
    }
}
