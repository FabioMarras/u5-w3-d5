package fabiomarras.u5w3d5.service;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.exceptions.NoPostiException;
import fabiomarras.u5w3d5.exceptions.NotFoundException;
import fabiomarras.u5w3d5.payloads.NewUserDTO;
import fabiomarras.u5w3d5.repositores.EventRepository;
import fabiomarras.u5w3d5.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    public Page<User> getAllUser(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }
    public User findById(int id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }
    public User findByIdAndUpdate(int id, NewUserDTO body) {
        User user = this.findById(id);

        user.setUsername(body.username());
        user.setName(body.name());
        user.setLastName(body.lastName());
        user.setEmail(body.email());
        user.setAvatar(body.avatar());

        List<Event> eventoDisponibile = new ArrayList<>();

        for (Event event : body.events()) {
            Event idEvent = eventService.findById(event.getId());

            if (idEvent != null && idEvent.getPostiDisponibili() > 0) {
                eventoDisponibile.add(event);
            } else {
                throw new NoPostiException("L'evento con ID " + event.getId() + " non ha posti disponibili.");
            }
        }

        user.setEvents(eventoDisponibile);

        return userRepository.save(user);
    }


    public void findByIdAndDelete(int id){
        User user = this.findById(id);
            userRepository.delete(user);
        }
    }

