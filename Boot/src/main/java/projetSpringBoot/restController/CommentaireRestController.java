package projetSpringBoot.restController;

import java.net.URI;
import java.util.Date;
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

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.CommentaireKey;
import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.CommentaireService;
import projetSpringBoot.service.UtilisateurService;
import projetSpringBoot.service.recette.RecetteService;

@RestController
@RequestMapping("/rest/commentaire")
@CrossOrigin(origins = "*")
public class CommentaireRestController {
    @Autowired
    CommentaireService commentaireService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    RecetteService recetteService;

    @JsonView(value = { Views.CommentaireView.class })
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Commentaire>> findAll() {

        return new ResponseEntity<List<Commentaire>>(commentaireService.findAll(), HttpStatus.OK);
    }

    @JsonView(value = { Views.CommentaireListing.class })
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Commentaire>> findById(@PathVariable("id") Integer id) {
        Optional<Recette> optRecette = recetteService.findById(id);
        if (optRecette.isPresent()) {
            List<Commentaire> listeComm = commentaireService.findByRecette(optRecette.get().getId());
            return new ResponseEntity<>(listeComm, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> postCommentaire(@RequestBody Commentaire arc, BindingResult br,
            @RequestParam(name = "auteur", required = true) String auteur,
            @RequestParam(name = "id_recette", required = true) Integer idRecette, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        CommentaireKey commentKey = new CommentaireKey();

        // Cherche utilisateur et ajoute comme auteur si présent
        Optional<Utilisateur> optUser = utilisateurService.findByPseudo(auteur);
        if (optUser.isPresent()) {
            commentKey.setAuteur(optUser.get());
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        // Cherche recette et ajoute a commentaire si présente
        Optional<Recette> optRecette = recetteService.findById(idRecette);
        if (optRecette.isPresent()) {
            commentKey.setRecette(optRecette.get());
        } else {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        // Chech si utilisateur a dejà ecris comm pour la recette en cours
        Optional<Commentaire> optComm = commentaireService.findById(commentKey);
        if (optComm.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        arc.setId(commentKey);

        Integer note = arc.getNote();
        note = (note > 5) ? 5 : (note < 1) ? 1 : note;
        arc.setNote(note);

        Date now = new Date();
        arc.setDateCommentaire(now);
        arc = commentaireService.insert(arc);
        URI uri = uCB.path("/rest/commentaire/{id}").buildAndExpand(arc.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable CommentaireKey id) {
        Optional<Commentaire> opt = commentaireService.findById(id);
        if (opt.isPresent()) {
            commentaireService.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Commentaire arc, @PathVariable("id") CommentaireKey id) {
        Optional<Commentaire> opt = commentaireService.findById(id);
        if (opt.isPresent()) {
            Commentaire commEnBase = opt.get();

            commEnBase = commentaireService.insert(commEnBase);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}