package pl.pcz.gumtree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pcz.gumtree.exceptions.ExceptionReason;
import pl.pcz.gumtree.exceptions.GumtreeCopyException;
import pl.pcz.gumtree.model.dto.CategoryResponse;
import pl.pcz.gumtree.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
                        .mainCategoryName(entity.getName())
                        .subCategories(entity.getChildren().stream()
                                .map(children -> children.getName())
                                .collect(Collectors.toList())
                        ).build())
                .collect(Collectors.toList());

    }

    public CategoryResponse getSubCategory(String name) {
        return Optional.ofNullable(categoryRepository.findByName(name))
                .map(entity -> CategoryResponse.builder()
                        .mainCategoryName(entity.getName())
                        .subCategories(entity.getChildren().stream()
                                .map(children -> children.getName())
                                .collect(Collectors.toList())
                        ).build())
                .orElseThrow(()->new GumtreeCopyException(ExceptionReason.CATEGORY_NOT_AVAILABLE));
    }


}
