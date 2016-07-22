package Service;

import java.util.HashMap;
import java.util.Map;

import Enum.Process;
import Enum.Status;
import Globals.GlobalData;

public class CompanyService {

    public Map<String,Object> getAllCompany() {
        Map<String,Object> response = new HashMap<String,Object>() ;
        response.put("status", Status.SUCCESS);
        response.put("process", Process.READ_LIST_COMPANY);
        response.put("list_company", GlobalData.DataCompany);
        return response ;
    }
}
