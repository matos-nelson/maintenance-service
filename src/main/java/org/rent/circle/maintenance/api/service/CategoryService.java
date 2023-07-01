package org.rent.circle.maintenance.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;
import org.rent.circle.maintenance.api.persistence.model.Category;
import org.rent.circle.maintenance.api.persistence.repository.CategoryRepository;
import org.rent.circle.maintenance.api.service.mapper.CategoryMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.listAll();
        return categoryMapper.toDtoList(categories);
    }
}
