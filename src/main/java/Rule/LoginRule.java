package Rule;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.core.BasicRule;

import Globals.CurrentUser; 
import Service.UserService;
import Enum.Role; 

@Rule(name="Login rule")
public class LoginRule  {

    private String username ;
    private int pin ;
    private boolean access ;
    private int id ;
    
    private boolean executed ;
 
    private CurrentUser currentUser ;
    private UserService userService = new UserService() ;
    
    public LoginRule(String username, int pin,boolean access,int id) {
        System.out.println("Created login rule class : "+username+"|"+pin+"|"+id+"|"+access);
        this.username = username; 
        this.pin = pin ;
        currentUser = new CurrentUser() ;
    }
    
    @Condition
    public boolean when() {
        return userService.checkLogin(username, pin) ;
    }

    @Action
    public void then() throws Exception {
        try {
            System.out.println("Login Rule executed");
            UserService userService = new UserService() ;
            currentUser.setName(username);
            currentUser.setRole(Role.CUSTOMER);
            currentUser.setId(userService.checkUserExist(username));
            executed = true ;
        } catch (Exception e) {
            throw e ;
        }
    }
    
    public  boolean isExecuted() {
        return executed ;
    }
    
    public CurrentUser getResult() {
        return currentUser ;
    }
}
