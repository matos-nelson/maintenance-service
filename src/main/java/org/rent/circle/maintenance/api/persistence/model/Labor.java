package org.rent.circle.maintenance.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.maintenance.api.persistence.BaseModel;

@Entity
@Table(name = "labor")
@Setter
@Getter
@ToString
public class Labor extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maintenance_request_id", insertable = false, updatable = false, nullable = false)
    private Long maintenanceRequestId;

    @Column(name = "work_date")
    private LocalDateTime workDate;

    @Column(name = "hours")
    private Float hours;

    @Column(name = "description")
    private String description;
}
