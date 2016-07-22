package Service;

import org.easyrules.api.RulesEngine;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import Globals.CurrentUser; 
import Rule.LoginRule;
import Service.UserService;

public class EasyRuleService {

    UserService userService = new UserService();
    CurrentUser currentUser = new CurrentUser() ;
    
    public CurrentUser doLogin(String username, int pin) {
        int id = userService.checkUserExist(username);
        boolean access = userService.checkLogin(username, pin);
        LoginRule loginRule = new LoginRule(username,pin,access,id);
        RulesEngine rulesEngine = aNewRulesEngine().build();
        rulesEngine.registerRule(loginRule);
        rulesEngine.fireRules();       
        System.out.println(loginRule.getResult().getRole()+"------------");
        currentUser.setId(loginRule.getResult().getId());
        currentUser.setName(loginRule.getResult().getName());
        currentUser.setRole(loginRule.getResult().getRole());
        return currentUser;
    }
}
