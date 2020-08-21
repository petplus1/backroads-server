package com.backroads.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="location")
public class Location implements Serializable {
    @Id
    @Column(name="location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "location",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Destination> destinations= new ArrayList<>();
    @Column(name="ind_search")
    private String ind_search;
    public Location() {
    }

    public Location(long location_id, String name) {
        this.location_id = location_id;
        this.name = name;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long id) {
        this.location_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference
    @JsonIgnore
    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public String getInd_search() {
        return ind_search;
    }

    public void setInd_search(String ind_search) {
        this.ind_search = ind_search;
    }
}
