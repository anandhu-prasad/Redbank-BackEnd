package com.redbank.models;

import com.redbank.util.StringSequenceIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "blood_banks")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileBb {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_banks_seq")
    @GenericGenerator(name = "blood_banks_seq", strategy = "com.redbank.util.StringSequenceIdentifier", parameters = {
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "BOB"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    @Column(name="user_id")
    private String userId;

    @NotBlank(message = "Name field is required.")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters long.")
    private String name;

    @Email
    @NotBlank(message = "Email field is required.")
    private String email;

    @NotBlank(message = "License number is required.")
    @Column(name = "license_number")
    private String licenseNumber;

    private String phone1;

    private String phone2;

    private String phone3;

    private String phone4;

    private String phone5;

    @NotBlank(message = "Address field is required.")
    private String address;

    @NotBlank(message = "State field is required.")
    private String state;

    @NotBlank(message = "District field is required.")
    private String district;

    @NotBlank(message = "Pin code field is required.")
    @Pattern(regexp = "^\\d{6}$", message = "Invalid pincode format.")
    private String pincode;

    @NotBlank(message = "Password field is required.")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,50}$",
            message="Password must be og at least 8 characters long and should contain at least 1 Upper case letter, 1 Lower case letter, 1 Number and 1 Special character."
    )
    private String password;

    @Column(name = "registration_date")
    private Timestamp registrationDate;
    private String avatar;

    public ProfileBb(String name, String email, String licenseNumber, String phone1, String phone2, String phone3, String phone4, String phone5, String address, String state, String district, String pincode, String password, Timestamp registrationDate) {
        this.name = name;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.phone4 = phone4;
        this.phone5 = phone5;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.password = password;
        this.registrationDate = registrationDate;
    }
}
