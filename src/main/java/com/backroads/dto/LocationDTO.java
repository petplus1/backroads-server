package com.backroads.dto;

import javax.persistence.Column;

public class LocationDTO {
    private Long id;
    private String name;
    private String ind_search;
    public LocationDTO() {
    }

    public LocationDTO(Long id, String name, String ind_search) {
        this.id = id;
        this.name = name;
        this.ind_search = ind_search;
    }

    public String getInd_search() {
        return ind_search;
    }

    public void setInd_search(String ind_search) {
        this.ind_search = ind_search;
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
}
