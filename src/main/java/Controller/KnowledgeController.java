package Controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Globals.CurrentUser;
import Service.KnowledgeService;

@RestController
@RequestMapping(value = KnowledgeController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class KnowledgeController {
    
    public static final String BASE_PATH = "/knowledge" ;
    
    KnowledgeService knowledgeService = new KnowledgeService() ;
    
    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public Map<String,Object> addKnowledgeFromFile(@RequestParam String filePath) {
        return knowledgeService.addKnowledgeFromFile(filePath);
    }
    
    @RequestMapping(value = "/addRule", method = RequestMethod.POST)
    public Map<String,Object> addKnowledgeFromString(@RequestParam String newRule) {
        return knowledgeService.addKnowledgeFromString(newRule);
    }
    
    @RequestMapping(value = "/deleteRule", method = RequestMethod.POST)
    public Map<String,Object> deleteRuleFromPackage(@RequestParam String packageName, @RequestParam String ruleName) {
        return knowledgeService.deleteRuleFromPackage(packageName, ruleName);
    }
    
    @RequestMapping(value = "/deletePackage", method = RequestMethod.POST)
    public Map<String,Object> deletePackage(@RequestParam String packageName) {
        return knowledgeService.deletePackage(packageName);
    }
    
    @RequestMapping(value = "/reset")
    public Map<String,Object> resetKnowledge() {
        return knowledgeService.resetKnowledgeBase();
    }
    
    @RequestMapping(value = "/show")
    public Map<String,Object> showAllRulesAndPackage() {
        return knowledgeService.showAllRulesAndPackage();
    }
}
