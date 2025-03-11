package com.ran.shard.common.dto;

import lombok.Data;

import java.util.List;

/**
 * ShardUploadTaskDetailDTO
 *
 * @author rwei
 * @since 2025/2/8 13:29
 */
@Data
public class ShardUploadTaskDetailDTO {
    private String shardUploadTaskId;

    private int partNum;

    private boolean success;

    private List<Integer> completedPartList;
}
