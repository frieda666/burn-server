package edu.uci.banerjee.burnserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "escapedfires")
public class EscapedFire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "escaped")
    private Boolean escaped;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "escaped_fire_id")
    private Fire fire;  

    public EscapedFire(String name, Boolean escaped) {
        this.name = name;
        this.escaped = escaped;
    }
}

