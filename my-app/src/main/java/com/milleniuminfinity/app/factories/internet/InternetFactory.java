package com.milleniuminfinity.app.factories.internet;

import java.io.Serializable;

/**
 * Write a description of class InternetFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InternetFactory implements Serializable
{
   /* public static Internet getInternetType(String type, String ISP, String planName, String price, String dataAllowance)
    {
        if(type.equalsIgnoreCase("ADSL")) {

            Internet adsl = new ADSL.Builder()
                    .ISP(ISP)
                    .planName(planName)
                    .price(price)
                    .dataAllowance(dataAllowance)
                    .build();

            return adsl;
        }
        else if(type.equalsIgnoreCase("Fibre"))
        {
            Internet fibre = new Fibre.Builder()
                    .ISP(ISP)
                    .planName(planName)
                    .price(price)
                    .dataAllowance(dataAllowance)
                    .build();

            return fibre;
        }
        else
        {
            Internet mobile = new Mobile.Builder()
                    .ISP(ISP)
                    .planName(planName)
                    .price(price)
                    .dataAllowance(dataAllowance)
                    .build();

            return mobile;
        }
    }*/
}
