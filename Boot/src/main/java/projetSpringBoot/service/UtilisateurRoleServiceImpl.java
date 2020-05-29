package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.UtilisateurRole;
import projetSpringBoot.repository.UtilisateurRoleRepository;

@Service
public class UtilisateurRoleServiceImpl implements UtilisateurRoleService {

	@Autowired
	private UtilisateurRoleRepository utilisateurRoleRepository;
	
	@Override
	public Boolean insert(UtilisateurRole ur) {
		utilisateurRoleRepository.save(ur);
		return true;
	}

	@Override
	public UtilisateurRole update(UtilisateurRole ur) {
		Optional<UtilisateurRole> opt = utilisateurRoleRepository.findById(ur.getId());
		if (opt.isPresent()) {
			UtilisateurRole urEnBase = opt.get();
			if (ur.getUtilisateur() != null) {
				urEnBase.setUtilisateur(ur.getUtilisateur());
			}
			if (ur.getRole() != null) {
				urEnBase.setRole(ur.getRole());
			}
			utilisateurRoleRepository.save(urEnBase);
			return urEnBase;
		} else {
			return null;
		}
	}

	@Override
	public List<UtilisateurRole> findAll() {
		return utilisateurRoleRepository.findAll();
	}

	@Override
	public void delete(UtilisateurRole ur) {
		utilisateurRoleRepository.delete(ur);
	}

	@Override
	public void deleteById(Integer id) {
		Optional<UtilisateurRole> opt = utilisateurRoleRepository.findById(id);
		if (opt.isPresent()) {
			utilisateurRoleRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Optional<UtilisateurRole> findById(Integer id) {
		return utilisateurRoleRepository.findById(id);
	}

}
