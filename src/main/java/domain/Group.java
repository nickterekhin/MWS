package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 03.03.2015.
 */
public class Group {
    private long ugroup;
    private String groupName;
    private Set<String> permission;
    private Permission perms;

    public Set<String> getPermission() {
        return permission;
    }

    public void setPermission(Set<String> permission) {
        this.permission = permission;
    }

    public Group() {
        this.permission = new HashSet<>();
    }

    public Group(long ugroup, String groupName, String permission) {
        this.ugroup = ugroup;
        this.groupName = groupName;
        this.permission = new HashSet<>();
        this.permission.add(permission);
    }

    public Group(long ugroup, String groupName, Set<String> permission) {
        this.ugroup = ugroup;
        this.groupName = groupName;
        this.permission = permission;

    }

    public long getUgroup() {
        return ugroup;
    }

    public void setUgroup(long ugroup) {
        this.ugroup = ugroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Permission getPerms() {
        return perms;
    }

    public void setPerms(Permission perms) {
        this.perms = perms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (ugroup != group.ugroup) return false;
        if (!groupName.equals(group.groupName)) return false;
        if (!permission.equals(group.permission)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ugroup ^ (ugroup >>> 32));
        result = 31 * result + groupName.hashCode();
        result = 31 * result + permission.hashCode();
        return result;
    }
}
