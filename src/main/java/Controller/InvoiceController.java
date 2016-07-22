package Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Service.InvoiceService;


@RestController
@RequestMapping(value = InvoiceController.BASE_PATH, produces = {MediaType
        .APPLICATION_JSON_VALUE})
public class InvoiceController {

    public static final String BASE_PATH = "/invoice" ;
    
    InvoiceService invoiceService = new InvoiceService();
    
    @RequestMapping(value = "/paid/{id}", method=RequestMethod.GET)
    public Map<String,Object> paidInvoice(@PathVariable int id) {
        return invoiceService.paidInvoice(id);
    }
    
    @RequestMapping(value="/all")
    public Map<String,Object> getAll() {
        return invoiceService.getAllInvoice();
    }
    
    @RequestMapping(value="/filter", method = RequestMethod.GET)
    public Map<String,Object> getInvoiceByPaidStatus(@RequestParam boolean paid) {
        if (paid) return invoiceService.getAllPaidInvoice();
        else return invoiceService.getAllUnpaidInvoice();
    }
    
    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public Map<String,Object> getInvoiceByIdUser(@PathVariable int idUser) {
        return invoiceService.getInvoiceByIdUser(idUser);
    }
    
    @RequestMapping(value="/my")
    public Map<String,Object> getMyInvoice() {
        return invoiceService.getMyInvoice();
    }
    
}
