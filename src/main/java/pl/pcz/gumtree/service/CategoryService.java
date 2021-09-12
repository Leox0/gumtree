package pl.pcz.gumtree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.model.dto.CategoryResponse;
import pl.pcz.gumtree.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponse> getMainCategories() {

        return categoryRepository.findAllByParentNull()
                .stream()
                .map(entity -> CategoryResponse.builder()
                        .name(entity.getName())
                        .children(entity.getChildren().stream()
                                .map(children -> children.getName())
                                .collect(Collectors.toList())
                        ).build())
                .collect(Collectors.toList());

    }

    public List<CategoryResponse> getSubCategory(String name) {
        return categoryRepository.findByName(name).getChildren()
                .stream()
                .map(entity -> CategoryResponse.builder()
                        .name(entity.getName())
                        .children(entity.getChildren().stream()
                                .map(children -> children.getName())
                                .collect(Collectors.toList())
                        ).build())
                .collect(Collectors.toList());
    }


}
