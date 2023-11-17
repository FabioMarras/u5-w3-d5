package fabiomarras.u5w3d5.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("Non hai i permessi adeguati");
    }
    public UnauthorizedException(String message) {super(message);}
}
