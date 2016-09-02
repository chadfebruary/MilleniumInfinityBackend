package com.milleniuminfinity.app.client;

import com.google.gson.Gson;
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
    public ResponseEntity<Shop> createShop(@RequestBody Shop newShop, UriComponentsBuilder ucBuilder){
        System.out.println("Creating request for: " + newShop.toString());

        Shop shop = shopService.create(newShop);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/shop/{shopNumber}").buildAndExpand(newShop.getShopNumber()).toUri());

        return new ResponseEntity<Shop>(shop, HttpStatus.CREATED);
    }

    //Retrieve a single shop
    @RequestMapping(value = "/shop/{shopNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Shop> getEmployee(@PathVariable("shopNumber") String shopNumber){
        System.out.println("Fetching shop with number: " + shopNumber);
        Shop request = shopService.readById(shopNumber);

        if(request == null){
            System.out.println("Shop with number: " + shopNumber + " NOT FOUND");
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Shop>(request, HttpStatus.OK);
    }

    //Retrieve all shops
    @RequestMapping(value = "/shops/", method = RequestMethod.GET)
    public ResponseEntity<Set<Shop>> getShops(){
        Set<Shop> requests = shopService.readAll();

        if(requests.isEmpty()){
            return new ResponseEntity<Set<Shop>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Shop>>(requests, HttpStatus.OK);
    }

    //Update a shop
    @RequestMapping(value = "/shop/{shopNumber}", method = RequestMethod.PUT)
    public ResponseEntity<Shop> updateShop(@PathVariable("shopNumber") String shopNumber, @RequestBody String shop)
    {
        Shop currentRequest = shopService.readById(shopNumber);

        if(currentRequest == null)
        {
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }

        Gson gson = new Gson();
        Shop updatedShop = new Shop.Builder()
                .copy(currentRequest)
                .shopName(gson.fromJson(shop, Shop.class).getShopName())
                .shopOwner(gson.fromJson(shop, Shop.class).getShopOwner())
                .shopPhoneNumber(gson.fromJson(shop, Shop.class).getShopPhoneNumber())
                .build();

        shopService.update(updatedShop);
        return new ResponseEntity<Shop>(updatedShop, HttpStatus.OK);
    }

    //Delete a shop
    @RequestMapping(value = "/shop/{shopNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<Shop> deleteEmployee(@PathVariable("shopNumber") String shopNumber)
    {
        Shop request = shopService.readById(shopNumber);

        if(request == null)
        {
            return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
        }

        shopService.delete(request);
        return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
    }
}
