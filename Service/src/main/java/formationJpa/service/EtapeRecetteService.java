package formationJpa.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formationJpa.entity.recette.EtapeRecette;
import formationJpa.exception.NoEtapeRecetteException;
import formationJpa.repository.EtapeRecetteRepository;


@Service
public class EtapeRecetteService {
	
	@Autowired
	private EtapeRecetteRepository etapeRecetteRepository;
	
	
	public void insert(EtapeRecette etapeRecette) throws NoEtapeRecetteException {
		if(etapeRecette.getText().isEmpty()) {
			throw new NoEtapeRecetteException();
		}
		etapeRecetteRepository.save(etapeRecette);		
	}
	
	public EtapeRecette update(EtapeRecette etapeRecette) {
		Optional<EtapeRecette> opt=etapeRecetteRepository.findById(etapeRecette.getId());
		if (opt.isPresent()) {
			EtapeRecette etapeRecetteEnBase=opt.get();
			if(etapeRecette.getText()!=null) {
				etapeRecetteEnBase.setText(etapeRecette.getText());
			}
			if(etapeRecette.getPhoto()!=null) {
				etapeRecetteEnBase.setPhoto(etapeRecette.getPhoto());
			}
			etapeRecetteRepository.save(etapeRecetteEnBase);
			return etapeRecetteEnBase;
		
		}else {
			return null;
		}
	}
	
	public void delete(EtapeRecette etapeRecette) {
		delete(etapeRecette);
	}
	public void deleteById(Integer id) {
		etapeRecetteRepository.deleteById(id);
		
	}
		
	public Optional<EtapeRecette> findById(Integer id) {
		return etapeRecetteRepository.findById(id);
	}
}
