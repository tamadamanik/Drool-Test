package Controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.EasyRuleService;
import Globals.CurrentUser;

@RestController
@RequestMapping(value = EasyRuleController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class EasyRuleController {
    
    public static final String BASE_PATH = "/easyrule" ;
    
    public EasyRuleService easyRuleService = new EasyRuleService() ;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CurrentUser userLogin(@RequestParam String username, @RequestParam int pin) {
        return easyRuleService.doLogin(username, pin);
    }  
}
