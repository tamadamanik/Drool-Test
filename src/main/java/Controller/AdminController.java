package Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.AdminService;


@RestController
@RequestMapping(value = AdminController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class AdminController {
    
    public static final String BASE_PATH = "/admin" ;
    
    AdminService adminService = new AdminService();
    
    @RequestMapping(value = "/login", method =RequestMethod.POST)
    public Map<String,Object> adminLogin(@RequestParam String username, @RequestParam String password) {
        return adminService.tellerLogin(username, password);
    }

    
}
