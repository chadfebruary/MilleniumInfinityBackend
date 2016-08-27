package com.milleniuminfinity.app.services.internet.Impl;


import com.milleniuminfinity.app.domain.internet.Internet;
import com.milleniuminfinity.app.repository.internet.InternetRepository;
import com.milleniuminfinity.app.services.internet.InternetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chad on 5/8/2016.
 */
@Service
public class InternetServiceImpl implements InternetService {

    @Autowired
    InternetRepository internetRepository;

    @Override
    public Internet create(Internet internet)
    {
        return internetRepository.save(internet);
    }

    @Override
    public Internet readById(String ipAddress)
    {
        return internetRepository.findOne(ipAddress);
    }

    @Override
    public Set<Internet> readAll()
    {
        Set<Internet> result = new HashSet<>();
        while(internetRepository.findAll().iterator().hasNext())
        {
            result.add(internetRepository.findAll().iterator().next());
        }
        return result;
    }

    @Override
    public Internet update(Internet internet)
    {
        return internetRepository.save(internet);
    }

    @Override
    public void delete(Internet internet)
    {
        internetRepository.delete(internet);
    }
}
