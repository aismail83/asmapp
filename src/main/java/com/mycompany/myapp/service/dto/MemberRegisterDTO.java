package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import com.mycompany.myapp.repository.MemberRegisterRepository;
import com.mycompany.myapp.domain.MemberRegister;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException; 
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.MemberRegister} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MemberRegisterDTO implements Serializable {
    
   
    private Long id;
    
    @Size(max = 10)
    private String memberNumber;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 150)
    private String surName;

    @NotNull
    @Size(max = 10)
    private String registerDate;

    @NotNull
    @Size(max = 10)
    private String phoneNumber;

    private String adresse;

    @NotNull
    private String email;

    private String etat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String  memberNumber ) {
        this.memberNumber=memberNumber;
        
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberRegisterDTO)) {
            return false;
        }

        MemberRegisterDTO memberRegisterDTO = (MemberRegisterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, memberRegisterDTO.id);
    }

    
    

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }


    // prettier-ignore
    @Override
    public String toString() {
        return "MemberRegisterDTO{" +
            "id=" + getId() +
            ", lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", surName='" + getSurName() + "'" +
            ", registerDate='" + getRegisterDate() + "'" +
            ", memberNumber=" + getMemberNumber() +
            ", phoneNumber=" + getPhoneNumber() +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
