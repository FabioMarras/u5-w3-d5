package fabiomarras.u5w3d5.controllers;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.exceptions.BadRequestException;
import fabiomarras.u5w3d5.payloads.NewEventDTO;
import fabiomarras.u5w3d5.payloads.NewUserDTO;
import fabiomarras.u5w3d5.repositores.UserRepository;
import fabiomarras.u5w3d5.service.EventService;
import fabiomarras.u5w3d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

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
    @ResponseStatus(HttpStatus.OK)
    public Event findByIdAndUpdate(@PathVariable int id, @RequestBody Event body){
        return eventService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id){
        eventService.findByIdAndDelete(id);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public String uploadFile(@RequestParam("avatar") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return eventService.uploadPicture(body);
    }

    @GetMapping("/myEvents") //ENDPOINT ESCLUSIVO CHE RITORNA LA LISTA DEGLI EVENTI DEL PROFILO
    public List<Event> getMyEvents(@AuthenticationPrincipal User currentUser) {
        return currentUser.getEvents();
    }
}
