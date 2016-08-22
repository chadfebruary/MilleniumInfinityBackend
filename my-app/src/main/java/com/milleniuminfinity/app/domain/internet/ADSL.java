package com.milleniuminfinity.app.domain.internet;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Write a description of class ADSL here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
@javax.persistence.Entity
public class ADSL implements Serializable, Internet
{
    /*@Override
    public String handleRequest(int request)
    {
        if(request == 1)
        {
            return "ADSL";
        }
        else 
        {
            if(nextConnectionType != null)
            {
                return nextConnectionType.handleRequest(request);
                
            }
            
            return "Invalid option";
        }
    }*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ipAddress, ISP, planName;
    String price, dataAllowance;
    private Internet nextInternetType;

    private ADSL(){}

    private ADSL(Builder builder)
    {
        this.ipAddress = builder.ipAddress;
        this.ISP = builder.ISP;
        this.planName = builder.planName;
        this.price = builder.price;
        this.dataAllowance = builder.dataAllowance;
    }

    @Override
    public String getIPAddress()
    {
        return ipAddress;
    }

    @Override
    public String getISP()
    {
        return ISP;
    }

    @Override
    public String getPlanName()
    {
        return planName;
    }

    @Override
    public String getPrice()
    {
        return price;
    }

    @Override
    public String getDataAllowance()
    {
        return dataAllowance;
    }

    @Override
    public void setNextInternetType(Internet nextInternetType)
    {
        this.nextInternetType = nextInternetType;
    }

    public static class Builder
    {
        private String ipAddress, ISP, planName;
        String price, dataAllowance;

        public Builder ipAddress(String value)
        {
            this.ipAddress = value;
            return this;
        }

        public Builder ISP(String value)
        {
            this.ISP = value;
            return this;
        }

        public Builder planName(String value)
        {
            this.planName = value;
            return this;
        }

        public Builder price(String value)
        {
            this.price = value;
            return this;
        }

        public Builder dataAllowance(String value)
        {
            this.dataAllowance = value;
            return this;
        }

        public Builder copy(ADSL value)
        {
            this.ipAddress = value.ipAddress;
            this.ISP = value.ISP;
            this.planName = value.planName;
            this.price = value.price;
            this.dataAllowance = value.dataAllowance;

            return this;
        }

        public ADSL build()
        {
            return new ADSL(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        ADSL adsl = (ADSL) o;

        return ipAddress != null ? ipAddress.equals(adsl.ipAddress) : adsl.ipAddress == null;
    }

    @Override
    public int hashCode()
    {
        return ipAddress != null ? ipAddress.hashCode() : 0;
    }
}
