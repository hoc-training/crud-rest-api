package com.hoc.training.repository;

import com.hoc.training.entity.Region;

public interface RegionRepository {

    Region selectById(String id);

    void insert(Region region);
}
