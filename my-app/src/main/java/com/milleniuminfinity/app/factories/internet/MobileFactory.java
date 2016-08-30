package com.milleniuminfinity.app.factories.internet;

import com.milleniuminfinity.app.domain.internet.Internet;
import com.milleniuminfinity.app.domain.internet.Mobile;

import java.io.Serializable;

/**
 * Created by 208023429 on 5/13/2016.
 */
public class MobileFactory implements Serializable {

    public static Internet getMobile(String ISP, String planName, String price, String dataAllowance) {
        Internet mobile = new Mobile.Builder()
                .ISP(ISP)
                .planName(planName)
                .price(price)
                .dataAllowance(dataAllowance)
                .build();

        return mobile;
    }
}
