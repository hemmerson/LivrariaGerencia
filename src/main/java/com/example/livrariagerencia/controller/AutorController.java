package com.example.livrariagerencia.controller;

import com.example.livrariagerencia.model.Autor;
import com.example.livrariagerencia.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("autor")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @GetMapping("/form")
    public ModelAndView form(Autor autor, ModelMap model) {
        model.addAttribute("autor", autor);
        return new ModelAndView("autor/form");
    }

    @GetMapping
    public ModelAndView list(ModelMap model) {
        model.addAttribute("autores", repository.findAll());
        return new ModelAndView("autor/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("autor") Autor autor) {
        repository.save(autor);
        return new ModelAndView("redirect:/autor");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("autor", repository.findById(id));
        return new ModelAndView("autor/form_edit", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("autor") Autor autor) {
        repository.save(autor);
        return new ModelAndView("redirect:/autor");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/autor");
    }
}
