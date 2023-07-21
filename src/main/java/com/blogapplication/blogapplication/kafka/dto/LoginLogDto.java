package com.blogapplication.blogapplication.kafka.dto;


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


    private Long id;

    private LocalDateTime loginAt;

    private String channel;
}
