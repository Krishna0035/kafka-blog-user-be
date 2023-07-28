package com.blogapplication.blogapplication.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllBlogs {
    private Long BlogId;
    private String name;
    private String Role;
    private String content;
    private String avatar;
}
