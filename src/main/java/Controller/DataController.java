package Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Service.DataService;


@RestController
@RequestMapping(value = DataController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class DataController {
    
    public static final String BASE_PATH = "/data";
    
    DataService dataService  = new DataService();
    
    @RequestMapping
    public Map<String,Object> setupData() {
        return dataService.insertDummyData();
    }   
}
