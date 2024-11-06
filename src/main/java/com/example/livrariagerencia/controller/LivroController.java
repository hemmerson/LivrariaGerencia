package com.example.livrariagerencia.controller;

import com.example.livrariagerencia.model.Livro;
import com.example.livrariagerencia.repository.AutorRepository;
import com.example.livrariagerencia.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/form")
    public String form(Model model) {
        if (!model.containsAttribute("livro")) {
            model.addAttribute("livro", new Livro());
        }
        model.addAttribute("autores", autorRepository.findAll());
        return "livro/form";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livro/list";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("livro") Livro livro,
                       BindingResult result,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("autores", autorRepository.findAll());
            return "livro/form";
        }

        try {
            livroRepository.save(livro);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Livro salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erro ao salvar o livro: " + e.getMessage());
        }

        return "redirect:/livro";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent()) {
            model.addAttribute("livro", livro.get());
            model.addAttribute("autores", autorRepository.findAll());
            return "livro/form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Livro não encontrado.");
            return "redirect:/livro";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("livro") Livro livro,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("autores", autorRepository.findAll());
            return "livro/form";
        }

        try {
            if (!livroRepository.existsById(livro.getId())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Livro não encontrado para atualização.");
                return "redirect:/livro";
            }

            livroRepository.save(livro);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Livro atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erro ao atualizar o livro: " + e.getMessage());
        }

        return "redirect:/livro";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        try {
            if (!livroRepository.existsById(id)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Livro não encontrado para remoção.");
                return "redirect:/livro";
            }

            livroRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Livro removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erro ao remover o livro: " + e.getMessage());
        }

        return "redirect:/livro";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage",
                "Ocorreu um erro: " + ex.getMessage());
        return "redirect:/livro";
    }
}