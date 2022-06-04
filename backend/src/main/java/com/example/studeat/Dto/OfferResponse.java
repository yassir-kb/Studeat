package com.example.studeat.Dto;

import com.example.studeat.Model.Offer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferResponse {

    private Offer offer;
    private String portalException;

    public OfferResponse(Offer offer, String portalException) {
        this.offer = offer;
        this.portalException = portalException;
    }
}
