package fabiomarras.u5w3d5.controllers;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.exceptions.BadRequestException;
import fabiomarras.u5w3d5.payloads.NewEventDTO;
import fabiomarras.u5w3d5.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public Page<Event> getEvent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy){
        return eventService.getAllEvent(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable int id){
        return eventService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Event saveNewEvent(@RequestBody @Validated NewEventDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return eventService.save(body);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Event findByIdAndUpdate(@PathVariable int id, @RequestBody Event body){
        return eventService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public void findByIdAndDelete(@PathVariable int id){
        eventService.findByIdAndDelete(id);
    }
}
