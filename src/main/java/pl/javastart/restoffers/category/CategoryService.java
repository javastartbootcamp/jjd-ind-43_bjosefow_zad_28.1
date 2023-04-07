package pl.javastart.restoffers.category;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.offer.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getCategoriesNames() {
        return categoryRepository.findCategoriesNames();
    }

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        setCategoryValuesToDto(category, categoryDto);
        return categoryDto;
    }

    public CategoryDto insert(CategoryDto categoryDto) {
        Category categoryToDb = new Category();
        setDtoValuesToCategory(categoryDto, categoryToDb);
        categoryToDb.setOfferList(new ArrayList<>());
        categoryRepository.save(categoryToDb);
        return toDto(categoryToDb);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private void setCategoryValuesToDto(Category category, CategoryDto dto) {
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        if (!category.getOfferList().isEmpty()) {
            dto.setOffers(category.getOfferList().size());
        }
    }

    private void setDtoValuesToCategory(CategoryDto dto, Category category) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
    }
}
