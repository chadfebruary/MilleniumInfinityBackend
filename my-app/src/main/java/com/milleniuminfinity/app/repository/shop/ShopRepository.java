package com.milleniuminfinity.app.repository.shop;

import com.milleniuminfinity.app.domain.shop.Shop;
import com.milleniuminfinity.app.repository.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 208023429 on 4/21/2016.
 */
@org.springframework.stereotype.Repository
public interface ShopRepository extends CrudRepository<Shop, String> {

}
