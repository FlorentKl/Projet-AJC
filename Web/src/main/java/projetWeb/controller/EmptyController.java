package projetWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import formationJpa.entity.recette.Recette;
import formationJpa.service.RecetteService;

@Controller
public class EmptyController {

	@Autowired
	RecetteService recetteService;



	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("home");
		List<Recette> recettes = recetteService.findAllWithTags();
		modelAndView.addObject("recettes", recettes);
		return modelAndView;
		
	}

}
