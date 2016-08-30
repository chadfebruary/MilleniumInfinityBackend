package com.milleniuminfinity.app.factories.internet;

import com.milleniuminfinity.app.domain.internet.ADSL;
import com.milleniuminfinity.app.domain.internet.Internet;

import java.io.Serializable;

/**
 * Created by 208023429 on 5/13/2016.
 */
public class ADSLFactory implements Serializable {

    public static Internet getADSL(String ISP, String planName, String price, String dataAllowance) {
        Internet adsl = new ADSL.Builder()
                .ISP(ISP)
                .planName(planName)
                .price(price)
                .dataAllowance(dataAllowance)
                .build();

        return adsl;
    }
}
