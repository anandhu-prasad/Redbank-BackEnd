package com.redbank.models;

import com.redbank.util.StringSequenceIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "individuals")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileInd {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individuals_seq")
    @GenericGenerator(name = "individuals_seq", strategy = "com.redbank.util.StringSequenceIdentifier", parameters = {
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.VALUE_PREFIX_PARAMETER, value = "IND"),
            @org.hibernate.annotations.Parameter(name = StringSequenceIdentifier.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    @Column(name="user_id")
    private String userId;

    @NotBlank(message = "Name field is required.")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters long.")
    private String name;

    @Column(name="blood_group")
    @NotBlank(message = "Blood group field is required.")
    @Pattern(regexp = "^B+|B-|A+|A-|AB+|AB-|O+|O_$", message="Invalid blood group.")
    private String bloodGroup;

    @Email
    @NotBlank(message = "Email field is required.")
    private String email;

    @Past
    private Date dob;

    @NotBlank(message = "Phone field is required.")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number.")
    private String phone;

    @NotBlank(message = "Address field is required.")
    private String address;

    @NotBlank(message = "State field is required.")
    private String state;

    @NotBlank(message = "District field is required.")
    private String district;

    @NotBlank(message = "Pin code field is required.")
    @Pattern(regexp = "^\\d{6}$", message = "Invalid pincode format.")
    private String pincode;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @Column(name = "last_donation_date")
    private Timestamp lastDonationDate;

    @Column(name="donor_status", columnDefinition = "int default 0")
    private int donorStatus;

    @NotBlank(message = "Password field is required.")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,50}$",
            message="Password must be og at least 8 characters long and should contain at least 1 Upper case letter, 1 Lower case letter, 1 Number and 1 Special character."
    )
    private String password;
    private String avatar;

    public ProfileInd(String name, String bloodGroup, String email, Date dob, String phone, String address, String state, String district, String pincode, Timestamp registrationDate, int donorStatus, String password) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.registrationDate = registrationDate;
        this.donorStatus = donorStatus;
        this.password = password;
    }
}
