package fabiomarras.u5w3d5.entities;

import fabiomarras.u5w3d5.Enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String avatar;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
