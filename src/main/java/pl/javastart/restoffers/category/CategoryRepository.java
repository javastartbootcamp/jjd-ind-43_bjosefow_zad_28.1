package pl.javastart.restoffers.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.name from Category c")
    List<String> findCategoriesNames();

    Category getCategoriesByName(String categoryName);
}
