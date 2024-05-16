package com.localbinnotfound.mall;

import com.localbinnotfound.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.localbinnotfound.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.localbinnotfound.mall.modules.pms.model.PmsProductAttribute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {
    @Autowired
    PmsProductAttributeCategoryMapper mapper;
    @Autowired
    PmsProductAttributeMapper attributeMapper;

    @Test
    public void test1() {
        System.out.println(mapper.getListWithAttr());
    }

    @Test
    public void test2() {
        System.out.println(attributeMapper.getAttrInfoByCid(58L));
    }
}
