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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.UtilisateurService;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class UtilisateurRestController {
    @Autowired
    UtilisateurService utilisateurService;

    @JsonView(Views.UtilisateurView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Utilisateur>> findAll() {

        return new ResponseEntity<List<Utilisateur>>(utilisateurService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.UtilisateurView.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Utilisateur> findById(@PathVariable("id") Integer id) {
        Optional<Utilisateur> opt = utilisateurService.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> postUtilisateur(@RequestBody Utilisateur utilisateur, BindingResult br,
            UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        utilisateur = utilisateurService.insert(utilisateur);
        URI uri = uCB.path("/rest/commentaire/{id}").buildAndExpand(utilisateur.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        Optional<Utilisateur> opt = utilisateurService.findById(id);
        if (opt.isPresent()) {
            utilisateurService.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Utilisateur arc, @PathVariable("id") Integer id) {
        Optional<Utilisateur> opt = utilisateurService.findById(id);
        if (opt.isPresent()) {
            Utilisateur commEnBase = opt.get();

            commEnBase = utilisateurService.insert(commEnBase);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}