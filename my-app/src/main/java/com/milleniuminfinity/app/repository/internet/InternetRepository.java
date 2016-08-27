package com.milleniuminfinity.app.repository.internet;

import com.milleniuminfinity.app.domain.internet.Internet;
import com.milleniuminfinity.app.repository.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chad on 4/21/2016.
 */
public interface InternetRepository extends CrudRepository<Internet, String> {
}
