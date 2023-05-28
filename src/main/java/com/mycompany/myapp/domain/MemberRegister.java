package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A MemberRegister.
 */
@Entity
@Table(name = "member_register")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MemberRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Size(max = 10)
    @Column(name = "member_number")
    private String memberNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 150)
    @Column(name = "sur_name", length = 150, nullable = false)
    private String surName;

    @NotNull
    @Size(max = 10)
    @Column(name = "register_date", length = 10, nullable = false)
    private String registerDate;

    @NotNull
    @Size(max = 10)
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "adresse")
    private String adresse;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "etat")
    private String etat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MemberRegister id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public MemberRegister lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public MemberRegister firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return this.surName;
    }

    public MemberRegister surName(String surName) {
        this.setSurName(surName);
        return this;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getRegisterDate() {
        return this.registerDate;
    }

    public MemberRegister registerDate(String registerDate) {
        this.setRegisterDate(registerDate);
        return this;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getMemberNumber() {
        return this.memberNumber;
    }

    public MemberRegister memberNumber(String memberNumber) {
        this.setMemberNumber(memberNumber);
        return this;
    }

    public void setMemberNumber(String memberNumer) {
        this.memberNumber = memberNumer;
    }

    public String  getPhoneNumber() {
        return this.phoneNumber;
    }

    public MemberRegister phoneNumber(String  phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String  phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public MemberRegister adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return this.email;
    }

    public MemberRegister email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtat() {
        return this.etat;
    }

    public MemberRegister etat(String etat) {
        this.setEtat(etat);
        return this;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberRegister)) {
            return false;
        }
        return id != null && id.equals(((MemberRegister) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    
    

    // prettier-ignore
    @Override
    public String toString() {
        return "MemberRegister{" +
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
