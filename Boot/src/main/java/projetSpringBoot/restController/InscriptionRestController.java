package projetSpringBoot.restController;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetSpringBoot.model.Roles;
import projetSpringBoot.model.Utilisateur;
import projetSpringBoot.model.UtilisateurRole;
import projetSpringBoot.service.UtilisateurRoleService;
import projetSpringBoot.service.UtilisateurService;

@RestController
@RequestMapping("/rest/inscription")
@CrossOrigin(origins = "*")
public class InscriptionRestController {

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	UtilisateurRoleService utilisateurRoleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> inscription(@Valid @RequestBody Utilisateur utilisateur, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		Optional<Utilisateur> opt = utilisateurService.findByPseudo(utilisateur.getPseudo());
		if (opt.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		Date now = new Date();

		utilisateur.setEnabled(true);
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateur.setDateInscription(now);
		utilisateurService.insert(utilisateur);

		UtilisateurRole role = new UtilisateurRole();
		role.setUtilisateur(utilisateur);
		role.setRole(Roles.ROLE_USER);
		utilisateurRoleService.insert(role);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("/check/{pseudo}")
	public ResponseEntity<Boolean> loginDispo(@PathVariable("pseudo") String pseudo) {
		Optional<Utilisateur> opt = utilisateurService.findByPseudo(pseudo);
		if (opt.isPresent()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
