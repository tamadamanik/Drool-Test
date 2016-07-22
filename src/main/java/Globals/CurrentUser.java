package Globals;

import Enum.*;
public class CurrentUser {
    
    private Role role = Role.EMPTY ;
    private int id = -1 ;
    private String name = "" ;
        
    public void setRole(Role role_) {role = role_;}
    public void setId(int id_) {id = id_ ;}
    public void setName(String name_) {name = name_ ;}
    
    public Role getRole() {return role ;}
    public int getId() {return id ; }
    public String getName() {return name ;}
}
