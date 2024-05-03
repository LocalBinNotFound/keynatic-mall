package com.localbinnotfound.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 产品评价回复表
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-02
 */
@Getter
@Setter
@TableName("pms_comment_replay")
@ApiModel(value = "PmsCommentReplay对象", description = "产品评价回复表")
public class PmsCommentReplay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long commentId;

    private String memberNickName;

    private String memberIcon;

    private String content;

    private Date createTime;

    @ApiModelProperty("评论人员类型；0->会员；1->管理员")
    private Integer type;


}
