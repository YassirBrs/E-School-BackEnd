package com.virtualpairprogrammers.roombooking.model;

import com.virtualpairprogrammers.roombooking.model.entities.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
public enum Layout {
    PCs("pc"),
    Projector("Projector"),
    BOARD("BOARD");

    private String description;

    public String getDescription() {
        return description;
    }

}
