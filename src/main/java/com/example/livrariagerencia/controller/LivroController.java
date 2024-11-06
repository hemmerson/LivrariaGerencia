package com.example.livrariagerencia.controller;

import com.example.livrariagerencia.model.Livro;
import com.example.livrariagerencia.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
@RequestMapping("livro")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping
    public ModelAndView form(Livro livro, ModelMap model) {
        model.addAttribute("livro", livro);
        return new ModelAndView("livro/form");
    }
}
