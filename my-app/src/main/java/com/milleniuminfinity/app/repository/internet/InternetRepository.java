package com.milleniuminfinity.app.repository.internet;

import com.milleniuminfinity.app.domain.internet.Internet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Chad on 4/21/2016.
 */
@Repository
public interface InternetRepository extends CrudRepository<Internet, String> {
}
