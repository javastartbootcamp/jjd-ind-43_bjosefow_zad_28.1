package pl.javastart.restoffers.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<CategoryDto> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/names")
    public List<String> getCategoriesNames() {
        return categoryService.getCategoriesNames();
    }

    @PostMapping("")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.insert(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
