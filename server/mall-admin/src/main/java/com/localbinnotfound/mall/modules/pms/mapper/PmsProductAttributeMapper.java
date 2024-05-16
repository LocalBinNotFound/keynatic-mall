package com.localbinnotfound.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import com.localbinnotfound.mall.modules.pms.model.dto.PmsAttrInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    List<PmsAttrInfoDTO> getAttrInfoByCid(Long cId);
}
