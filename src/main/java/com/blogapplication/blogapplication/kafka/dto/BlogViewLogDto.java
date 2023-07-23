package com.blogapplication.blogapplication.kafka.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogViewLogDto {

    private Long blogId;

    private Long viewedBy;

    private LocalDateTime viewedAt;



}
