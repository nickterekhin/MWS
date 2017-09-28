package db;

import domain.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 03.03.2015.
 */
public interface PermissionsJDBC {
   public boolean createPermission(long groupId);
    public boolean addpermission(long groupId, String permision);
    public boolean updatePermission(long groupId, String permissionList);
    public boolean update(long groupId,List<String> permission);
    public boolean resetPermission(long groupId,String groupName);
    public boolean deletePermission(long groupId);
    public Permission getPermission(long groupId);
    public Set<String> getPermissionsListOfString(long groupId);
}
