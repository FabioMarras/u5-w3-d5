package fabiomarras.u5w3d5.repositores;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
