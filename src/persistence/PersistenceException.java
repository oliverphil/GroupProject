package persistence;

/**
 * An extension of RunTimeException to signal unexpected behaviour in the persistence methods.
 * 
 * @author Wanja Leuthold - 300424190
 *
 */
@SuppressWarnings("serial")
public class PersistenceException extends RuntimeException {

  public PersistenceException(String message, Exception e) {
    super(message, e);
  }
}
