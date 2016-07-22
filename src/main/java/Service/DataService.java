package Service;

import java.util.HashMap;
import java.util.Map;

import Enum.Process;
import Enum.Status;
import Globals.GlobalData;
import Module.Account;
import Module.Admin;
import Module.Company;
import Module.Invoice;

public class DataService {

    public boolean checkIsDataEmpty() {
        return (GlobalData.DataAccount.size()==0 && GlobalData.DataCompany.size()==0
                && GlobalData.DataInvoice.size()==0 && GlobalData.DataTeller.size()==0);
    }
    
    public void insertData() {
        Admin teller1  = new  Admin() ;
        GlobalData.DataTeller.add(teller1);
        teller1.setLevel(1);
        teller1.setName("teller1");
        GlobalData.DataTeller.add(teller1);
        
        Account account1 = new Account() ;
        Account account2 = new Account() ;
        GlobalData.DataAccount.add(account1);
        account1.setId(1) ; account2.setId(2);
        account1.setName("account1"); account2.setName("account2");
        account1.setPin(123456); account2.setPin(654321);
        account1.setBalance(1000000); account2.setBalance(2000000);
        GlobalData.DataAccount.add(account1);
        GlobalData.DataAccount.add(account2);
        
        Invoice invoice1 = new Invoice() ; Invoice invoice3 = new Invoice();
        Invoice invoice2 = new Invoice() ; Invoice invoice4 = new Invoice() ;
        GlobalData.DataInvoice.add(invoice1);
        invoice1.setId(1); invoice2.setId(2); invoice3.setId(3); invoice4.setId(4);
        invoice1.setAccountId(1); invoice2.setAccountId(2);
        invoice3.setAccountId(1); invoice4.setAccountId(2);
        invoice1.setCompanyId(1); invoice2.setCompanyId(1);
        invoice3.setCompanyId(2); invoice4.setCompanyId(2);
        invoice1.setPaidStatus(false); invoice2.setPaidStatus(false);
        invoice3.setPaidStatus(false); invoice4.setPaidStatus(false);
        invoice1.setAmount(150000); invoice2.setAmount(300000);
        invoice3.setAmount(900000); invoice4.setAmount(900000);
        invoice1.setDescription("aa"); invoice2.setDescription("aa");
        invoice3.setDescription("aa"); invoice4.setDescription("aa");
        GlobalData.DataInvoice.add(invoice4); GlobalData.DataInvoice.add(invoice3);
        GlobalData.DataInvoice.add(invoice2); GlobalData.DataInvoice.add(invoice1);
        
        Company company1 = new Company() ;
        Company company2 = new Company() ;
        GlobalData.DataCompany.add(company1);
        company1.setId(1); company2.setId(2);
        company1.setName("A Company");
        company2.setName("B Company");
        GlobalData.DataCompany.add(company2); GlobalData.DataCompany.add(company1); 
    }
    
    public Map<String,Object> insertDummyData() {
        Map<String,Object> response = new HashMap<String,Object>() ;
        if (checkIsDataEmpty()) {
            insertData() ;
            response.put("status", Status.SUCCESS);
            response.put("process", Process.INSERT_DUMMY_DATA);
        } else {
            response.put("status", Status.FAILED);
            response.put("process", Process.INSERT_DUMMY_DATA);
            response.put("description","Data already exist");
        }
        return response ;
    }
}
