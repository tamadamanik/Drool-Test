package Service;

import Globals.GlobalData;

import Globals.CurrentUser;

import java.util.HashMap;
import java.util.Map;
import Enum.*;


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

public class AdminService {
    
    CurrentUser currentUser = new CurrentUser() ;
    
    public StatefulKnowledgeSession createNewRules() {
        String newRules = 
            "package Controller;\n"+
            "import Globals.CurrentUser ;\n"+    
            "import Enum.Role ;\n"+            
            "import java.util.Map;\n"+            
            "rule \"AdminLogin\"\n"+
                "when\n"+
                    "currentUser : CurrentUser(role==Role.EMPTY)\n"+
                    "$map : Map(this[\"username\"] == this[\"password\"])\n"+
                    "$id : Integer()\n"+
                "then\n"+
                    "System.out.println(currentUser.getRole());\n"+
                    "currentUser.setRole(Role.ADMIN) ;\n"+
                    "currentUser.setId($id);\n"+
                    "currentUser.setName($map.get(\"username\").toString());\n"+
                    "System.out.println(currentUser.getRole());\n"+
                    "update(currentUser);\n"+
            "end\n";
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( newRules.getBytes() ), ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            System.out.println(kbuilder.getErrors().toString() );
        }        
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );        
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        return ksession ;
    }
    
    public Map<String,Object> tellerLogin(String username,String password) {
        int id = checkUserExist(username) ;
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("username", username);
        map.put("password", password);
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession kSession = kContainer.newKieSession("Admin_Rules");
//        kSession.getAgenda().getAgendaGroup("Login").setFocus();
        StatefulKnowledgeSession kSession = createNewRules();
        kSession.insert(currentUser);
        kSession.insert(map);
        kSession.insert(id);
        kSession.fireAllRules();  
        if (id!=-1) {
            if (username.compareTo(password)==0) return successLogin(username,id) ;
            else return failLogin(username) ;
        } else {
            return usernameNotFound(username) ;
        }
    }
    
    public int checkUserExist(String username) {
        for (int i=1;i<GlobalData.DataTeller.size();i++) {
            if (GlobalData.DataTeller.get(i).getName().equals(username)) return i ;
        }
        return -1 ;
    }
    
    public Map<String,Object> successLogin(String username, int id) {
        Map<String,Object> successMap = new  HashMap<String,Object> () ;
        successMap.put("status","login success") ;
        successMap.put("username",username) ;
        return successMap ;
    }
    
    public Map<String,Object> failLogin(String username) {
        Map<String,Object> successMap = new  HashMap<String,Object> () ;
        successMap.put("status","login fail") ;
        successMap.put("username",username) ;
        return successMap ;
    }
    
    public Map<String,Object> usernameNotFound(String username) {
        Map<String,Object> successMap = new  HashMap<String,Object> () ;
        successMap.put("status","username not found") ;
        return successMap ;
    }
}
