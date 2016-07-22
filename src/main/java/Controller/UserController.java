package Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Globals.CurrentUser;
import Service.UserService;


@RestController
@RequestMapping(value = UserController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class UserController {

    public static final String BASE_PATH = "/user" ;
    
    
    UserService userService = new UserService() ;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CurrentUser userLogin(@RequestParam String username, @RequestParam int pin) {
        return userService.doLogin(username, pin);
    }
   
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public CurrentUser userLogin2(@RequestParam String username, @RequestParam int pin) {
        return userService.doLoginWithDeletedRule(username, pin);
    }
    
    @RequestMapping(value = "/logout")
    public CurrentUser userLogout() {
        return userService.doLogout();
    }
    
    @RequestMapping(value = "/profile")
    public CurrentUser checkCurrentUser() {
        return userService.checkCurrentLogin();
    }
}
