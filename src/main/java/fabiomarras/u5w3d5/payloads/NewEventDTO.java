package fabiomarras.u5w3d5.payloads;

import fabiomarras.u5w3d5.entities.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record NewEventDTO(
        @NotNull(message = "Inserisci obbligatoriamente il titolo")
        @Size(min = 2, message = "Scrivi un titolo con più di 2 caratteri")
        String titolo,
        @NotNull(message = "Inserisci obbligatoriamente la descrizione")
        @Size(min = 5, message = "Scrivi una descrizione con più di 5 caratteri")
        String descrizione,
        @NotNull(message = "Inserisci obbligatoriamente la data")
        LocalDate data,
        @NotNull(message = "Inserisci obbligatoriamente il luogo dell'evento")
        String luogo,
        @NotNull(message = "Inserisci obbligatoriamente i posti disponibili")
        @Size(min = 1, message = "Scrivi un valore maggiore di 0 caratteri")
        int posti_dispositivi,
        List<User> user
){
}
