package hh.palvelinohjelmointi.bikerental.repository;

import hh.palvelinohjelmointi.bikerental.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>{

     Optional<Category> findCategoryByCategoryName(String categoryName);

}
