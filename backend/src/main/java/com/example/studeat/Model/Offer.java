package com.example.studeat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    private String id;
    private String title;
    private String restauName;
    private String localisation;
    private String price;
    private String contract;
    private String description;
    private String status;
}
