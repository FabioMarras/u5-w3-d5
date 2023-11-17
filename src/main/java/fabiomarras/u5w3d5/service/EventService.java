package fabiomarras.u5w3d5.service;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.exceptions.NotFoundException;
import fabiomarras.u5w3d5.payloads.NewEventDTO;
import fabiomarras.u5w3d5.repositores.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Page<Event> getAllEvent(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventRepository.findAll(pageable);
    }
    public Event findById(int id){
        return eventRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Event save(@RequestBody NewEventDTO body) throws IOException {
        Event newEvent = new Event();
        newEvent.setTitolo(body.titolo());
        newEvent.setDescrizione(body.descrizione());
        newEvent.setData(body.data());
        newEvent.setLuogo(body.luogo());
        newEvent.setPostiDisponibili(body.postiDisponibili());

        Event saveEvent = eventRepository.save(newEvent);
        return saveEvent;
    }

    public Event findByIdAndUpdate(int id, Event body){
        Event newEvent = eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        newEvent.setTitolo(body.getTitolo());
        newEvent.setDescrizione(body.getDescrizione());
        newEvent.setData(body.getData());
        newEvent.setLuogo(body.getLuogo());
        newEvent.setPostiDisponibili(body.getPostiDisponibili());

        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        newEvent.setUsers(event.getUsers());

        return eventRepository.save(newEvent);
    }

    public void findByIdAndDelete(int id){
        Event newEvent = this.findById(id);
        eventRepository.delete(newEvent);
    }

}
