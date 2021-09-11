package pl.pcz.gumtree.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.pcz.gumtree.model.dao.CategoryEntity;
import pl.pcz.gumtree.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/categories")
    public List<CategoryEntity> getMainCategories() {
        return categoryService.getMainCategories();
    }
    @GetMapping(path = "/categories/{name}")
    public List<CategoryEntity> getSubCategories(@PathVariable String name) {
        return categoryService.getSubCategory(name);
    }

}
