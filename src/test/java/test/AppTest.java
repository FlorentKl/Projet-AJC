package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import formationJpa.config.Config;
import formationJpa.entity.Utilisateur;
import formationJpa.entity.tag.Tag;
import formationJpa.service.TagService;
import formationJpa.service.UtilisateurService;

public class AppTest {

    public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		//utilisateur
		UtilisateurService utilisateurService = ctx.getBean(UtilisateurService.class);
		Utilisateur vincent = new Utilisateur(100, "vincent", "nouveaupassword");
//		utilisateurService.add(vincent);
		utilisateurService.update(vincent);
//		System.out.println(utilisateurService.findById(101).get());
		
		
		//tag
//		TagService tagService = ctx.getBean(TagService.class);
//		Tag pates = new Tag("pates");
//		tagService.add(pates);
		
		
		ctx.close();
    }

}
