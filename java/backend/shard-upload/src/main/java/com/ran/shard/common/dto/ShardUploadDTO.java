package com.ran.shard.common.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * ShardDTO
 *
 * @author rwei
 * @since 2025/2/8 13:22
 */
@Data
public class ShardUploadDTO {
    private String shardUploadTaskId;

    private int partOrder;

    private MultipartFile file;
}
