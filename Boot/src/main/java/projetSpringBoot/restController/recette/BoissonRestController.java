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

import projetSpringBoot.model.imageModel.ImageModel;
import projetSpringBoot.model.recette.Boisson;
import projetSpringBoot.model.recette.Couts;
import projetSpringBoot.model.recette.Difficulte;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.ImageService;
import projetSpringBoot.service.recette.BoissonService;

@RestController
@RequestMapping("/rest/boisson")
@CrossOrigin(origins = "*")
public class BoissonRestController {
    @Autowired
    BoissonService boissonService;

    @Autowired
    ImageService imageService;
    /*
     * Get Mapping
     */

    @JsonView(Views.RecetteView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Boisson>> findAllBoisson() {
        return new ResponseEntity<List<Boisson>>(boissonService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Boisson>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Boisson>>(boissonService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/{id}")
    public ResponseEntity<Boisson> findById(@PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);
        return opt.map(boisson -> {
            return new ResponseEntity<Boisson>(boisson, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Boisson>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/{id}" })
    public ResponseEntity<Boisson> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);
        return opt.map(boisson -> {
            System.out.println(boisson.getTags());
            return new ResponseEntity<>(boisson, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Boisson>> findByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/notnom/{nom}")
    public ResponseEntity<List<Boisson>> findByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/nom/{nom}")
    public ResponseEntity<List<Boisson>> findAllByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/notnom/{nom}")
    public ResponseEntity<List<Boisson>> findAllByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/search")
    public ResponseEntity<List<Boisson>> search(@RequestParam(required = false) String namelike,
            @RequestParam(required = false) Difficulte diff, @RequestParam(required = false) Difficulte nodiff,
            @RequestParam(required = false) Couts cout, @RequestParam(required = false) Couts nocout,
            @RequestParam(required = false) Double note) {

        List<Boisson> listeFinale = new ArrayList<Boisson>();
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
            return new ResponseEntity<>(boissonService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(listeFinale, HttpStatus.OK);
    }

    // Check si nom recette existe déjà
    @GetMapping("/check/{string}")
    public ResponseEntity<Boolean> checkNom(@PathVariable("string") String string) {
        Optional<Boisson> opt = boissonService.findByNom(string);
        if (opt.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /*
     * Post Mapping
     */

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Boisson> addBoisson(@RequestBody Boisson boisson, BindingResult br,
            UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<ImageModel> optImg = imageService.findById(boisson.getImgRecette().getId());
        if (optImg.isPresent()) {
            boisson.setImgRecette(optImg.get());
        }
        Boisson boissonNew = boissonService.insert(boisson);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/boisson/{id}").buildAndExpand(boisson.getId()).toUri());
        return new ResponseEntity<>(boissonNew, headers, HttpStatus.CREATED);
    }

    /*
     * Delete Mapping
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);
        return opt.map(boisson -> {
            boissonService.deleteById(boisson.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    /*
     * Put Mapping
     */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Boisson boisson, @PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);

        return opt.map(temp -> {
            boissonService.update(boisson);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    private List<Boisson> filterNameLike(List<Boisson> listeFinale, String namelike) {
        List<Boisson> listeFiltrante = boissonService.findByNomContaining(namelike);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Boisson> filterDiff(List<Boisson> listeFinale, Difficulte diff) {
        List<Boisson> listeFiltrante = boissonService.findByDifficulte(diff);
        if (listeFiltrante.isEmpty()) {
            listeFinale.clear();
        } else if (listeFinale.isEmpty()) {
            listeFinale = listeFiltrante.stream().collect(Collectors.toList());
        } else {
            listeFinale = listeFinale.stream().filter(listeFiltrante::contains).collect(Collectors.toList());
        }
        return listeFinale;
    }

    private List<Boisson> filterDiffNot(List<Boisson> listeFinale, Difficulte nodiff) {
        List<Boisson> listeFiltrante = boissonService.findByDifficulteNot(nodiff);
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

    private List<Boisson> filterCout(List<Boisson> listeFinale, Couts cout) {
        List<Boisson> listeFiltrante = boissonService.findByCout(cout);
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

    private List<Boisson> filterCoutNot(List<Boisson> listeFinale, Couts nocout) {
        List<Boisson> listeFiltrante = boissonService.findByCoutNot(nocout);
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

    private List<Boisson> filterNote(List<Boisson> listeFinale, Double note) {
        List<Boisson> listeFiltrante = boissonService.findByNote(note);
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