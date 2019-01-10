package generator;

import java.io.Serializable;

/**
 * sys_role_menu
 * @author 
 */
public class SysRoleMenu implements Serializable {
    private String roleMenuId;

    private String roleId;

    private String menuId;

    private static final long serialVersionUID = 1L;

    public String getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(String roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}