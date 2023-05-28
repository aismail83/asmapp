package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.myapp.config.Constants;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;

/**
 * A user.
 */
@Entity
@Table(name = "membre")
public class Membre  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "membre_numbre", length = 10,unique = true)
    private String membreNumbre;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 256)
    @Column(name = "name_arabe", length = 256)
    private String nameArabe;

    @Size(max = 10)
    @Column(name = "registre_date", length = 10)
    private String registreDate;


    @Size(min = 10, max = 10)
    @Column(name = "phone_numbre", length = 10)
    private String phoneNumbre;


    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;

    @Size(max = 256)
    @Column(length = 256)
    private String adresse;

    @Size(max = 20)
    @Column(length = 20)
    private String etat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMembreNumbre() {
        return membreNumbre;
    }

    public void setMembreNumbre(String membreNumbre) {
        this.membreNumbre = membreNumbre;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
        return phoneNumbre;
    }

    public void setPhoneNumbre(String phoneNumbre) {
        this.phoneNumbre = phoneNumbre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    
}
