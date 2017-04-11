package br.com.wine.controller;

import br.com.wine.model.TipoVinho;
import br.com.wine.model.Vinho;
import br.com.wine.repository.Vinhos;
import br.com.wine.repository.filter.VinhoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {

    @Autowired
    private Vinhos vinhos;

    @GetMapping("/novo")
    public ModelAndView novo(Vinho vinho) {
        ModelAndView mv = new ModelAndView("vinho/cadastro-vinho");
        mv.addObject(vinho);
        mv.addObject("tipos", TipoVinho.values());
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return novo(vinho);
        }

        vinhos.save(vinho);
        attributes.addFlashAttribute("message", "Vinho salvo com sucesso!");
        return new ModelAndView("redirect:/vinhos/novo");
    }

    @GetMapping
    public ModelAndView pesquisar(VinhoFilter vinhoFilter) {
        ModelAndView mv = new ModelAndView("/vinho/pesquisa-vinhos");
        mv.addObject("vinhos", vinhos.findByNomeContainingIgnoreCase(
                Optional.ofNullable(vinhoFilter.getNome()).orElse("%")));
        return mv;
    }

    @GetMapping("/{codigo}")
    public ModelAndView editar(@PathVariable Long codigo) {
        Vinho vinho = vinhos.findOne(codigo);
        return novo(vinho);
    }

    @DeleteMapping("/{codigo}")
    public String deletar(@PathVariable Long codigo, RedirectAttributes attributes) {
        vinhos.delete(codigo);
        attributes.addFlashAttribute("message", "Vinho removido com sucesso");
        return "redirect:/vinhos";
    }
}
