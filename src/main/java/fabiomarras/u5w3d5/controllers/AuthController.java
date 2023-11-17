package fabiomarras.u5w3d5.controllers;

import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.exceptions.BadRequestException;
import fabiomarras.u5w3d5.payloads.NewUserDTO;
import fabiomarras.u5w3d5.payloads.UserLoginDTO;
import fabiomarras.u5w3d5.payloads.UserLoginSuccessDTO;
import fabiomarras.u5w3d5.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body){

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    public User newUser(@RequestBody @Validated NewUserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return authService.save(body);
        }
    }
}
