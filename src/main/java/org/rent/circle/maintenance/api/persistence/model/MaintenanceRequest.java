package org.rent.circle.maintenance.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.maintenance.api.persistence.BaseModel;

@Entity
@Table(name = "maintenance_request")
@Setter
@Getter
@ToString
public class MaintenanceRequest extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "resident_id")
    private Long residentId;

    @Column(name = "property_id")
    private Long propertyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", updatable = false)
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "status")
    private String status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "maintenance_request_id", referencedColumnName = "id", nullable = false)
    private List<Labor> labors;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "maintenance_request_id", referencedColumnName = "id", nullable = false)
    private List<Billable> billables;
}
