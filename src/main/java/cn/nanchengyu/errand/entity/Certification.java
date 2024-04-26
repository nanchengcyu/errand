package cn.nanchengyu.errand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 骑手认证
 * @TableName certification
 */
@TableName(value ="certification")
@Data
public class Certification implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private Integer userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 本人照片
     */
    private String avatar;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String cardNo;

    /**
     * 身份证正面
     */
    private String card1;

    /**
     * 身份证反面
     */
    private String card2;

    /**
     * 常住地址
     */
    private String address;

    /**
     * 审核状态
     */
    private String status;

    /**
     * 审核理由
     */
    private String reason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}