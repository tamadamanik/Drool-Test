package Module;

public class Admin {
    
    private int id ;
    private String name ;

    public Admin() {}
   
    
    public void setLevel(int id_) {id= id_;}
    public void setName(String name_) {name = name_ ;}
    
    public String getName() { return name ; }
    public int getId() {return id ; }
}
