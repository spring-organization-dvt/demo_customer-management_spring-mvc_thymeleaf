package com.thinkpad.controller;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.thinkpad.model.Customer;
import com.thinkpad.service.CustomerService;
import com.thinkpad.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index", "customers", customerService.findAll());
    }

    @GetMapping("/customer/create")
    public ModelAndView create() {
        return new ModelAndView("create", "customer", new Customer());
    }

    @PostMapping("/customer/save")
    public ModelAndView save(Customer customer) {
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("index", "customers", customerService.findAll());
        modelAndView.addObject("success", "Save");
//        redirectAttributes.addFlashAttribute("success","Saved");
        return modelAndView;
    }

//    public String save(Customer customer, RedirectAttributes redirect) {
//        customer.setId((int)(Math.random() * 10000));
//        customerService.save(customer);
//        redirect.addFlashAttribute("success", "Saved customer successfully!");
//        return "redirect:/";
//    }

    @GetMapping("/customer/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        return new ModelAndView("edit", "customer", customerService.findById(id));
    }

    @PostMapping("customer/update")
    public ModelAndView update(Customer customer) {
        customerService.update(customer.getId(), customer);
        ModelAndView modelAndView = new ModelAndView("index", "customers", customerService.findAll());
        modelAndView.addObject("success", "updated");
        return modelAndView;
    }
}
