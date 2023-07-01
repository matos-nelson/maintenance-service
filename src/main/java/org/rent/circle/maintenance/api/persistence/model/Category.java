package org.rent.circle.maintenance.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.maintenance.api.persistence.BaseModel;

@Entity
@Table(name = "category")
@Setter
@Getter
@ToString
public class Category extends BaseModel {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;
}
