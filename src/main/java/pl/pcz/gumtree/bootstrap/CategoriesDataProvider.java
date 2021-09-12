package pl.pcz.gumtree.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pcz.gumtree.model.dao.CategoryEntity;
import pl.pcz.gumtree.repository.CategoryRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class CategoriesDataProvider implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {


        //################
        CategoryEntity nieruchomosci = generateEntity("Nieruchomosci", null);
        CategoryEntity pokojeDoWynajecia = generateEntity("Pokoje do wynajecia", nieruchomosci);
        CategoryEntity mieszkaniaIDomyDoWynajcia = generateEntity("Mieszkania i domy do wynajcia", nieruchomosci);

        nieruchomosci.getChildren().add(pokojeDoWynajecia);
        nieruchomosci.getChildren().add(mieszkaniaIDomyDoWynajcia);

        //################
        CategoryEntity Motoryzacja = generateEntity("Motoryzacja", null);

        //################
        CategoryEntity moda = generateEntity("moda", null);

        //################
        CategoryEntity elektronika = generateEntity("elektronika", null);


    }
    private CategoryEntity generateEntity(String name, CategoryEntity parent){
        return categoryRepository.save(CategoryEntity.builder()
                .name(name)
                .parent(parent)
                .children(new ArrayList<>())
                .build());
    }

}
