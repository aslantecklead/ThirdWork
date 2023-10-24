package com.example.secondwork;

import com.example.secondwork.dao.*;
import com.example.secondwork.model.*;

public class CrudController {
    private ClientDAO clientDAO;
    private DealDAO dealDAO;
    private EstateDAO estateDAO;
    private OfferDAO offerDAO;
    private ShowingScheduleDAO showingScheduleDAO;

    public CrudController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public CrudController(DealDAO dealDAO) {
        this.dealDAO = dealDAO;
    }

    public CrudController(EstateDAO estateDAO) {
        this.estateDAO = estateDAO;
    }

    public CrudController(OfferDAO offerDAO) {
        this.offerDAO = offerDAO;
    }

    public CrudController(ShowingScheduleDAO showingScheduleDAO) {
        this.showingScheduleDAO = showingScheduleDAO;
    }

    public void insert(Object object) {
        if (object instanceof Client) {
            Client client = (Client) object;
            client.setId(ClientDAO.CLIENT_COUNT++);
            clientDAO.clients.add(client);
        } else if (object instanceof Deal) {
            Deal deal = (Deal) object;
            deal.setId(DealDAO.DEAL_COUNT++);
            dealDAO.deals.add(deal);
        } else if (object instanceof Estate) {
            Estate estate = (Estate) object;
            estate.setId(EstateDAO.ESTATE_COUNT++);
            estateDAO.estate.add(estate);
        } else if (object instanceof Offer) {
            Offer offer = (Offer) object;
            offer.setId(OfferDAO.OFFER_COUNT++);
            offerDAO.offers.add(offer);
        } else if (object instanceof ShowingSchedule) {
            ShowingSchedule showingSchedule = (ShowingSchedule) object;
            showingSchedule.setId(ShowingScheduleDAO.SCHEDULE_COUNT++);
            showingScheduleDAO.showingSchedules.add(showingSchedule);
        }
    }

    public void update(Object object) {
        int key = 0;
        if (object instanceof Client) {
            Client client = (Client) object;
            key = clientDAO.getKeyList(client);
            clientDAO.clients.set(key, client);
        } else if (object instanceof Deal) {
            Deal deal = (Deal) object;
            key = dealDAO.getKeyList(deal);
            dealDAO.deals.set(key, deal);
        } else if (object instanceof Estate) {
            Estate estate = (Estate) object;
            key = estateDAO.getKeyList(estate);
            estateDAO.estate.set(key, estate);
        } else if (object instanceof Offer) {
            Offer offer = (Offer) object;
            key = offerDAO.getKeyList(offer);
            offerDAO.offers.set(key, offer);
        } else if (object instanceof ShowingSchedule) {
            ShowingSchedule showingSchedule = (ShowingSchedule) object;
            key = showingScheduleDAO.getKeyList(showingSchedule);
            showingScheduleDAO.showingSchedules.set(key, showingSchedule);
        }
    }

    public void delete(Object object) {
        int id = 0;
        if(object instanceof Client){
            id = clientDAO.getKeyList((Client)object);
            clientDAO.clients.remove(id);
        }
        else if(object instanceof Deal){
            id = dealDAO.getKeyList((Deal)object);
            dealDAO.deals.remove(id);
        } else if(object instanceof Estate){
            id = estateDAO.getKeyList((Estate) object);
            estateDAO.estate.remove(id);
        } else if (object instanceof Offer) {
            id = offerDAO.getKeyList((Offer) object);
            offerDAO.offers.remove(id);
        } else if (object instanceof ShowingSchedule) {
            id = showingScheduleDAO.getKeyList((ShowingSchedule) object);
            showingScheduleDAO.showingSchedules.remove(id);
        }
    }
}
