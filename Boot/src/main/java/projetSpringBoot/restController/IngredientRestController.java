package projetSpringBoot.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projetSpringBoot.model.Ingredients.AssociationIngredientRecette;
import projetSpringBoot.model.Ingredients.AssociationIngredientRecetteKey;
import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.Ingredients.Unite;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.AssociationIngredientRecetteService;
import projetSpringBoot.service.IngredientService;
import projetSpringBoot.service.recette.RecetteService;

@RestController
@RequestMapping("/rest/ingredient")
@CrossOrigin(origins = "*")
public class IngredientRestController {
    @Autowired
    IngredientService ingredientService;

    @Autowired
    AssociationIngredientRecetteService airService;

    @Autowired
    RecetteService recetteService;

    @JsonView(value = { Views.IngredientView.class })
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Ingredient>> findAll() {
        return new ResponseEntity<List<Ingredient>>(ingredientService.findAll(), HttpStatus.OK);
    }

    @JsonView(value = { Views.IngredientView.class })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") Integer id) {
        Optional<Ingredient> opt = ingredientService.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/assoc")
    public ResponseEntity<Void> assocIngreRecette(@RequestBody Ingredient ingr, BindingResult br,
            @RequestParam(name = "idr", required = false) Integer idRecette,
            @RequestParam(required = false) Integer quantite, @RequestParam(required = false) Unite unite,
            UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Ingredient> optIngr = ingredientService.findByNom(ingr.getNom());

        Ingredient ingredient;
        if (optIngr.isPresent()) {
            ingredient = optIngr.get();
        } else {
            ingredient = ingredientService.insert(ingr);
        }

        Optional<Recette> optRecette = recetteService.findById(idRecette);
        if (!optRecette.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AssociationIngredientRecette assoc = new AssociationIngredientRecette();
        assoc.setQuantite(quantite);
        assoc.setUnite(unite);
        assoc.setId(new AssociationIngredientRecetteKey(optRecette.get(), ingredient));

        assoc = airService.insert(assoc);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/ingredient/{id}").buildAndExpand(ingredient.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> postIngredient(@RequestBody Ingredient ingredient, BindingResult br,
            UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        ingredient = ingredientService.insert(ingredient);
        URI uri = uCB.path("/rest/ingredient/{id}").buildAndExpand(ingredient.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {
        Optional<Ingredient> opt = ingredientService.findById(id);
        if (opt.isPresent()) {
            ingredientService.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Ingredient ingredient, @PathVariable("id") Integer id) {
        Optional<Ingredient> opt = ingredientService.findById(id);
        if (opt.isPresent()) {
            Ingredient ingredientEnBase = opt.get();

            ingredientEnBase.setNom(ingredient.getNom());
            ingredientEnBase.setPicture(ingredient.getPicture());
            ingredientEnBase.setRecettes(ingredient.getRecettes());

            ingredientEnBase = ingredientService.insert(ingredientEnBase);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}