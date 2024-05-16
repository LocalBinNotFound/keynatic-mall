package com.localbinnotfound.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.localbinnotfound.mall.modules.pms.model.PmsBrand;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-09
 */
public interface PmsBrandService extends IService<PmsBrand> {
    Page list(String keyword, Integer pageNum, Integer pageSize);

    boolean updateShowStatus(List<Long> ids, Integer showStatus);
    boolean updateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
