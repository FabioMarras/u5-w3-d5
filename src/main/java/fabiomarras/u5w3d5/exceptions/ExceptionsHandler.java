package fabiomarras.u5w3d5.exceptions;

import fabiomarras.u5w3d5.payloads.ErrorsPayload;
import fabiomarras.u5w3d5.payloads.NewUserDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NoPostiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // Genera errore 404
    public ErrorsPayload handleNotFound(NoPostiException e) {
        return new ErrorsPayload(e.getMessage(), new Date());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // Genera errore 404
    public ErrorsPayload handleNotFound(NotFoundException e) {
        return new ErrorsPayload(e.getMessage(), new Date());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Genera errore 400 se lo user viene inserito male
    public NewUserDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorsList() != null) {
            List<String> errorsList = e.getErrorsList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new NewUserDTO(e.getMessage(), null, null,null, null, null, null, null, errorsList);
        } else {
            return new NewUserDTO(e.getMessage(), null, null, null, null, null, null, null, new ArrayList<>());
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // Genera errore 500
    public ErrorsPayload handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsPayload("BUG LATO SERVER, ", new Date());
    }
}
