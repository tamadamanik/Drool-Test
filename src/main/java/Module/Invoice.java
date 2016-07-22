package Module;

public class Invoice {
    
    private int AccountId ;
    private int CompanyId; 
    private int amount ;
    private boolean paidStatus ;
    private String description ;
    private int id ;
    
    public Invoice() {}
    
    public void setId(int id_) {id = id_;}
    public void setAccountId(int id_) { AccountId = id_; }
    public void setCompanyId(int id_) { CompanyId = id_ ; }
    public void setAmount (int amount_) {amount = amount_; }
    public void setPaidStatus(boolean paidStatus_) { paidStatus=  paidStatus_ ;}
    public void setDescription(String description_) {description = description_;}
    
    public int getId() {return id ; }
    public int getAccountId() {return AccountId ; }
    public int getCompanyId() { return CompanyId ; }
    public int getAmount() { return amount ; }
    public boolean getPaidStatus() { return paidStatus ; }
    public String getDescription() { return description ; }
}
