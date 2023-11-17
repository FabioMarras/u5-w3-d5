package fabiomarras.u5w3d5.repositores;

import fabiomarras.u5w3d5.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
