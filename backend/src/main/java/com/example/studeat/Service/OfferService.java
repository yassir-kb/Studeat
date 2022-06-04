package com.example.studeat.Service;

import com.example.studeat.Controller.OfferRepository;
import com.example.studeat.Dto.OfferException;
import com.example.studeat.Model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer getOfferInfo(String id) throws OfferException {
        if (offerRepository.findAllById(id) == null) {
            throw new OfferException("Offer does not exist !");
        } else {
            return offerRepository.findAllById(id);
        }
    }

    public void updateOfferInfo(String id, Offer body) throws OfferException {
        Offer offer = offerRepository.findAllById(id);
        if (offer == null) {
            throw new OfferException("Offer cannot be updated !");
        } else {
            offer = body;
            offerRepository.save(offer);
        }
    }


    public void addProspectOffer(Offer offer) throws OfferException {
        for (int i = 0; i < offerRepository.findAll().size(); i++) {
            if (offerRepository.findAll().get(i).getTitle().equals(offer.getTitle()) && offerRepository.findAll().get(i).getRestauName().equals(offer.getRestauName()) && offerRepository.findAll().get(i).getLocalisation().equals(offer.getLocalisation())) {
                throw new OfferException("Offer incorrect or already added !");
            } else {
                break;
            }
        }
        offer.setStatus("Prospect");
        offerRepository.insert(offer);
    }

    public void deleteOffers() {
        offerRepository.deleteAll();
    }

    public void deleteOffer(String idOffer) throws OfferException {
        Offer offer = offerRepository.findAllById(idOffer);
        if (offer == null) {
            throw new OfferException("Offer does not exist !");
        } else {
            offerRepository.deleteById(idOffer);
        }
    }
}
