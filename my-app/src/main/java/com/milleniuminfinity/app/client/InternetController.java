package com.milleniuminfinity.app.client;

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
    @RequestMapping(value = "/internet/", method = RequestMethod.POST)
    public ResponseEntity<Void> createInternet(@RequestBody Internet internet, UriComponentsBuilder ucBuilder)
    {
        internetService.create(internet);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/internet/{ipAddress}").buildAndExpand(internet.getIPAddress()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Retrieve a single internet service
    @RequestMapping(value = "/internet/{ipAddress}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Internet> getInternet(@PathVariable("ipAddress") String ipAddress)
    {
        Internet internet = internetService.readById(ipAddress);

        if(internet == null)
        {
            return new ResponseEntity<Internet>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Internet>(internet, HttpStatus.OK);
    }

    //Retrieve all internet services
    @RequestMapping(value = "/internet/", method = RequestMethod.GET)
    public ResponseEntity<Set<Internet>> getInternetServices()
    {
        Set<Internet> internetServices = internetService.readAll();

        if(internetServices.isEmpty())
        {
            return new ResponseEntity<Set<Internet>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Set<Internet>>(internetServices, HttpStatus.OK);
    }

    //Update an internet service
    @RequestMapping(value = "/internet/{ipAddress}", method = RequestMethod.PUT)
    public ResponseEntity<Internet> updateInternet(@PathVariable("ipAddress") String ipAddress, @RequestBody Internet internet)
    {
        Internet currentInternet = internetService.readById(ipAddress);

        if(currentInternet == null) {
            return new ResponseEntity<Internet>(HttpStatus.NOT_FOUND);
        }

        Internet updatedInternet = new Internet.Builder().copy(currentInternet)
                .planName(internet.getPlanName())
                .ISP(internet.getISP())
                .price(internet.getPrice())
                .dataAllowance(internet.getDataAllowance())
                .type(internet.getType())
                .build();

        internetService.update(updatedInternet);
        return new ResponseEntity<Internet>(updatedInternet, HttpStatus.OK);
    }

    //Delete an internet service
    @RequestMapping(value = "/internet/{ipAddress}", method = RequestMethod.DELETE)
    public ResponseEntity<Internet> deleteInternet(@PathVariable("ipAddress")String ipAddress)
    {
        Internet internet = internetService.readById(ipAddress);

        if(internet == null)
        {
            return new ResponseEntity<Internet>(HttpStatus.NOT_FOUND);
        }

        internetService.delete(internet);
        return new ResponseEntity<Internet>(HttpStatus.NO_CONTENT);
    }
}
