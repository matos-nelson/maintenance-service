package org.rent.circle.maintenance.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;
import org.rent.circle.maintenance.api.persistence.model.Category;

@Mapper(componentModel = "cdi")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);
}
