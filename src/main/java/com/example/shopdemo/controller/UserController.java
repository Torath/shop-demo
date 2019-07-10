package com.example.shopdemo.controller;

import com.example.shopdemo.model.*;
import com.example.shopdemo.service.ProduktService;
import com.example.shopdemo.service.SecurityService;
import com.example.shopdemo.service.UserService;
import com.example.shopdemo.service.ZamowienieService;
import com.example.shopdemo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ProduktService produktService;

    @Autowired
    private ZamowienieService zamowienieService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Niepoprawne dane logowania.");

        if (logout != null)
            model.addAttribute("message", "Zostales wylogowany.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(ModelMap model) {
        model.addAttribute("produkty",produktService.findAll());

        if (hasRole("ROLE_ADMIN")) {

            return "admin_welcome";
        }else
        return "welcome";
    }


    protected boolean hasRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()))
                return true;
        }

        return false;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dodajProdukt")
    public String addItem(ModelMap model){
        model.addAttribute("produktForm", new Produkt());
        model.addAttribute("enumValues", Kategoria.values());
        return "addItemForm";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/dodajProdukt")
    public String addItem(@ModelAttribute("produktForm") Produkt produktForm, BindingResult bindingResult) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "addItemForm";
        }

        produktService.save(produktForm);

        return "redirect:/welcome";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edytujProdukt/{id}")
    public String editItem( ModelMap model, @PathVariable Long id){
        model.addAttribute("editItemForm", produktService.findById(id));
        model.addAttribute("enumValues", Kategoria.values());
        return "editItemForm";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edytujProdukt/{id}")
    public String editItem(@ModelAttribute("editItemForm") Produkt editItemForm, BindingResult bindingResult, @PathVariable Long id) {
        //userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "editItemForm";
        }
        Produkt produkt=produktService.findById(id);
        produkt.setCena(editItemForm.getCena());
        produkt.setNazwaProduktu(editItemForm.getNazwaProduktu());
        produkt.setIloscWMagazynie(editItemForm.getIloscWMagazynie());
        produkt.setKategoria(editItemForm.getKategoria());

        produktService.save(produkt);

        return "redirect:/welcome";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/zamowProdukt/{id}")
    public String zamowProdukt( ModelMap model, @PathVariable Long id){
        model.addAttribute("editItemForm", new Zamowienie());
        model.addAttribute("produkt",produktService.findById(id));
        return "zamowienieForm";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/zamowProdukt/{id}")
    public String zamowProdukt(@ModelAttribute("editItemForm") Zamowienie editItemForm, BindingResult bindingResult, @PathVariable Long id) {
        //userValidator.validate(userForm, bindingResult);
        Zamowienie zamowienie= new Zamowienie();
        zamowienie.setAdresWysylki(editItemForm.getAdresWysylki());

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        System.out.println("aaaaaaaaaaaaaaaaaaa");
        System.out.println("username:"+username);
        zamowienie.setUser(
                userService.findByUsername((username))
        );
        zamowienie.setProdukt(produktService.findById(id));
        //zamowienie.setStatusZamowienia(StatusZamowienia.NOWE);
        if (bindingResult.hasErrors()) {
            return "editItemForm";
        }


        zamowienieService.save(zamowienie);

        return "redirect:/welcome";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/zamowienia")
    public String zamowProdukt( ModelMap model){
        if(hasRole("ROLE_ADMIN")){
            model.addAttribute("zamowienia",zamowienieService.findAll());
            System.out.println(zamowienieService.findAll());
            return "admin_zamowienia";
        }else {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedInUser.getName();
            model.addAttribute("zamowienia", zamowienieService.findByUser(
                    userService.findByUsername(username)
            ));
        }
        return "zamowienia";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/zamowienia")
    public String zapiszStatusZamowienia(@ModelAttribute("zamowienie") Zamowienie zamowienie, BindingResult bindingResult, @PathVariable Long id) {
        //userValidator.validate(userForm, bindingResult);
        Zamowienie zam= zamowienieService.findById(id);
        zamowienie.setStatusZamowienia(zamowienie.getStatusZamowienia());



        zamowienieService.save(zam);

        return "redirect:/zamowienia";
    }

}
