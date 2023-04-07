package pl.javastart.restoffers.category;

import pl.javastart.restoffers.offer.Offer;

import javax.persistence.*;
import java.util.List;

public class CategoryDto {

    private String name;

    private String description;
    private int offers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }
}
