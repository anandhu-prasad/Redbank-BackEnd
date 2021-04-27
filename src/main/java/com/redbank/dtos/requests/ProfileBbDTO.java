package com.redbank.dtos.requests;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileBbDTO {

    @NotBlank(message = "Name field is required.")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters long.")
    private String name;

    @Email
    @NotBlank(message = "Email field is required.")
    private String email;

    private List<String> phone;

    @NotBlank(message = "License number is required.")
    private String licenseNumber;

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

}
