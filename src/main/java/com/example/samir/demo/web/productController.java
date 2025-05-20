package com.example.samir.demo.web;


import com.example.samir.demo.entities.Product;
import com.example.samir.demo.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller

public class productController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/user/index")

    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }
    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/index";

    }
    @DeleteMapping("/admin/delete")
    public String delete(@RequestParam("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/index";
    }
    @PostMapping("/admin/newProduct")
    public String NewProduct( Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }
    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new-product";

        }
        productRepository.save(product);
        return "redirect:/index";
    }
    @GetMapping("/notAuthorized")
            public String notAuthorized(){
        return "NotAuthorized";
        }

        @GetMapping("/login")
        public String login(){
        return "login";
        }
@GetMapping("/logout")
        public String logout(HttpSession session)
        {
            session.invalidate();
            return "login";
        }

    }




