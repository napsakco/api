package co.napsak.api.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity(name = "place")
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", length = 99)
    private String name;
    @Column(name = "address", length = 399)
    private String address;
    @ManyToOne
    private User owner;
    @Column(name = "lat", precision = 10, scale = 7)
    private BigDecimal latitude;
    @Column(name = "lng", precision = 10, scale = 7)
    private BigDecimal longitude;
    @Column(name = "approved", columnDefinition = "boolean default false", nullable = false)
    private Boolean isApproved;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
//    private Set<Event> events = new HashSet<>();
}
