package pl.pcz.gumtree.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pcz.gumtree.model.dto.CategoryResponse;
import pl.pcz.gumtree.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getMainCategories() {
        return categoryService.getMainCategories();
    }

    @GetMapping(path = "/{name}")
    public CategoryResponse getSubCategories(@PathVariable String name) {
        return categoryService.getSubCategory(name);
    }


}
