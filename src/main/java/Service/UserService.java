package Service;

import java.util.HashMap;
import java.util.Map;

import Enum.Process;
import Enum.Role;
import Enum.Status;
import Globals.BaseKnowledge;
import Globals.CurrentUser;
import Globals.GlobalData;

import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class UserService {

    CurrentUser currentUser = new CurrentUser() ;
    
    public String getStringRule() {
        String newRules = 
                "package Controller ;\n"+
        
                "import Globals.GlobalData ;\n"+
                "import Globals.CurrentUser ;\n"+
        
                "import Enum.Role ;\n"+
                "import Service.UserService ;\n"+
        
                "rule \"UserLogin\"\n"+
                    "agenda-group \"Login\"\n"+
                    "when\n"+
                        "$currentUser : CurrentUser(role==Role.EMPTY)\n"+
                        "$id : Integer()\n"+
                        "$username : String()\n"+
                        "$check : Boolean()\n"+
                        "eval($check==true)\n"+
                    "then\n"+
                        "System.out.println(\"get update\"+$username+\" | \"+$id+\" | \"+$check);\n"+
                        "$currentUser.setName($username);\n"+
                        "$currentUser.setRole(Role.CUSTOMER);\n"+
                        "$currentUser.setId($id);\n"+
                        "update($currentUser);\n"+
                "end\n";
        return newRules ;
    }
    
    public StatefulKnowledgeSession createNewRules() {
        String newRules = getStringRule(); 
        KnowledgeBase kbase = BaseKnowledge.kBase;           
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        return ksession ;
    }
    
    public StatefulKnowledgeSession createNewRulesWithDeletedRules() {
        String newRules = getStringRule(); 
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( newRules.getBytes() ), ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            System.out.println(kbuilder.getErrors().toString() );
        }        
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();        
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        kbase.removeRule("Controller", "UserLogin");
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        return ksession ;
    }
    
    public int checkUserExist(String username) {
        for (int i=1;i<GlobalData.DataAccount.size();i++) {
            if (GlobalData.DataAccount.get(i).getName().compareTo(username)==0) return i ;
        }
        return -1 ;
    }
    
    public boolean checkLogin(String username, int pin) {
        int id = checkUserExist(username) ;
        System.out.println("Check login service : "+username+"|"+pin+"|"+id);
        return (id!=-1 && GlobalData.DataAccount.get(id).getPin()==pin);
    }

    
    public CurrentUser doLogin(String username, int pin) {
        boolean check = checkLogin(username,pin);
        int id = checkUserExist(username);
//        KieServices ks = KieServices.Factory.get();
//        
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        
//        KieSession kSession = kContainer.newKieSession("User_Rules");
        
//        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kBuilder.add(ResourceFactory.newClassPathResource(
//                "userRules/user.drl"), ResourceType.DRL);
        KnowledgeBase kBase = BaseKnowledge.kBase;
       
        StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
        kSession.getAgenda().getAgendaGroup("Login").setFocus();
        kSession.insert(currentUser);
        kSession.insert(username);
        kSession.insert(id);
        kSession.insert(check);
        kSession.fireAllRules();   
        return currentUser ;
    }
    
    public CurrentUser doLoginWithDeletedRule(String username, int pin) {
        boolean check = checkLogin(username,pin);
        int id = checkUserExist(username);
        StatefulKnowledgeSession kSession = createNewRulesWithDeletedRules() ;
        kSession.getAgenda().getAgendaGroup("Login").setFocus();
        kSession.insert(currentUser);
        kSession.insert(username);
        kSession.insert(id);
        kSession.insert(check);
        kSession.fireAllRules();   
        return currentUser ;        
    }
    
    public CurrentUser doLogout() {        
        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession kSession = kContainer.newKieSession("User_Rules");
        StatefulKnowledgeSession kSession = createNewRules() ;        
        kSession.getAgenda().getAgendaGroup("Logout").setFocus();
        kSession.insert(currentUser);
        kSession.fireAllRules();   
        return currentUser ;
    }   

   public CurrentUser checkCurrentLogin() {        
        return currentUser ;
    }
}
