package Service;

import java.util.HashMap;
import java.util.Map;

import Enum.Status;
import Enum.Process;
import Globals.CurrentUser;
import Globals.GlobalData;

public class AccountService {

    CurrentUser currentUser = new CurrentUser() ;

    public int getBalanceById(int id) {
        return GlobalData.DataAccount.get(id).getBalance();
    }
    
    public String getNameById(int id) {
        return GlobalData.DataAccount.get(id).getName();
    }
    
    public void changeBalance(int id, int totalChange) {
        int currentBalance = getBalanceById(id) ;
        GlobalData.DataAccount.get(id).setBalance(currentBalance + totalChange);
    }

    public boolean transferProcess(int idSender, int amount, int idReceiver) {
        if (getBalanceById(idSender) < amount) return false ;
        changeBalance(idSender,amount*-1);
        changeBalance(idReceiver,amount);
        return true ;    
    }
    
    public Map<String,Object> transferTo(int idReceiver, int amount) {
        boolean isSuccess = transferProcess(currentUser.getId(),amount,idReceiver);
        String senderName = getNameById(currentUser.getId());
        String receiverName = getNameById(idReceiver);
        Map<String,Object> response = new HashMap<String,Object>() ;
        if (isSuccess) {
            response.put("status",Status.SUCCESS);
            response.put("process",Process.TRANSFER);
            response.put("description", "Transfer "+amount+" from "+senderName+" to "+receiverName+" success.");
        } else {
            response.put("status",Status.FAILED);
            response.put("process",Process.TRANSFER);
            response.put("description", "Transfer "+amount+" from "+senderName+" to "+receiverName+" fail.");
        }
        return response ;
    }
    
    public Map<String,Object> checkAccountDetail() {
        int id = currentUser.getId(); 
        int accountBalance = GlobalData.DataAccount.get(id).getBalance() ;
        String username = GlobalData.DataAccount.get(id).getName();
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("username", username) ;
        response.put("balance",accountBalance);
        response.put("status",Status.SUCCESS);
        response.put("process", Process.CHECK_MY_ACCOUNT);
        response.put("description", "Check your account detail");
        return response ;
    }
    
    public Map<String,Object> checkAccountDetailWithId(int id) {
        int accountBalance = GlobalData.DataAccount.get(id).getBalance() ;
        int pin = GlobalData.DataAccount.get(id).getPin();
        String username = GlobalData.DataAccount.get(id).getName();
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("status",Status.SUCCESS);
        response.put("process", Process.CHECK_CUSTOMER_ACCOUNT);
        response.put("description", "Check customer account detail");
        response.put("username", username) ;
        response.put("PIN", pin);
        response.put("balance",accountBalance);
        return response ;
    }    
    
    public Map<String,Object> getAllAccount() {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("status", Status.SUCCESS);
        response.put("process", Process.READ_LIST_ACCOUNT);
        response.put("description", "Look list account");
        response.put("list", GlobalData.DataAccount);
        return response ;
    }

}
