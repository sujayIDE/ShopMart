package in.ashokit.service;

import in.ashokit.entity.User;
import in.ashokit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user)
    {
        userRepository.save(user);
    }

    public User updateUser(Long id, User user)
    {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent())
        {
             user.setUserId(id);
           return userRepository.save(user);
        }else{
            return null;
        }
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User deleteById(Long id)
    {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent())
        {
            userRepository.deleteById(id);
            return byId.get();
        }else{
            return null;
        }
    }

    public User userLogin(User user) {

        User byUserEmail =
                userRepository.findByUserEmail(user.getUserEmail());

        if (byUserEmail != null &&
                byUserEmail.getUserPassword().equals(user.getUserPassword())) {

            return byUserEmail;
        }

        return null;
    }


}
