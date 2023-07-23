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
public class BlogLikeLogDto {

    private Long id;

    private Long blogId;

    private Long likedBy;

    private LocalDateTime likedAt;
}
