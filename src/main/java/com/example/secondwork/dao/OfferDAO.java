package com.example.secondwork.dao;

import com.example.secondwork.model.Offer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OfferDAO {

    public static int OFFER_COUNT;
    public List<Offer> offers;
    {
        offers = new ArrayList<>();

        offers.add(new Offer(++OFFER_COUNT, "Luxury Apartment in Downtown", 750000.0, "John Doe"));
        offers.add(new Offer(++OFFER_COUNT, "Cozy House with Garden", 420000.0, "Jane Smith"));
        offers.add(new Offer(++OFFER_COUNT, "Office Space in Business District", 1200000.0, "Robert Johnson"));
        offers.add(new Offer(++OFFER_COUNT, "Seaside Villa with Ocean View", 2200000.0, "Emily Brown"));
        offers.add(new Offer(++OFFER_COUNT, "Spacious Warehouse in Industrial Area", 600000.0, "Michael Wilson"));
    }

    public List<Offer> index() {
        return offers;
    }

    public Offer show(long id) {
        return offers.stream().filter(offer -> offer.getId() == id).findFirst().orElse(null);
    }

    public int getKeyList(Offer offer) {
        int key = -1;
        List<Offer> books = this.index();
        for (int i = 0; i < books.size(); i++) {
            if(offer.getId() == books.get(i).getId()){
                key = i;
                break;
            }
        }
        return key;
    }
}
