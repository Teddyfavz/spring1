package com.favcode.favschool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int addressId;

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
    private int zipCode;
}
