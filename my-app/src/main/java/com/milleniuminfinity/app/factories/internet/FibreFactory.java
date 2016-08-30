package com.milleniuminfinity.app.factories.internet;

import com.milleniuminfinity.app.domain.internet.Fibre;
import com.milleniuminfinity.app.domain.internet.Internet;

import java.io.Serializable;

/**
 * Created by 208023429 on 5/13/2016.
 */
public class FibreFactory implements Serializable {

    public static Internet getFibre(String ISP, String planName, String price, String dataAllowance) {
        Internet fibre = new Fibre.Builder()
                .ISP(ISP)
                .planName(planName)
                .price(price)
                .dataAllowance(dataAllowance)
                .build();

        return fibre;
    }
}
