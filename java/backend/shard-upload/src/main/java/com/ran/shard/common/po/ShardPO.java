package com.ran.shard.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ShardPO
 *
 * @author rwei
 * @since 2025/2/8 11:12
 */
@Data
@TableName("t_shard_info")
public class ShardPO {
    private String id;

    private String uploadTaskId;

    private int partOrder;

    private String fileFullPath;
}
