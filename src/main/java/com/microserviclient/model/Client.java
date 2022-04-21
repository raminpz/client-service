package com.microserviclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Document(value = "clients")
public class Client {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String numberDucument;
    private String phone;
    private String address;
    private Date birthdate;

}
