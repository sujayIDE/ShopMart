package in.ashokit.restcontroller;

import in.ashokit.entity.User;
import in.ashokit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user)
    {
        User saveUser = userService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable Long id, @RequestBody User user)
    {
        User updateUser = userService.updateUser(id, user);
        if(updateUser!=null)
        {
            return new ResponseEntity<>(updateUser,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUsers = userService.getAllUsers();
        if(!allUsers.isEmpty())
        {
           return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id)
    {
        User user = userService.deleteById(id);
       if(user!=null)
       {
           return new ResponseEntity<>(user,HttpStatus.OK);
       }else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping("/userLogin")
    public ResponseEntity<Boolean> handleLogin(@RequestBody User user){
        boolean useredLogin = userService.userLogin(user);
        if(useredLogin)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
