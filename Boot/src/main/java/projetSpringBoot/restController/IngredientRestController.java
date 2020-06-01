package projetSpringBoot.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.service.IngredientService;

@RestController
@RequestMapping("/rest/ingredient")
@CrossOrigin(origins = "*")
public class IngredientRestController {
    @Autowired
    IngredientService ingredientService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Ingredient>> findAll() {
        return new ResponseEntity<List<Ingredient>>(ingredientService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") Integer id) {
        Optional<Ingredient> opt = ingredientService.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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