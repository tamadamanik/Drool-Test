package Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.AccountService;


@RestController
@RequestMapping(value = AccountController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class AccountController {

    public static final String BASE_PATH = "/account" ;
   
    AccountService accountService = new AccountService()  ;
    
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public Map<String,Object> transferTo(@RequestParam int idReceiver, @RequestParam int amount) {
        return accountService.transferTo(idReceiver, amount);
    }
    
    @RequestMapping(value = "/checkmine")
    public Map<String,Object> checkMyAccount() {
        return accountService.checkAccountDetail();
    }
    
    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public Map<String,Object> checkAccountDetail(@PathVariable int id) {
        return accountService.checkAccountDetailWithId(id);
    }
    
    @RequestMapping(value = "/all")
    public Map<String,Object> getAllAccount() {
        return accountService.getAllAccount();
    }
}
