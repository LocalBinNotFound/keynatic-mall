package com.localbinnotfound.mall;

import com.localbinnotfound.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {
    @Autowired
    PmsProductAttributeCategoryMapper mapper;

    @Test
    public void test1() {
        System.out.println(mapper.getListWithAttr());
    }
}
