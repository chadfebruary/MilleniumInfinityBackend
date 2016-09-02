package com.milleniuminfinity.app.client;

import com.google.gson.Gson;
import com.milleniuminfinity.app.domain.internet.Internet;
import com.milleniuminfinity.app.services.internet.InternetService;
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
public class InternetController {

    //Inject service
    @Autowired
    private InternetService internetService;

    //Create an internet service
    @RequestMapping(value = "/internetService/", method = RequestMethod.POST)
    public ResponseEntity<Internet> createInternet(@RequestBody Internet newInternet, UriComponentsBuilder ucBuilder)
    {
        System.out.println("Creating request for: " + newInternet.toString());

        Internet internet = internetService.create(newInternet);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/internet/{ipAddress}").buildAndExpand(newInternet.getIPAddress()).toUri());

        return new ResponseEntity<Internet>(internet, HttpStatus.CREATED);
    }

    //Retrieve a single internet service
    @RequestMapping(value = "/internetService/{ipAddress}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Internet> getInternet(@PathVariable("ipAddress") String ipAddress)
    {
        System.out.println("Fetching internet service with IP: " + ipAddress);
        Internet request = internetService.readById(ipAddress);

        if(request == null)
        {
            System.out.println("Internet service with IP: " + ipAddress + "NOT FOUND");
            return new ResponseEntity<Internet>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Internet>(request, HttpStatus.OK);
    }

    //Retrieve all internet services
    @RequestMapping(value = "/internetServices/", method = RequestMethod.GET)
    public ResponseEntity<Set<Internet>> getInternetServices()
    {
        Set<Internet> requests = internetService.readAll();

        if(requests.isEmpty())
        {
            return new ResponseEntity<Set<Internet>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Set<Internet>>(requests, HttpStatus.OK);
    }

    //Update an internet service
    @RequestMapping(value = "/internetService/{ipAddress}", method = RequestMethod.PUT)
    public ResponseEntity<Internet> updateInternet(@PathVariable("ipAddress") String ipAddress, @RequestBody String internet)
    {
        Internet currentInternet = internetService.readById(ipAddress);

        if(currentInternet == null) {
            return new ResponseEntity<Internet>(HttpStatus.NOT_FOUND);
        }

        Gson gson = new Gson();
        Internet updatedInternet = new Internet.Builder()
                .copy(currentInternet)
                .planName(gson.fromJson(internet, Internet.class).getPlanName())
                .ISP(gson.fromJson(internet, Internet.class).getISP())
                .price(gson.fromJson(internet, Internet.class).getPrice())
                .dataAllowance(gson.fromJson(internet, Internet.class).getDataAllowance())
                .type(gson.fromJson(internet, Internet.class).getType())
                .build();

        internetService.update(updatedInternet);
        return new ResponseEntity<Internet>(updatedInternet, HttpStatus.OK);
    }

    //Delete an internet service
    @RequestMapping(value = "/internetService/{ipAddress}", method = RequestMethod.DELETE)
    public ResponseEntity<Internet> deleteInternet(@PathVariable("ipAddress")String ipAddress)
    {
        Internet request = internetService.readById(ipAddress);

        if(request == null)
        {
            return new ResponseEntity<Internet>(HttpStatus.NOT_FOUND);
        }

        internetService.delete(request);
        return new ResponseEntity<Internet>(HttpStatus.NO_CONTENT);
    }
}
