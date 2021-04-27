package com.redbank.dtos.responses;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponseDTO {
    private String userToken;
    private String userId;
    private int userType;
    private Date authTokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;


}
