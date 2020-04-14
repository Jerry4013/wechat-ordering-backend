package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);

    private final String BUYER_OPENID = "1101100";

    private final String ORDER_ID = "1586818935223154523";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Jerry123");
        orderDTO.setBuyerAddress("1225 Ma");
        orderDTO.setBuyerPhone("234567897");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123458");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123457");
        orderDetail1.setProductQuantity(2);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        logger.info("【创建订单】result = {}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        logger.info("【查询单个订单】result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        OrderDTO cancel = orderService.cancel(result);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), cancel.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}