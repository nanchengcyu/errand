package cn.nanchengyu.errand.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 订单表
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 描述
     */
    private String descr;

    /**
     * 物品图片
     */
    private String img;

    /**
     * 物品类型
     */
    private String type;

    /**
     * 物品重量
     */
    private Double weight;

    /**
     * 小费
     */
    private Double price;

    /**
     * 发起人ID
     */
    private Integer userId;

    /**
     * 接单人ID
     */
    private Integer acceptId;

    /**
     * 创建时间
     */
    private String time;

    /**
     * 接单时间
     */
    private String acceptTime;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 取货地址ID
     */
    private Integer addressId;

    /**
     * 送货地址ID
     */
    private Integer targetId;

    /**
     * 订单备注
     */
    private String comment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    //下单人名称
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String acceptName;
}