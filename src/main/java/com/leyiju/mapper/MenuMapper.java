package com.leyiju.mapper;

import com.leyiju.domain.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> getMenusById(Long resourceId);
}
