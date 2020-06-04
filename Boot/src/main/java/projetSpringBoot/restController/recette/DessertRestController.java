package projetSpringBoot.restController.recette;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.imageModel.ImageModel;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.ImageService;
import projetSpringBoot.service.UtilisateurService;
import projetSpringBoot.service.recette.DessertService;

@RestController
@RequestMapping("/rest/dessert")
@CrossOrigin(origins = "*")
public class DessertRestController {
    @Autowired
    DessertService dessertService;

    @Autowired
    ImageService imageService;

    @Autowired
    UtilisateurService utilisateurService;

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

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/search")
    public ResponseEntity<List<Dessert>> search(@RequestParam(required = false) String namelike,
            @RequestParam(required = false) Difficulte diff, @RequestParam(required = false) Difficulte nodiff,
            @RequestParam(required = false) Couts cout, @RequestParam(required = false) Couts nocout,
            @RequestParam(required = false) Double note) {

        List<Dessert> listeFinale = new ArrayList<Dessert>();
        Boolean premiereListe = true;
        Boolean paramAbsent = true;
        if (namelike != null && premiereListe) {
            listeFinale = filterNameLike(listeFinale, namelike);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
            paramAbsent = false;
        }
        if (diff != null && premiereListe) {
            listeFinale = filterDiff(listeFinale, diff);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
            paramAbsent = false;
        }
        if (nodiff != null && premiereListe) {
            listeFinale = filterDiffNot(listeFinale, nodiff);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
            paramAbsent = false;
        }
        if (cout != null && premiereListe) {
            listeFinale = filterCout(listeFinale, cout);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
            paramAbsent = false;
        }
        if (nocout != null && premiereListe) {
            listeFinale = filterCoutNot(listeFinale, nocout);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
            paramAbsent = false;
        }
        if (note != null && premiereListe) {
            listeFinale = filterNote(listeFinale, note);
            if (listeFinale.isEmpty()) {
                premiereListe = false;
            }
            paramAbsent = false;
        }

        if (paramAbsent) {
            return new ResponseEntity<>(dessertService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(listeFinale, HttpStatus.OK);
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

    @JsonView(Views.RecetteWithAll.class)
    @PostMapping(value = { "", "/" })
    public ResponseEntity<Dessert> addDessert(@RequestBody Dessert dessert, BindingResult br,
            @RequestParam(name = "auteur", required = true) String auteur, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Cherche utilisateur et ajoute comme auteur si présent
        Optional<Utilisateur> optUser = utilisateurService.findByPseudo(auteur);
        if (optUser.isPresent()) {
            dessert.setAuteur(optUser.get());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<ImageModel> optImg = imageService.findById(dessert.getImgRecette().getId());
        if (optImg.isPresent()) {
            dessert.setImgRecette(optImg.get());
        }
        dessert = dessertService.insert(dessert);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/dessert/{id}").buildAndExpand(dessert.getId()).toUri());
        return new ResponseEntity<>(dessert, headers, HttpStatus.CREATED);
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

    private List<Dessert> filterNameLike(List<Dessert> listeFinale, String namelike) {
        List<Dessert> listeFiltrante = dessertService.findByNomContaining(namelike);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Dessert> filterDiff(List<Dessert> listeFinale, Difficulte diff) {
        List<Dessert> listeFiltrante = dessertService.findByDifficulte(diff);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Dessert> filterDiffNot(List<Dessert> listeFinale, Difficulte nodiff) {
        List<Dessert> listeFiltrante = dessertService.findByDifficulteNot(nodiff);
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

    private List<Dessert> filterCout(List<Dessert> listeFinale, Couts cout) {
        List<Dessert> listeFiltrante = dessertService.findByCout(cout);
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

    private List<Dessert> filterCoutNot(List<Dessert> listeFinale, Couts nocout) {
        List<Dessert> listeFiltrante = dessertService.findByCoutNot(nocout);
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

    private List<Dessert> filterNote(List<Dessert> listeFinale, Double note) {
        List<Dessert> listeFiltrante = dessertService.findByNote(note);
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