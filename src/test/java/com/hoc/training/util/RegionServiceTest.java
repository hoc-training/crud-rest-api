package com.hoc.training.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hoc.training.entity.Region;
import com.hoc.training.repository.RegionRepository;
import com.hoc.training.service.RegionService;

@Extensions({
    @ExtendWith(MockitoExtension.class)
})
public class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    private RegionService regionService;

    @BeforeEach
    void setUp() {
        regionService = new RegionService(regionRepository);
    }

    @Test
    void notFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
          regionService.get("not found");
        });
    }

    @Test
    void getSuccess() {
        Region data = new Region();
        data.setId("test_id");
        data.setName("test_name");

        Mockito.when(regionRepository.selectById("test_id")).thenReturn(data);
        var region = regionService.get("test_id");

        Assertions.assertNotNull(region);
        Assertions.assertEquals("test_id", data.getId());
        Assertions.assertEquals("test_name", data.getName());
    }

    @Test
    void testAddNewSuccess() {
        var region = regionService.addNew("Region 1");

        Assertions.assertNotNull(region);
        Assertions.assertEquals("Region 1", region.getName());
        Assertions.assertNotNull(region.getId());
        
        Region regionData = new Region();
        regionData.setId(region.getId());
        regionData.setName(region.getName());

        Mockito.verify(regionRepository, Mockito.times(1)).insert(regionData);
    }
}
