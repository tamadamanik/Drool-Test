package Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Service.CompanyService;

@RestController
@RequestMapping(value = CompanyController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class CompanyController {

    public static final String BASE_PATH = "/company" ;

    CompanyService companyService = new CompanyService();
    
    @RequestMapping(value = "/all")
    public Map<String,Object> getAllCompany() {
        return companyService.getAllCompany();
    }
}
