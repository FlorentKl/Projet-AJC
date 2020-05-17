package projetSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.service.RecetteService;

@Controller
public class HomeController {

    @Autowired
    RecetteService recetteService;

    @RequestMapping(value = { "", "/home" })
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Recette> recettes = recetteService.findAllWithTags();
        modelAndView.addObject("recettes", recettes);
        return modelAndView;
    }
}
