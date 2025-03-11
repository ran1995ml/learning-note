package com.ran.shard.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ShardUploadTaskPO
 *
 * @author rwei
 * @since 2025/2/8 11:07
 */
@Data
@TableName("t_shard_upload_task")
public class ShardUploadTaskPO {
    private String id;

    private String fileName;

    private int partNum;

    private String md5;

    private String fileFullPath;
}
