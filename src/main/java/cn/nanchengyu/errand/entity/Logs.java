package cn.nanchengyu.errand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统日志
 * @TableName logs
 */
@TableName(value ="logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Logs implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 操作名称
     */
    private String operation;

    /**
     * 操作类型
     */
    private String type;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 操作人
     */
    private String user;

    /**
     * 系统日志
     */
    private String time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}