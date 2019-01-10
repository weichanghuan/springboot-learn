package generator;

import generator.SysUser;

public interface SysUserDao {
    int deleteByPrimaryKey(String userName);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userName);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}