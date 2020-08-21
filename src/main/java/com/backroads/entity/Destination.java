package com.backroads.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="destination")
public class Destination implements Serializable {
    @Id
    @Column(name="destination_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="country")
    private String country;
    @Column(name="price")
    private int price;
    @Column(name="d_from")
    private String d_from;
    @Column(name="d_to")
    private String d_to;
    @Lob
    @Column(name="description")
    private String description;
    @Column(name="picture")
    private String picture;
    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Location location;
    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Season season;

    @OneToMany(mappedBy = "destination")
    private Set<Reservation> reservations=new HashSet<>();
    public Destination() {
    }

    public Destination(Long id, String name, String country, int price, String d_from, String d_to, String description, String picture, Location location, Season season) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.price = price;
        this.d_from = d_from;
        this.d_to = d_to;
        this.description = description;
        this.picture = picture;
        this.location = location;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getD_from() {
        return d_from;
    }

    public void setD_from(String from) {
        this.d_from = from;
    }

    public String getD_to() {
        return d_to;
    }

    public void setD_to(String to) {
        this.d_to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    @JsonBackReference
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    @JsonBackReference
    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
    @JsonIgnore
    public Set<Reservation> getReservations() {
        return reservations;
    }
    @JsonIgnore
    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
