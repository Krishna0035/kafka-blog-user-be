package com.blogapplication.blogapplication.kafka.dto;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserLogDto {
    @SerializedName("id")
    private Long id;

    @SerializedName("email")
    private String email;

    @SerializedName("profileId")
    private String profileId;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    // Add constructors, getters, and setters (or lombok annotations) as needed
}
