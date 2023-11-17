package fabiomarras.u5w3d5.service;

import fabiomarras.u5w3d5.entities.User;
import fabiomarras.u5w3d5.exceptions.NotFoundException;
import fabiomarras.u5w3d5.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
}
