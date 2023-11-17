package fabiomarras.u5w3d5.controllers;

import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.service.AuthService;
import fabiomarras.u5w3d5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
}
