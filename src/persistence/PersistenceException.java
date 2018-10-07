package persistence;

@SuppressWarnings("serial")
public class PersistenceException extends RuntimeException {

  public PersistenceException(String message) {
    super(message);
  }
}
