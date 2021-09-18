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
        CategoryEntity motoryzacja = generateEntity("Motoryzacja", null);
        CategoryEntity samochodyOsobowe = generateEntity("Samochody osobowe", motoryzacja);
        CategoryEntity samochodyDostawcze = generateEntity("Samochody dostawcze", motoryzacja);
        CategoryEntity quady = generateEntity("Quady", motoryzacja);
        CategoryEntity motocykle = generateEntity("Motocykle", motoryzacja);
        CategoryEntity ciagniki = generateEntity("Ciagniki", motoryzacja);

        motoryzacja.getChildren().add(samochodyOsobowe);
        motoryzacja.getChildren().add(samochodyDostawcze);
        motoryzacja.getChildren().add(quady);
        motoryzacja.getChildren().add(motocykle);
        motoryzacja.getChildren().add(ciagniki);


        //################
        CategoryEntity moda = generateEntity("Moda", null);
        CategoryEntity obuwie = generateEntity("Obuwie", moda);
        CategoryEntity odziez = generateEntity("Odziez", moda);
        CategoryEntity bizuteria = generateEntity("bizuteria", moda);

        moda.getChildren().add(obuwie);
        moda.getChildren().add(odziez);
        moda.getChildren().add(bizuteria);

        //################
        CategoryEntity elektronika = generateEntity("Elektronika", null);


    }
    private CategoryEntity generateEntity(String name, CategoryEntity parent){
        return categoryRepository.save(CategoryEntity.builder()
                .name(name)
                .parent(parent)
                .children(new ArrayList<>())
                .build());
    }

}
