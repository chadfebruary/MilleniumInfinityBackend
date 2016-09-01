package com.milleniuminfinity.app.repository.shop;

import com.milleniuminfinity.app.domain.shop.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by 208023429 on 4/21/2016.
 */
@Repository
public interface ShopRepository extends CrudRepository<Shop, String> {

}
