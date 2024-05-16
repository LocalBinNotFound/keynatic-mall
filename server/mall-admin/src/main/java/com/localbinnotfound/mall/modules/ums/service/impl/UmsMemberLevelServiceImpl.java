package com.localbinnotfound.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localbinnotfound.mall.modules.ums.mapper.UmsMemberLevelMapper;
import com.localbinnotfound.mall.modules.ums.model.UmsMemberLevel;
import com.localbinnotfound.mall.modules.ums.service.UmsMemberLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author localbinnotfound
 * @since 2024-05-16
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        QueryWrapper<UmsMemberLevel> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(UmsMemberLevel::getDefaultStatus, defaultStatus);

        return this.list(queryWrapper);
    }
}
