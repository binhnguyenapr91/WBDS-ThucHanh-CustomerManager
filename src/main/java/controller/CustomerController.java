package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import service.CustomerService;
import service.ICustomerService;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService = new ICustomerService();

    @GetMapping("/")
    String showAll(Model model){
        List customerList = customerService.getAllCustomer();
        model.addAttribute("customers",customerList);
        return "index";
    }


    @GetMapping("/customer/{id}/view")
    String getById(@PathVariable(name="id")int id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer",customer);
        return "/view";
    }

    @GetMapping("/customer/create")
    String createCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "/create";
    }

    @PostMapping("/customer/save")
    RedirectView saveCustomer(Customer customer, RedirectAttributes redirectAttributes){
        customer.setId((int) Math.random()*1000);
        customerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("success","Add customer successful");
        return new RedirectView("/");
    }

    @GetMapping("/customer/{id}/update")
    ModelAndView updateCustomer(@PathVariable (name = "id") int id){
        Customer customer = customerService.getCustomerById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/customer/update")
    RedirectView updateCustomer(Customer customer,RedirectAttributes redirectAttributes){
        customerService.updateCustomer(customer.getId(),customer);
        redirectAttributes.addFlashAttribute("success","Update customer successful");
        return new RedirectView("/");

    }

    @GetMapping("/customer/{id}/delete")
    String delete(@PathVariable("id") int id,Model model){
        customerService.deleteCustomerById(id);
        List customerList = customerService.getAllCustomer();
        model.addAttribute("customers",customerList);
        return "index";
    }
}
