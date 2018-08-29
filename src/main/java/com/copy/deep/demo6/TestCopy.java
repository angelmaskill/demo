package com.copy.deep.demo6;

import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.beans.BeanCopier;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: yanlu.myl
 * @Description:
 * @Date: Created on 2018/8/29 17:50
 * @Modified By:
 */
public class TestCopy {
    @Test
    public void normalCopyTest() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setName("orderName");

        List<Address> addressList = new ArrayList<>();
        Address address1 = new Address("BEIJING STREET",1);
        Address address2 = new Address("HENAN STREET",2);
        addressList.add(address1);
        addressList.add(address2);
        entity.setAddressList(addressList);

        final BeanCopier copier = BeanCopier.create(OrderEntity.class, OrderDto.class, false);
        OrderDto dto = new OrderDto();
        copier.copy(entity, dto, null);
        System.out.println(dto.getAddressList().get(0).getAddressName());
        Assert.assertEquals(1, dto.getId());
        Assert.assertEquals("orderName", dto.getName());
        Assert.assertEquals(2, dto.getAddressList().size());
    }

    @Test
    public void sameNameDifferentTypeCopyTest() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1);
        entity.setName("orderName");
        final BeanCopier copier = BeanCopier.create(OrderEntity.class, PropWithDiffType.class,
            false);
        PropWithDiffType dto = new PropWithDiffType();
        copier.copy(entity, dto, null);
        Assert.assertEquals(null,
            dto.getId()); // OrderEntity的id为int类型，而PropWithDiffType的id为Integer类型，不拷贝
        Assert.assertEquals("orderName", dto.getName());
    }
}
