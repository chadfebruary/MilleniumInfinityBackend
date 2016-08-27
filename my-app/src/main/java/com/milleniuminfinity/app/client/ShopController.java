package com.milleniuminfinity.app.client;

import com.milleniuminfinity.app.domain.shop.Shop;
import com.milleniuminfinity.app.services.shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by cfebruary on 2016/08/27.
 */
@RestController
public class ShopController {
    //Inject service
    @Autowired
    private ShopService shopService;

    //Create a shop
    @RequestMapping(value = "/shop/", method = RequestMethod.POST)
    public ResponseEntity<Void> createShop(@RequestBody Shop shop, UriComponentsBuilder ucBuilder){
        shopService.create(shop);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/shop/{shopNumber}").buildAndExpand(shop.getShopNumber()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Retrieve a single shop
    @RequestMapping(value = "/shop/{shopNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> getEmployee(@PathVariable("shopNumber") String shopNumber){
        Shop shop = shopService.readById(shopNumber);

        if(shop == null){
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    }

    //Retrieve all shops
    @RequestMapping(value = "/shops/", method = RequestMethod.GET)
    public ResponseEntity<Set<Shop>> getShops(){
        Set<Shop> shops = shopService.readAll();

        if(shops.isEmpty()){
            return new ResponseEntity<Set<Shop>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Shop>>(shops, HttpStatus.OK);
    }

    //Update a shop
    @RequestMapping(value = "/shop/{shopNumber}", method = RequestMethod.PUT)
    public ResponseEntity<Shop> updateShop(@PathVariable("shopNumber") String shopNumber, @RequestBody Shop shop)
    {
        Shop currentShop = shopService.readById(shopNumber);

        if(currentShop == null)
        {
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }
        Shop updatedShop = new Shop.Builder().copy(currentShop)
                .shopName(shop.getShopName())
                .shopOwner(shop.getShopOwner())
                .shopPhoneNumber(shop.getShopPhoneNumber())
                .build();
        shopService.update(updatedShop);
        return new ResponseEntity<Shop>(updatedShop, HttpStatus.OK);
    }

    //Delete a shop
    @RequestMapping(value = "/shop/{shopNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<Shop> deleteEmployee(@PathVariable("shopNumber") String shopNumber)
    {
        Shop shop = shopService.readById(shopNumber);

        if(shop == null)
        {
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }

        shopService.delete(shop);
        return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
    }
}
