package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.exception.NoEtapeRecetteException;
import projetSpringBoot.model.recette.EtapeRecette;
import projetSpringBoot.repository.EtapeRecetteRepository;

@Service
public class EtapeRecetteServiceImpl implements EtapeRecetteService {

	@Autowired
	private EtapeRecetteRepository etapeRecetteRepository;

	@Override
	public Boolean insert(EtapeRecette etapeRecette) throws NoEtapeRecetteException {
		if (etapeRecette.getTexte().isEmpty()) {
			return false;
		}
		etapeRecetteRepository.save(etapeRecette);
		return true;
	}

	@Override
	public EtapeRecette update(EtapeRecette etapeRecette) {
		Optional<EtapeRecette> opt = etapeRecetteRepository.findById(etapeRecette.getId());
		if (opt.isPresent()) {
			EtapeRecette etapeRecetteEnBase = opt.get();
			if (etapeRecette.getTexte() != null) {
				etapeRecetteEnBase.setTexte(etapeRecette.getTexte());
			}
			if (etapeRecette.getPhoto() != null) {
				etapeRecetteEnBase.setPhoto(etapeRecette.getPhoto());
			}
			etapeRecetteRepository.save(etapeRecetteEnBase);
			return etapeRecetteEnBase;

		} else {
			return null;
		}
	}

	@Override
	public void delete(EtapeRecette etapeRecette) {
		delete(etapeRecette);
	}

	@Override
	public void deleteById(Integer id) {
		etapeRecetteRepository.deleteById(id);

	}

	public List<EtapeRecette> findAll() {
		return etapeRecetteRepository.findAll();
	}

	@Override
	public Optional<EtapeRecette> findById(Integer id) {
		return etapeRecetteRepository.findById(id);
	}
}
