package db;

import domain.Group;
import domain.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 04.03.2015.
 */
public interface GroupJDBC {
    public boolean createGroup(Group g);
    public List<Group> getAll();
    public Group getByGroupId(long groupId);
    public Permission getPermission(long groupId);
    public Set<String> getPermissionList(long groupId);
    public boolean updateGroupName(long groupId);
    public boolean deleteGroup(long groupId);


}
