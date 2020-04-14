package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findById(1).orElse(null);
        System.out.println(productCategory);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(4);
        productCategory.setCategoryName("新商品");
        repository.save(productCategory);
//        ProductCategory productCategory = repository.findById(2).orElse(null);
//        if (productCategory != null) {
//            productCategory.setCategoryType(10);
//            repository.save(productCategory);
//        }
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}


