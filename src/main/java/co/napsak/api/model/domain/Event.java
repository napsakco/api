package co.napsak.api.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "event")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToOne
    private Place place;
    @Column
    private Long creationDate;
    @Column
    private Long startDate;
    @Column
    private Long endDate;
    @Column(name = "approved", columnDefinition = "boolean default false", nullable = false)
    private Boolean approved;
}
