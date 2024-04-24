package cn.nanchengyu.errand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 联系人信息
 * @TableName address
 */
@TableName(value ="address")
@Data
public class Address implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 地址
     */
    private String address;

    /**
     * 门牌号
     */
    private String doorNo;

    /**
     * 联系人
     */
    private String userName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 关联用户ID
     */
    private Integer userId;
    //关联用户名称
    @TableField(exist = false)
    private String user;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}