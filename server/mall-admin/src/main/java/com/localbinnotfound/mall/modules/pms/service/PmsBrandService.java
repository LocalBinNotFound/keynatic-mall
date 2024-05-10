package com.localbinnotfound.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.localbinnotfound.mall.modules.pms.model.PmsBrand;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
public interface PmsBrandService extends IService<PmsBrand> {

    Page list(Integer pageNum, Integer pageSize);
}
