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
import org.springframework.transaction.annotation.Transactional;
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
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.recette.Plat;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.ImageService;
import projetSpringBoot.service.UtilisateurService;
import projetSpringBoot.service.recette.PlatService;

@RestController
@RequestMapping("/rest/plat")
@CrossOrigin(origins = "*")
public class PlatRestController {
    @Autowired
    PlatService platService;

    @Autowired
    ImageService imageService;

    @Autowired
    UtilisateurService utilisateurService;
    /*
     * Get Mapping
     */

    @JsonView(Views.RecetteView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Plat>> findAllPlat() {
        return new ResponseEntity<List<Plat>>(platService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Plat>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Plat>>(platService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/{id}")
    public ResponseEntity<Plat> findById(@PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        return opt.map(plat -> {
            return new ResponseEntity<Plat>(plat, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Plat>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/{id}" })
    public ResponseEntity<Plat> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        return opt.map(plat -> {
            return new ResponseEntity<>(plat, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Plat>> findByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/notnom/{nom}")
    public ResponseEntity<List<Plat>> findByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/nom/{nom}")
    public ResponseEntity<List<Plat>> findAllByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/notnom/{nom}")
    public ResponseEntity<List<Plat>> findAllByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/search")
    public ResponseEntity<List<Plat>> search(@RequestParam(required = false) String namelike,
            @RequestParam(required = false) Difficulte diff, @RequestParam(required = false) Difficulte nodiff,
            @RequestParam(required = false) Couts cout, @RequestParam(required = false) Couts nocout,
            @RequestParam(required = false) Double note) {

        List<Plat> listeFinale = new ArrayList<Plat>();
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
            return new ResponseEntity<>(platService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(listeFinale, HttpStatus.OK);
    }

    // Check si nom recette existe déjà
    @GetMapping("/check/{string}")
    public ResponseEntity<Boolean> checkNom(@PathVariable("string") String string) {
        Optional<Plat> opt = platService.findByNom(string);
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
    public ResponseEntity<Plat> addPlat(@RequestBody Plat plat, BindingResult br,
            @RequestParam(name = "auteur", required = true) String auteur, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Cherche utilisateur et ajoute comme auteur si présent
        Optional<Utilisateur> optUser = utilisateurService.findByPseudo(auteur);
        if (optUser.isPresent()) {
            plat.setAuteur(optUser.get());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<ImageModel> optImg = imageService.findById(plat.getImgRecette().getId());
        if (optImg.isPresent()) {
            plat.setImgRecette(optImg.get());
        }
        plat = platService.insert(plat);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/plat/{id}").buildAndExpand(plat.getId()).toUri());
        return new ResponseEntity<>(plat, headers, HttpStatus.CREATED);
    }

    /*
     * Delete Mapping
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        return opt.map(plat -> {
            platService.deleteById(plat.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    /*
     * Put Mapping
     */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Plat plat, @PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        System.out.println(plat.getImgRecette().getId());

        return opt.map(temp -> {

            plat.setId(id);
            platService.update(plat);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    private List<Plat> filterNameLike(List<Plat> listeFinale, String namelike) {
        List<Plat> listeFiltrante = platService.findByNomContaining(namelike);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Plat> filterDiff(List<Plat> listeFinale, Difficulte diff) {
        List<Plat> listeFiltrante = platService.findByDifficulte(diff);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Plat> filterDiffNot(List<Plat> listeFinale, Difficulte nodiff) {
        List<Plat> listeFiltrante = platService.findByDifficulteNot(nodiff);
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

    private List<Plat> filterCout(List<Plat> listeFinale, Couts cout) {
        List<Plat> listeFiltrante = platService.findByCout(cout);
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

    private List<Plat> filterCoutNot(List<Plat> listeFinale, Couts nocout) {
        List<Plat> listeFiltrante = platService.findByCoutNot(nocout);
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

    private List<Plat> filterNote(List<Plat> listeFinale, Double note) {
        List<Plat> listeFiltrante = platService.findByNote(note);
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