package projetSpringBoot.controller.recette;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import projetSpringBoot.container.RecetteAndEtapeContainer;
import projetSpringBoot.exception.NoEtapeRecetteException;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.EtapeRecette;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.service.EtapeRecetteService;
import projetSpringBoot.service.RecetteService;

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
        modelAndView.addObject("recetteEtapeContainer", new RecetteAndEtapeContainer());
        modelAndView.addObject("couts", Couts.values());
        modelAndView.addObject("difficultes", Difficulte.values());
        return modelAndView;
    }

    @PostMapping("/ajout/entree")
    public ModelAndView ajoutEntree(@Valid @ModelAttribute("recetteEtapeContainer") RecetteAndEtapeContainer recette,
            BindingResult br) {
        System.out.println(recette);
        return save(recette.getEntree(), recette.getEtapeRecettes(), br);
    }

    @PostMapping("/ajout/plat")
    public ModelAndView ajoutPlat(@Valid @ModelAttribute("recetteEtapeContainer") RecetteAndEtapeContainer recette,
            BindingResult br) {
        return save(recette.getPlat(), recette.getEtapeRecettes(), br);
    }

    @PostMapping("/ajout/dessert")
    public ModelAndView ajoutDessert(@Valid @ModelAttribute("recetteEtapeContainer") RecetteAndEtapeContainer recette,
            BindingResult br) {
        return save(recette.getDessert(), recette.getEtapeRecettes(), br);
    }

    @PostMapping("/ajout/boisson")
    public ModelAndView ajoutBoisson(@Valid @ModelAttribute("recetteEtapeContainer") RecetteAndEtapeContainer recette,
            BindingResult br) {
        return save(recette.getBoisson(), recette.getEtapeRecettes(), br);
    }

    private ModelAndView save(Recette r, List<EtapeRecette> etapes, BindingResult br) {
        Recette newRecette = recetteService.insert(r);
        int nombreEtape = 1;
        for (EtapeRecette er : etapes) {
            er.setId_recette(newRecette);
            er.setNumEtape(nombreEtape);
            nombreEtape++;
            try {
                etapeRecetteService.insert(er);
            } catch (NoEtapeRecetteException e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("redirect:/creation-recette");
    }
}
