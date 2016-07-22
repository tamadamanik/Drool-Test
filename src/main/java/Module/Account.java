package Module;


public class Account {
    
    private String name ;
    private int pin ;
    private int balance ;
    private int id ;
    
    public Account() {}
    
    public void setName(String name_) {name = name_; }
    public void setPin(int pin_) {pin = pin_;}
    public void setBalance(int balance_) {balance = balance_ ;}
    public void setId(int id_) {id =  id_;}
    
    public String getName() {return name;}
    public int getPin() {return pin ;}
    public int getBalance() {return balance ; }
}

