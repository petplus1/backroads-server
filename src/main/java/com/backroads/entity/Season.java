package com.backroads.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "season")
public class Season implements Serializable {
    @Id
    @Column(name="season_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long season_id;
    @Column(name="name")
    private String name;
//    @JsonIgnore
    @OneToMany(mappedBy = "season",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Destination> destinations=new ArrayList<>();
    @Column(name="ind_search")
    private String ind_search;
    public Season() {
    }
    public Season(long season_id, String name) {
        this.season_id = season_id;
        this.name = name;
    }

    public Long getSeason_id() {
        return season_id;
    }

    public void setSeason_id(Long id) {
        this.season_id = id;
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
