package fabiomarras.u5w3d5.controllers;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.payloads.NewUserDTO;
import fabiomarras.u5w3d5.service.AuthService;
import fabiomarras.u5w3d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy){
        return userService.getAllUser(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userService.findById(id);
    }

    @GetMapping("/me")
    public UserDetails getMyProfile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @PutMapping("/me") //DA QUI POSSIAMO MODIFICARE LO USER, POSSIAMO ANCHE AGGIUNGERE E RIMUOVERE EVENTI
    public UserDetails putMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody NewUserDTO body){
        return userService.findByIdAndUpdate(currentUser.getId(), body);
    }

}
