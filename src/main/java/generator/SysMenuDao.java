package generator;

import generator.SysMenu;

public interface SysMenuDao {
    int insert(SysMenu record);

    int insertSelective(SysMenu record);
}