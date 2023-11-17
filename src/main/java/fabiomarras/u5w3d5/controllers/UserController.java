package fabiomarras.u5w3d5.controllers;

import fabiomarras.u5w3d5.entities.Event;
import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.exceptions.BadRequestException;
import fabiomarras.u5w3d5.payloads.NewUserDTO;
import fabiomarras.u5w3d5.service.AuthService;
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

import java.io.IOException;

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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public User saveNewUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return authService.save(body);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    @ResponseStatus(HttpStatus.OK)
    public User findByIdAndUpdate(@PathVariable int id, @RequestBody @Validated NewUserDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return userService.findByIdAndUpdate(id, body);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id){
        userService.findByIdAndDelete(id);
    }

    @GetMapping("/me")
    public UserDetails getMyProfile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @PutMapping("/me") //DA QUI POSSIAMO MODIFICARE LO USER, POSSIAMO ANCHE AGGIUNGERE E RIMUOVERE EVENTI
    @ResponseStatus(HttpStatus.OK)
    public UserDetails putMyProfile(@AuthenticationPrincipal User currentUser, @RequestBody NewUserDTO body){
        return userService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getProfile(@AuthenticationPrincipal User currentUser){
        userService.findByIdAndDelete(currentUser.getId());
    };
}
