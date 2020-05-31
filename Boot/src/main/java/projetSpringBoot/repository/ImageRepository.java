package projetSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetSpringBoot.model.imageModel.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {

}