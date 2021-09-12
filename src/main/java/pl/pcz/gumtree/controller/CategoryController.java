package pl.pcz.gumtree.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pcz.gumtree.model.dto.CategoryResponse;
import pl.pcz.gumtree.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getMainCategories() {
        return categoryService.getMainCategories();
    }

    @GetMapping(path = "/{name}")
    public List<CategoryResponse> getSubCategories(@PathVariable String name) {
        return categoryService.getSubCategory(name);
    }

}
