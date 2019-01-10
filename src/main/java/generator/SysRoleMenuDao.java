package generator;

import generator.SysRoleMenu;

public interface SysRoleMenuDao {
    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);
}