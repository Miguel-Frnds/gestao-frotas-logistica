package models.exceptions.gestaoDeVeiculos;

public class PlacaInvalidaException extends RuntimeException {
    public PlacaInvalidaException(String message) {
        super(message);
    }
}
