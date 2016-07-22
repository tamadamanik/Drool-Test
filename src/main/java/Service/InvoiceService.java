package Service;

import Globals.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Module.Invoice;
import Enum.*;
import Enum.Process;

public class InvoiceService {
    
    CurrentUser currentUser =  new CurrentUser() ;
    AccountService accountService = new AccountService();
    
    public int getInvoiceAmountById(int id) {
        return GlobalData.DataInvoice.get(id).getAmount();
    }
    
    public String getInvoiceDescriptionById(int id) {
        return GlobalData.DataInvoice.get(id).getDescription();
    }
    
    public void changeInvoidStatus(int invoiceId) {
        GlobalData.DataInvoice.get(invoiceId).setPaidStatus(true);
    }
    
    public boolean paidInvoiceProcess(int invoiceId) {
        int currentBalance = accountService.getBalanceById(currentUser.getId());
        int invoiceAmount = getInvoiceAmountById(invoiceId) ;
        if (currentBalance < invoiceAmount) return false ;
        changeInvoidStatus(invoiceId);
        accountService.changeBalance(currentUser.getId(), invoiceAmount*-1);
        return true ;
    }
    
    public Map<String,Object> paidInvoice(int invoiceId) {
        boolean isSuccess = paidInvoiceProcess(invoiceId) ;
        Map<String,Object> response = new HashMap<String,Object>() ;
        if (isSuccess) {
            response.put("status",Status.SUCCESS);
            response.put("process",Process.PAID_INVOICE);
            response.put("description","Paid invoice success");
            response.put("amount", getInvoiceAmountById(invoiceId));
            response.put("invoice_description", getInvoiceDescriptionById(invoiceId));
        } else {
            response.put("status",Status.FAILED);
            response.put("process",Process.PAID_INVOICE);
            response.put("description","Paid invoice fail. Your balance not enough.");
            response.put("amount", getInvoiceAmountById(invoiceId));
            response.put("invoice_description", getInvoiceDescriptionById(invoiceId));
        }
        return response ;
    }
    
    public Map<String,Object> getAllInvoice() {
        Map<String,Object> response =  new HashMap<String,Object>();
        response.put("status", Status.SUCCESS);
        response.put("process", Process.READ_LIST_INVOICE);
        response.put("list_invoice", GlobalData.DataInvoice);
        return response ;
    }
    
    public Map<String,Object> getAllPaidInvoice() {
        Map<String,Object> response =  new HashMap<String,Object>();
        List<Invoice> listInvoice = new ArrayList<Invoice>() ;
        for (int i=1;i<=GlobalData.DataInvoice.size();i++) {
            if (GlobalData.DataInvoice.get(i).getPaidStatus())
                listInvoice.add(GlobalData.DataInvoice.get(i));
        }
        response.put("status", Status.SUCCESS);
        response.put("process", Process.READ_LIST_PAID_INVOICE);
        response.put("list_invoice", listInvoice);             
        return response ;
    }
    
    public Map<String,Object> getAllUnpaidInvoice() {
        Map<String,Object> response =  new HashMap<String,Object>();
        List<Invoice> listInvoice = new ArrayList<Invoice>() ;
        for (int i=1;i<=GlobalData.DataInvoice.size();i++) {
            if (!GlobalData.DataInvoice.get(i).getPaidStatus())
                listInvoice.add(GlobalData.DataInvoice.get(i));
        }
        response.put("status", Status.SUCCESS);
        response.put("process", Process.READ_LIST_UNPAID_INVOICE);
        response.put("list_invoice", listInvoice);           
        return response ;
    }
    
    public Map<String,Object> getInvoiceByIdUser(int idUser) {
        Map<String,Object> response =  new HashMap<String,Object>();
        List<Invoice> listInvoice = new ArrayList<Invoice>() ;
        for (int i=1;i<=GlobalData.DataInvoice.size();i++) {
            if (GlobalData.DataInvoice.get(i).getAccountId()==idUser)
                listInvoice.add(GlobalData.DataInvoice.get(i));
        }
        response.put("status", Status.SUCCESS);
        response.put("process", Process.READ_ACCOUNT_INVOICE);
        response.put("list_invoice", listInvoice);        
        return response ;
    }
    
    public Map<String,Object> getMyInvoice() {
        return getInvoiceByIdUser(currentUser.getId());
    }
    
    public Map<String,Object> AddInvoice(int accountId, int amount, int companyId, String description) {
        Invoice invoice = new Invoice() ;
        invoice.setAccountId(accountId);
        invoice.setAmount(amount);
        invoice.setCompanyId(companyId);
        invoice.setDescription(description);
        invoice.setPaidStatus(false);
        invoice.setId(GlobalData.DataInvoice.size()+1);
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("status", Status.SUCCESS);
        response.put("process", Process.ADD_INVOICE);
        response.put("description", "Add new invoice success");
        response.put("added_invoice", invoice);
        return response ;
    }
}
