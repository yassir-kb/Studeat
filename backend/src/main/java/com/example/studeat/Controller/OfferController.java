package com.example.studeat.Controller;

import com.example.studeat.Dto.OfferResponse;
import com.example.studeat.Model.Offer;
import com.example.studeat.Dto.OfferException;
import com.example.studeat.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/offer")
public class OfferController {
    @Autowired
    private OfferService service;
    @Autowired
    private OfferRepository offerRepository;

    @GetMapping
    public List<Offer> getAllOffers() {
        return service.getAllOffers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResponse> getOfferInfo(@PathVariable String id) {
        try {
            Offer offer = service.getOfferInfo(id);
            return new ResponseEntity<>(new OfferResponse(offer, null), HttpStatus.OK);
        } catch (OfferException ce) {
            return new ResponseEntity<>(new OfferResponse(null, ce.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponse> updateOfferInfo(@PathVariable String id, @RequestBody Offer body) {
        try {
            service.updateOfferInfo(id, body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OfferException ce) {
            return new ResponseEntity<>(new OfferResponse(null, ce.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OfferResponse> addOfferProspect(@RequestBody Offer offer) {
        try {
            service.addProspectOffer(offer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            if (ex instanceof OfferException)
                return new ResponseEntity<>(new OfferResponse(null, ex.getMessage()), HttpStatus.CONFLICT);
            else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<OfferResponse> deleteOffers() {
        service.deleteOffers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idOffer}")
    public ResponseEntity<OfferResponse> deleteOffer(@PathVariable String idOffer) {
        try {
            service.deleteOffer(idOffer);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OfferException ce) {
            return new ResponseEntity<>(new OfferResponse(null, ce.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
