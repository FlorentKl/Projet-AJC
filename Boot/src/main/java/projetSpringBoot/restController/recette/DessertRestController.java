package projetSpringBoot.restController.recette;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.DessertService;

@RestController
@RequestMapping("/rest/dessert")
@CrossOrigin(origins = "*")
public class DessertRestController {
    @Autowired
    DessertService dessertService;

    /*
     * Get Mapping
     */

    @JsonView(Views.RecetteView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Dessert>> findAllDessert() {
        return new ResponseEntity<List<Dessert>>(dessertService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Dessert>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Dessert>>(dessertService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/{id}")
    public ResponseEntity<Dessert> findById(@PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);
        return opt.map(dessert -> {
            return new ResponseEntity<Dessert>(dessert, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Dessert>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/{id}" })
    public ResponseEntity<Dessert> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);
        return opt.map(dessert -> {
            return new ResponseEntity<>(dessert, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Dessert>> findByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(dessertService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/notnom/{nom}")
    public ResponseEntity<List<Dessert>> findByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(dessertService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/nom/{nom}")
    public ResponseEntity<List<Dessert>> findAllByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(dessertService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/notnom/{nom}")
    public ResponseEntity<List<Dessert>> findAllByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(dessertService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    // Renvoie recettes en fonction de la difficulté voulue
    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/difficulte/{difficulte}")
    public ResponseEntity<List<Dessert>> findByDifficulte(@PathVariable("difficulte") String difficulte) {
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
        return new ResponseEntity<>(dessertService.findByDifficulte(diff), HttpStatus.OK);
    }

    // Check si nom recette existe déjà
    @GetMapping("/check/{string}")
    public ResponseEntity<Boolean> checkNom(@PathVariable("string") String string) {
        Optional<Dessert> opt = dessertService.findByNom(string);
        if (opt.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /*
     * Post Mapping
     */

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> addDessert(@RequestBody Dessert dessert, BindingResult br, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dessertService.insert(dessert);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/dessert/{id}").buildAndExpand(dessert.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /*
     * Delete Mapping
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);
        return opt.map(dessert -> {
            dessertService.deleteById(dessert.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    /*
     * Put Mapping
     */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Dessert dessert, @PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);

        return opt.map(temp -> {
            dessertService.update(dessert);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}