package com.localbinnotfound.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3PolicyResult {
    private String dir;
    private String host;
    private long expire;
}