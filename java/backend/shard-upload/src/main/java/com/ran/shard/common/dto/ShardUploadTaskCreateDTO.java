package com.ran.shard.common.dto;

import lombok.Data;

/**
 * ShardUploadTaskCreateDTO
 *
 * @author rwei
 * @since 2025/2/8 13:21
 */
@Data
public class ShardUploadTaskCreateDTO {
    private String fileName;

    private int partNum;

    private String md5;
}
