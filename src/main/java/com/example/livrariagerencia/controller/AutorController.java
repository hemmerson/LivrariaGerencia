package com.example.livrariagerencia.controller;

import com.example.livrariagerencia.model.Autor;
import com.example.livrariagerencia.repository.AutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/form")
    public String form(Model model) {
        if (!model.containsAttribute("autor")) {
            model.addAttribute("autor", new Autor());
        }
        return "autor/form";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autor/list";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("autor") Autor autor,
                       BindingResult result,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        if (result.hasErrors()) {
            return "autor/form";
        }

        try {
            autorRepository.save(autor);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Autor salvo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erro ao salvar o autor: " + e.getMessage());
        }

        return "redirect:/autor";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()) {
            model.addAttribute("autor", autor.get());
            return "autor/form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Autor não encontrado.");
            return "redirect:/autor";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("autor") Autor autor,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            return "autor/form";
        }

        try {
            if (!autorRepository.existsById(autor.getId())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Autor não encontrado para atualização.");
                return "redirect:/autor";
            }

            autorRepository.save(autor);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Autor atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erro ao atualizar o autor: " + e.getMessage());
        }

        return "redirect:/autor";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        try {
            if (!autorRepository.existsById(id)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Autor não encontrado para remoção.");
                return "redirect:/autor";
            }

            autorRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Autor removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erro ao remover o autor: " + e.getMessage());
        }

        return "redirect:/autor";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage",
                "Ocorreu um erro: " + ex.getMessage());
        return "redirect:/autor";
    }
}