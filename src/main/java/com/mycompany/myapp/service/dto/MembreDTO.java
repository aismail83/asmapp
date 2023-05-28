package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Membre;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.*;

/**
 * A DTO representing a user, with his authorities.
 */
public class MembreDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;

    
    @Size(max = 50)
    private String lastName;

    @Size(max = 50)
    private String firstName;

    @Size(max = 256)
    private String nameArabe;

    @Size(max = 10)
    private String registreDate;


    @Size(min = 10, max = 10)
    private String phoneNumbre;


    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String adresse;

    @Size(max = 20)
    private String etat;
    public MembreDTO() {
        // Empty constructor needed for Jackson.
    }

    public MembreDTO(Membre membre) {
        this.id = membre.getId();
        this.firstName = membre.getFirstName();
        this.lastName = membre.getLastName();
        this.nameArabe = membre.getNameArabe();
        this.registreDate = membre.getRegistreDate();
        this.phoneNumbre = membre.getPhoneNumbre();
        this.email = membre.getEmail();
        this.adresse = membre.getAdresse();
        this.etat = membre.getEtat();

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameArabe() {
        return nameArabe;
    }

    public void setNameArabe(String nameArabe) {
        this.nameArabe = nameArabe;
    }


    public String getRegistreDate() {
        return registreDate;
    }

    public void setRegistreDate(String registreDate) {
        this.registreDate = registreDate;
    }

    public String getPhoneNumbre() {
        return  phoneNumbre;
    }

    public void setPhoneNumbre(String phoneNumbre) {
        this.phoneNumbre = phoneNumbre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getetat() {
        return etat;
    }

    public void setetat(String etat) {
        this.etat = etat;
    }

}
