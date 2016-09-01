package com.milleniuminfinity.app.services.shop.Impl;


import com.milleniuminfinity.app.domain.shop.Shop;
import com.milleniuminfinity.app.repository.shop.ShopRepository;
import com.milleniuminfinity.app.services.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chad on 5/8/2016.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Override
    public Shop create(Shop shop)
    {
        return shopRepository.save(shop);
    }

    @Override
    public Shop readById(String shopNumber)
    {
        return shopRepository.findOne(shopNumber);
    }

    @Override
    public Set<Shop> readAll()
    {
        Set<Shop> result = new HashSet<>();
        while(shopRepository.findAll().iterator().hasNext())
        {
            result.add(shopRepository.findAll().iterator().next());
        }
        return result;
    }

    @Override
    public Shop update(Shop shop)
    {
        return shopRepository.save(shop);
    }

    @Override
    public void delete(Shop shop)
    {
        shopRepository.delete(shop);
    }
}
