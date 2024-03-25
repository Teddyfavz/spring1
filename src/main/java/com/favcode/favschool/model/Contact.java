package com.favcode.favschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
/*@Data annotation provided by Lombok library which generates getter, setter, equals(),
hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.*/
@Data
public class Contact extends BaseEntity {
    private int contactId;
    /*
    * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
      @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
      @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
    * */
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 character long")
    private String name;

    @NotBlank(message = "Mobile number cannot be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Subject cannot be blank")
    @Size(min = 5, message = "Subject must be at least 5 character long")
    private String subject;

    @NotBlank(message = "Message cannot be blank")
    @Size(min = 10, message = "Message must be at least 10 character long")
    private String message;

    private String status;

}
