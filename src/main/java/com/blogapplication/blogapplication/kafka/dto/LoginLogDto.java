package com.blogapplication.blogapplication.kafka.dto;


import com.google.gson.annotations.SerializedName;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogDto {

//    @SerializedName("id")
    private Long id;

//    @SerializedName("loginAt")
    private LocalDateTime loginAt;

//    @SerializedName("channel")
    private String channel;
}
