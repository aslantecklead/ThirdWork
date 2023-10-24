package com.example.secondwork.dao;

import com.example.secondwork.model.ShowingSchedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ShowingScheduleDAO {

    public static int SCHEDULE_COUNT = 0;
    public List<ShowingSchedule> showingSchedules;
    {
        showingSchedules = new ArrayList<>();

        showingSchedules.add(new ShowingSchedule(++SCHEDULE_COUNT, "Property A", new Date(), "Client X"));
        showingSchedules.add(new ShowingSchedule(++SCHEDULE_COUNT, "Property B", new Date(), "Client Y"));
        showingSchedules.add(new ShowingSchedule(++SCHEDULE_COUNT, "Property C", new Date(), "Client Z"));
        showingSchedules.add(new ShowingSchedule(++SCHEDULE_COUNT, "Property D", new Date(), "Client W"));
        showingSchedules.add(new ShowingSchedule(++SCHEDULE_COUNT, "Property E", new Date(), "Client V"));
    }

    public List<ShowingSchedule> index() {
        return showingSchedules;
    }

    public ShowingSchedule show(long id) {
        return showingSchedules.stream().filter(schedule -> schedule.getId() == id).findFirst().orElse(null);
    }

    public int getKeyList(ShowingSchedule showingSchedule) {
        int key = -1;
        List<ShowingSchedule> books = this.index();
        for (int i = 0; i < books.size(); i++) {
            if(showingSchedule.getId() == books.get(i).getId()){
                key = i;
                break;
            }
        }
        return key;
    }
}
