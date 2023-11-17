package fabiomarras.u5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;
    private String titolo;
    private String descrizione;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;
    private String avatar;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<User> users;
}
