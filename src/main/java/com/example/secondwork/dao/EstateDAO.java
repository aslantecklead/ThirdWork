package com.example.secondwork.dao;

import com.example.secondwork.model.Estate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstateDAO {

    public static int ESTATE_COUNT;
    public
    List<Estate> estate;
    {
        estate = new ArrayList<>();

        estate.add(new Estate(++ESTATE_COUNT,"123 Main Street, City A", 3, 2, 350000.0));
        estate.add(new Estate(++ESTATE_COUNT,"456 Elm Avenue, City B", 4, 3, 420000.0));
        estate.add(new Estate(++ESTATE_COUNT,"789 Oak Road, City C", 2, 1, 200000.0));
        estate.add(new Estate(++ESTATE_COUNT,"101 Pine Lane, City D", 5, 4, 550000.0));
        estate.add(new Estate(++ESTATE_COUNT, "202 Maple Drive, City E", 3, 2, 290000.0));
        
    }

    public List<Estate> index(){
        return estate;
    }

    public Estate show(int id){
        return estate.stream().filter(personModel -> personModel.getId() == id).findAny().orElse(null);
    }

    public int getKeyList(Estate estate) {
        int key = -1;
        List<Estate> books = this.index();
        for (int i = 0; i < books.size(); i++) {
            if(estate.getId() == books.get(i).getId()){
                key = i;
                break;
            }
        }
        return key;
    }
}
