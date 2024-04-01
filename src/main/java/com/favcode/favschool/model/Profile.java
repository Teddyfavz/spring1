package com.favcode.favschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 character long")
    private String name;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Address1 cannot be blank")
    @Size(min = 5, message = "Address1 must be at least 5 character long")
    private String address1;

    private String address2;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 3, message = "City must be at least 5 character long")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 3, message = "State must be at least 5 character long")
    private String state;

    @NotBlank(message="Zip Code cannot be blank")
    @Pattern(regexp="(^$|[0-9]{5})",message = "Zip Code must be 5 digits")
    private String zipCode;
}
