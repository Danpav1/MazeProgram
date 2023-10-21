package Exceptions;

/**
 * custom exception for bad start or end coordinates
 */
public class BadStartEndCordsException extends RuntimeException {

  public BadStartEndCordsException(String message) {
    super(message);
  }
  
}
