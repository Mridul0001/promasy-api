package com.promasy.api.cbl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CblServiceImpl implements CblService{
    @Autowired CblRepository cblRepository;
    @Override
    public List<CblModel> getAllProjects() {
        return cblRepository.findAll();
    }
}
