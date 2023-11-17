package fabiomarras.u5w3d5.repositores;

import fabiomarras.u5w3d5.entities.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Integer> {
}
