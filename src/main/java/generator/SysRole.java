package generator;

import java.io.Serializable;

/**
 * sys_role
 * @author 
 */
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色名称
     */
    private String roleName;

    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}