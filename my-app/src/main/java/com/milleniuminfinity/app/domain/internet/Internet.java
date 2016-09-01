package com.milleniuminfinity.app.domain.internet;
/* Write a description of class Internet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@javax.persistence.Entity
public class Internet implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ipAddress;
    private String ISP;
    private String planName;
    private String price;
    private String dataAllowance;
    private String type = "";

    private Internet(){}

    private Internet(Builder builder)
    {
        this.ipAddress = builder.ipAddress;
        this.ISP = builder.ISP;
        this.planName = builder.planName;
        this.type = builder.type;
        this.price = builder.price;
        this.dataAllowance = builder.dataAllowance;
    }

    public String getIPAddress()
    {
        return ipAddress;
    }

    public String getISP()
    {
        return ISP;
    }

    public String getPlanName()
    {
        return planName;
    }

    public String getPrice()
    {
        return price;
    }

    public String getDataAllowance()
    {
        return dataAllowance;
    }

    public String getType(){return type;}


    public static class Builder
    {
        private String ipAddress, ISP, planName;
        String price, dataAllowance, type;

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

        public Builder type(String value)
        {
            this.type = value;
            return this;
        }

        public Builder copy(Internet value)
        {
            this.ipAddress = value.ipAddress;
            this.ISP = value.ISP;
            this.type = value.type;
            this.planName = value.planName;
            this.price = value.price;
            this.dataAllowance = value.dataAllowance;

            return this;
        }

        public Internet build()
        {
            return new Internet(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Internet internet = (Internet) o;

        return ipAddress != null ? ipAddress.equals(internet.ipAddress) : internet.ipAddress == null;
    }

    @Override
    public int hashCode()
    {
        return ipAddress != null ? ipAddress.hashCode() : 0;
    }
}
