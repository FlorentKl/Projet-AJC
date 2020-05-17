package projetSpringBoot.controller.recette;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projetSpringBoot.exception.NoEtapeRecetteException;
import projetSpringBoot.model.recette.Boisson;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Entree;
import projetSpringBoot.model.recette.EtapeRecette;
import projetSpringBoot.model.recette.Plat;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.service.EtapeRecetteService;
import projetSpringBoot.service.RecetteService;
import projetSpringBoot.wrapper.RecetteAndEtapeWrapper;

@Controller
@RequestMapping("/creation-recette")
public class CreationRecetteController {
    @Autowired
    EtapeRecetteService etapeRecetteService;

    @Autowired
    RecetteService recetteService;

    @GetMapping(value = { "", "/" })
    public ModelAndView recetteCreationFormulaire() {
        ModelAndView modelAndView = new ModelAndView("Recette/CreationRecette");
        modelAndView.addObject("recetteEtapeWrapper", new RecetteAndEtapeWrapper());
        modelAndView.addObject("couts", Couts.values());
        modelAndView.addObject("difficultes", Difficulte.values());
        return modelAndView;
    }

    @PostMapping("/ajout/entree")
    public ModelAndView ajoutEntree(@Valid @ModelAttribute("recettes") Entree entree,
            @Valid @ModelAttribute("etapeRecette") EtapeRecette[] etapeRecettes, BindingResult br) {
        ModelAndView mav = save(entree, br);
        for (EtapeRecette er : etapeRecettes) {
            try {
                etapeRecetteService.insert(er);
            } catch (NoEtapeRecetteException e) {
                e.printStackTrace();
            }
        }
        return mav;
    }

    @PostMapping("/ajout/plat")
    public ModelAndView ajoutEntree(@Valid @ModelAttribute("plat") Plat plat,
            @Valid @ModelAttribute("etapeRecette") EtapeRecette[] etapeRecettes, BindingResult br) {
        ModelAndView mav = save(plat, br);
        for (EtapeRecette er : etapeRecettes) {
            try {
                etapeRecetteService.insert(er);
            } catch (NoEtapeRecetteException e) {
                e.printStackTrace();
            }
        }
        return mav;
    }

    @PostMapping("/ajout/dessert")
    public ModelAndView ajoutEntree(@Valid @ModelAttribute("dessert") Dessert dessert,
            @Valid @ModelAttribute("etapeRecette") EtapeRecette[] etapeRecettes, BindingResult br) {
        ModelAndView mav = save(dessert, br);
        for (EtapeRecette er : etapeRecettes) {
            try {
                etapeRecetteService.insert(er);
            } catch (NoEtapeRecetteException e) {
                e.printStackTrace();
            }
        }
        return mav;
    }

    @PostMapping("/ajout/boisson")
    public ModelAndView ajoutEntree(@Valid @ModelAttribute("boisson") Boisson boisson,
            @Valid @ModelAttribute("etapeRecette") EtapeRecette[] etapeRecettes, BindingResult br) {
        ModelAndView mav = save(boisson, br);
        for (EtapeRecette er : etapeRecettes) {
            try {
                etapeRecetteService.insert(er);
            } catch (NoEtapeRecetteException e) {
                e.printStackTrace();
            }
        }
        return mav;
    }

    private ModelAndView save(Recette r, BindingResult br) {
        recetteService.insert(r);
        return new ModelAndView("redirect:/creation-recette");
    }
}
