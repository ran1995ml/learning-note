package com.ran.shard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ran.shard.common.dto.ShardMergeDTO;
import com.ran.shard.common.dto.ShardUploadDTO;
import com.ran.shard.common.dto.ShardUploadTaskCreateDTO;
import com.ran.shard.common.dto.ShardUploadTaskDetailDTO;
import com.ran.shard.common.po.ShardUploadTaskPO;

/**
 * ShardUploadService
 *
 * @author rwei
 * @since 2025/2/8 13:19
 */
public interface ShardUploadService extends IService<ShardUploadTaskPO> {
    String createShardUploadTask(ShardUploadTaskCreateDTO shardUploadTaskCreateDTO);

    void uploadShard(ShardUploadDTO shardUploadDTO);

    void mergeShard(ShardMergeDTO shardMergeDTO);

    ShardUploadTaskDetailDTO getShardUploadTaskDetail(String shardUploadTaskId);
}
