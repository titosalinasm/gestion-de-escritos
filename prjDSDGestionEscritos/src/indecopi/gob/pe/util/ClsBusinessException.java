package indecopi.gob.pe.util;


public class ClsBusinessException extends Exception {

  public ClsBusinessException(String errorMessage) {
    super(errorMessage);
  }
  
  public ClsBusinessException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
  }
}
