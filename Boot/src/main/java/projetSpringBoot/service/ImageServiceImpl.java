package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.imageModel.ImageModel;
import projetSpringBoot.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public void delete(ImageModel t) {
        imageRepository.delete(t);

    }

    @Override
    public void deleteById(Integer id) {
        imageRepository.deleteById(id);

    }

    @Override
    public List<ImageModel> findAll() {
        return null;
    }

    @Override
    public Optional<ImageModel> findById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public ImageModel insert(ImageModel t) {
        return imageRepository.save(t);
    }

    @Override
    public ImageModel update(ImageModel t) {
        Optional<ImageModel> optIm = imageRepository.findById(t.getId());
        if (optIm.isPresent()) {
            ImageModel imBase = optIm.get();
            imBase.setImage(t.getImage());
            imBase.setName(t.getName());
            imBase.setType(t.getType());
            return imageRepository.save(imBase);
        } else {
            return null;
        }
    }

}