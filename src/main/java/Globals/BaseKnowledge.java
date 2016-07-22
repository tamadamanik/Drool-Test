package Globals;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class BaseKnowledge {

    public static KnowledgeBase kBase ;
    public static boolean isActive = false ;
    
    public BaseKnowledge() {}
    
    public void addKnowledgeBaseFromFile(String filePath) {
        if (!isActive) {
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            isActive = true ;
        }
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add(ResourceFactory.newClassPathResource(
               filePath), ResourceType.DRL); 
        kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
    }
    
    public void addKnowledgeBaseFromString(String newRule) {
        if (!isActive) {
            kBase = KnowledgeBaseFactory.newKnowledgeBase();
            isActive = true ;
        }
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add( ResourceFactory.newByteArrayResource( newRule.getBytes() ), ResourceType.DRL );
        if ( kBuilder.hasErrors() ) {
            System.out.println(kBuilder.getErrors().toString() );
        }                
        kBase.addKnowledgePackages( kBuilder.getKnowledgePackages() );        
    }
    
    public void deleteRuleFromPackage(String packageName, String ruleName) {
        if (!isActive) return ;
        kBase.removeRule(packageName, ruleName);
    }
    
    public void deleteRuleFromWholePackage(String packageName) {
        if (!isActive) return ;
        kBase.removeKnowledgePackage(packageName);
    }

    public void resetKnowledgeBase() {
        if (isActive) kBase = KnowledgeBaseFactory.newKnowledgeBase();
    }
}
