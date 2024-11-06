package com.example.livrariagerencia.controller;

import com.example.livrariagerencia.model.Livro;
import com.example.livrariagerencia.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("livro")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("/form")
    public ModelAndView form(Livro livro, ModelMap model) {
        model.addAttribute("livro", livro);
        return new ModelAndView("livro/form");
    }

    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("livros", repository.findAll());
        return new ModelAndView("livro/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("livro") Livro livro) {
        repository.save(livro);
        return new ModelAndView("redirect:/livro/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("livro", repository.findById(id));
        return new ModelAndView("livro/form_edit", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("livro") Livro livro) {
        repository.save(livro);
        return new ModelAndView("redirect:/livro/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/livro/list");
    }
}
