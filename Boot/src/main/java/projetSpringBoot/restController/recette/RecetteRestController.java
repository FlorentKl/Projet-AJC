package projetSpringBoot.restController.recette;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.RecetteService;

@RestController
@RequestMapping("/rest/recette")
@CrossOrigin(origins = "*")
public class RecetteRestController {
    @Autowired
    RecetteService recetteService;

    /*
     * Get Mapping
     */

    @JsonView(Views.RecetteView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Recette>> findAllRecette() {
        return new ResponseEntity<List<Recette>>(recetteService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Recette>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Recette>>(recetteService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/id/{id}")
    public ResponseEntity<Recette> findById(@PathVariable("id") Integer id) {
        Optional<Recette> opt = recetteService.findById(id);
        return opt.map(recette -> {
            return new ResponseEntity<Recette>(recette, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Recette>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/id/{id}" })
    public ResponseEntity<Recette> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Recette> opt = recetteService.findById(id);
        return opt.map(recette -> {
            return new ResponseEntity<>(recette, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Recette>> findByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(recetteService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/notnom/{nom}")
    public ResponseEntity<List<Recette>> findByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(recetteService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/nom/{nom}")
    public ResponseEntity<List<Recette>> findAllByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(recetteService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/notnom/{nom}")
    public ResponseEntity<List<Recette>> findAllByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(recetteService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    // Renvoie recettes en fonction de la difficulté voulue
    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/difficulte/{difficulte}")
    public ResponseEntity<List<Recette>> findByDifficulte(@PathVariable("difficulte") String difficulte) {
        Difficulte diff;
        switch (difficulte) {
            case "F":
                diff = Difficulte.F;
                break;
            case "M":
                diff = Difficulte.M;
                break;
            case "D":
                diff = Difficulte.D;
                break;
            case "E":
                diff = Difficulte.E;
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(recetteService.findByDifficulte(diff), HttpStatus.OK);
    }

    // Check si nom recette existe déjà
    @GetMapping("/check/{string}")
    public ResponseEntity<Boolean> checkNom(@PathVariable("string") String string) {
        Optional<Recette> opt = recetteService.findByNom(string);
        if (opt.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /*
     * Delete Mapping
     */

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Recette> opt = recetteService.findById(id);
        return opt.map(recette -> {
            recetteService.deleteById(recette.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }
}