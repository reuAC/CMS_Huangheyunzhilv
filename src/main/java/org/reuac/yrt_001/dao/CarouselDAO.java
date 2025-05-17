package org.reuac.yrt_001.dao;

import org.reuac.yrt_001.model.CarouselSlide;

import java.util.List;

public interface CarouselDAO {
    List<CarouselSlide> findByPageCodeAndActive(String pageCode, boolean isActive);

}