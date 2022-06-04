package com.example.studeat.Controller;

import com.example.studeat.Model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfferRepository extends MongoRepository<Offer, String> {
    Offer findAllById(String id);
}
