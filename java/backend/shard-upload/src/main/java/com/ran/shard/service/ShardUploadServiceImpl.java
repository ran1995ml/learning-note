package com.ran.shard.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ran.shard.common.dto.ShardMergeDTO;
import com.ran.shard.common.dto.ShardUploadDTO;
import com.ran.shard.common.dto.ShardUploadTaskCreateDTO;
import com.ran.shard.common.dto.ShardUploadTaskDetailDTO;
import com.ran.shard.common.po.ShardPO;
import com.ran.shard.common.po.ShardUploadTaskPO;
import com.ran.shard.mapper.ShardMapper;
import com.ran.shard.mapper.ShardUploadTaskMapper;
import com.ran.shard.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * ShardUploadServiceImpl
 *
 * @author rwei
 * @since 2025/2/8 13:33
 */
@Service
public class ShardUploadServiceImpl extends ServiceImpl<ShardUploadTaskMapper, ShardUploadTaskPO> implements ShardUploadService {
    @Autowired
    private ShardMapper shardMapper;

    @Override
    public String createShardUploadTask(ShardUploadTaskCreateDTO shardUploadTaskCreateDTO) {
        ShardUploadTaskPO shardUploadTaskPO = new ShardUploadTaskPO();
        shardUploadTaskPO.setId(IdUtils.generateId());
        shardUploadTaskPO.setFileName(shardUploadTaskCreateDTO.getFileName());
        shardUploadTaskPO.setPartNum(shardUploadTaskCreateDTO.getPartNum());
        this.save(shardUploadTaskPO);
        return shardUploadTaskPO.getId();
    }

    @Override
    public void uploadShard(ShardUploadDTO shardUploadDTO) {
        LambdaQueryWrapper<ShardPO> queryWrapper = Wrappers.lambdaQuery(ShardPO.class)
                .eq(ShardPO::getUploadTaskId, shardUploadDTO.getShardUploadTaskId())
                .eq(ShardPO::getPartOrder, shardUploadDTO.getPartOrder());
        ShardPO shardPO = this.shardMapper.selectOne(queryWrapper);
        if (!Objects.isNull(shardPO)) return;
    }

    @Override
    public void mergeShard(ShardMergeDTO shardMergeDTO) {

    }

    @Override
    public ShardUploadTaskDetailDTO getShardUploadTaskDetail(String shardUploadTaskId) {
        return null;
    }
}
