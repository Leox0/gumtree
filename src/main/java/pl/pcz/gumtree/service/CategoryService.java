package pl.pcz.gumtree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.model.dao.CategoryEntity;
import pl.pcz.gumtree.repository.category.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> getMainCategories(){
        return categoryRepository.findAllByParentNull();
    }

    public List<CategoryEntity> getSubCategory(String name){
        return categoryRepository.findByName(name).getChildren();
    }
}
