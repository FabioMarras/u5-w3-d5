package fabiomarras.u5w3d5.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id) {
        super("L'elemento con id: " + id + " non è stato trovato! Provane un altro!");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
