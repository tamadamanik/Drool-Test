package Service;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.definition.rule.Rule;
import org.kie.internal.definition.KnowledgePackage;

import Globals.BaseKnowledge;

public class KnowledgeService {

    BaseKnowledge baseKnowledge = new BaseKnowledge() ;
    
    public Map<String,Object> addKnowledgeFromFile(String filePath) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        baseKnowledge.addKnowledgeBaseFromFile(filePath);
        response.put("process", "ADD KNOWLEDGE FROM FILE");
        response.put("file",filePath);
        return response ;
    }
    
    public Map<String,Object> addKnowledgeFromString(String newRule) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        baseKnowledge.addKnowledgeBaseFromString(newRule);
        response.put("process", "ADD KNOWLEDGE FROM STRING");
        response.put("rule", newRule);
        return response ;
    }
    
    public Map<String,Object> deleteRuleFromPackage(String packageName, String ruleName) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        baseKnowledge.deleteRuleFromPackage(packageName, ruleName);
        response.put("process", "DELETE RULE FROM PACKAGE");
        response.put("rule_name",ruleName) ;
        response.put("package_name", packageName) ;
        return response ;
    }
    
    public Map<String,Object> deletePackage(String packageName) {
        Map<String,Object> response = new HashMap<String,Object>() ;
        baseKnowledge.deleteRuleFromWholePackage(packageName);
        response.put("process", "DELETE PACKAGE");
        response.put("package_name", packageName) ;
        return response ;
    }
    
    public Map<String,Object> resetKnowledgeBase() {
        Map<String,Object> response = new HashMap<String,Object>();
        baseKnowledge.resetKnowledgeBase();
        response.put("process","RESET KNOWLEDGE BASE");
        return response ;
    }
    
    public Map<String,Object> showAllRulesAndPackage() {
        int totalPackage = 0, totalRules;
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("process", "SHOW ALL RULES AND PACKAGES");
        if (BaseKnowledge.isActive) {
            for (KnowledgePackage pckg : BaseKnowledge.kBase.getKnowledgePackages()) {
                totalPackage++;
                totalRules=0 ;
                Map<String,Object> rules = new HashMap<String,Object>() ;
                rules.put("name", pckg.getName());
                for (Rule rule : pckg.getRules()) {
                    totalRules++;
                    rules.put("Rules "+Integer.toString(totalRules), rule.getName());
                }
                rules.put("total rules", totalRules);
                response.put("Package "+Integer.toString(totalPackage),rules);
                
            }
        }
        response.put("total package", totalPackage);
        return response ;
    }
}
