package projetSpringBoot.restController.recette;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Dessert;
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

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/search")
    public ResponseEntity<List<Recette>> search(@RequestParam(required = false) String namelike,
            @RequestParam(required = false) Difficulte diff, @RequestParam(required = false) Difficulte nodiff,
            @RequestParam(required = false) Couts cout, @RequestParam(required = false) Couts nocout,
            @RequestParam(required = false) Double note) {

        List<Recette> listeFinale = new ArrayList<Recette>();
        Boolean premiereListe = true;
        if (namelike != null && premiereListe) {
            listeFinale = filterNameLike(listeFinale, namelike);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
        }
        if (diff != null && premiereListe) {
            listeFinale = filterDiff(listeFinale, diff);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
        }
        if (nodiff != null && premiereListe) {
            listeFinale = filterDiffNot(listeFinale, nodiff);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
        }
        if (cout != null && premiereListe) {
            listeFinale = filterCout(listeFinale, cout);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
        }
        if (nocout != null && premiereListe) {
            listeFinale = filterCoutNot(listeFinale, nocout);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
        }
        if (note != null && premiereListe) {
            listeFinale = filterNote(listeFinale, note);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
        }

        return new ResponseEntity<>(listeFinale, HttpStatus.OK);
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

    @PostMapping("/test")
    public ResponseEntity<Void> test(@RequestBody Dessert dessert, BindingResult bra, @RequestBody Ingredient[] ingr,
            BindingResult brb) {
        System.out.println(dessert.getNom());
        for (Ingredient i : ingr) {

            System.out.println(i.getNom());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<Recette> filterNameLike(List<Recette> listeFinale, String namelike) {
        List<Recette> listeFiltrante = recetteService.findByNomContaining(namelike);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Recette> filterDiff(List<Recette> listeFinale, Difficulte diff) {
        List<Recette> listeFiltrante = recetteService.findByDifficulte(diff);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Recette> filterDiffNot(List<Recette> listeFinale, Difficulte nodiff) {
        List<Recette> listeFiltrante = recetteService.findByDifficulteNot(nodiff);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        }
        if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Recette> filterCout(List<Recette> listeFinale, Couts cout) {
        List<Recette> listeFiltrante = recetteService.findByCout(cout);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        }
        if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Recette> filterCoutNot(List<Recette> listeFinale, Couts nocout) {
        List<Recette> listeFiltrante = recetteService.findByCoutNot(nocout);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        }
        if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Recette> filterNote(List<Recette> listeFinale, Double note) {
        List<Recette> listeFiltrante = recetteService.findByNote(note);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        }
        if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }
}