package com.promasy.api.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceServiceImpl implements SequenceService{
    @Autowired SequenceRepository sequenceRepository;
    @Override
    public int getSequence(String sequenceName) {
        SequenceModel counter = sequenceRepository.findBySequenceName(sequenceName);
        try{
         counter.setValue(counter.getValue()+1);
         sequenceRepository.save(counter);
         return counter.getValue();
        }catch (NullPointerException n){
            n.printStackTrace();
        }
        return 0;
    }
}
