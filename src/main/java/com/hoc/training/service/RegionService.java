package com.hoc.training.service;

import java.util.UUID;

import com.hoc.training.entity.Region;
import com.hoc.training.repository.RegionRepository;

public class RegionService {

    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region get(String id) {
        Region region = regionRepository.selectById(id);
        if(region != null) {
            return region;
        } else {
            throw new IllegalArgumentException("not found");
        }
    }

    public Region addNew(String name) {
        Region data = new Region();
        data.setId(UUID.randomUUID().toString());
        data.setName(name);
        
        regionRepository.insert(data);
        
        return data;
    }
}
