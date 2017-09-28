package db.impl;

import db.GroupJDBC;
import db.PermissionsJDBC;
import db.jdbcadapter.JDBCAdapter;
import domain.Group;
import domain.Permission;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 28.03.2015.
 */
public class GroupJDBCImpl extends JDBCAdapter implements GroupJDBC{
    private Connection connection;
    private PermissionsJDBC permissionsJDBC;

    public GroupJDBCImpl(PermissionsJDBC permissionsJDBC) {
        this.permissionsJDBC = permissionsJDBC;
    }

    @Override
    public boolean createGroup(Group g) {
        return false;
    }

    @Override
    public List<Group> getAll() {
        return null;
    }

    @Override
    public Group getByGroupId(long groupId) {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_group WHERE ugroup=?");
            preparedStatement.setLong(1,groupId);
            ResultSet res = preparedStatement.executeQuery();
            Group group=null;
            if(res.next())
            {
                group = new Group();
                this.initGroup(group, res);

            }
            return group;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;

    }

    @Override
    public Permission getPermission(long groupId) {
        return permissionsJDBC.getPermission(groupId);
    }

    @Override
    public Set<String> getPermissionList(long groupId) {
        return permissionsJDBC.getPermissionsListOfString(groupId);
    }

    @Override
    public boolean updateGroupName(long groupId) {
        return false;
    }

    @Override
    public boolean deleteGroup(long groupId) {
        return false;
    }

    private void initGroup(Group g, ResultSet r) throws SQLException {
        g.setUgroup(r.getLong("ugroup"));
        g.setGroupName(r.getString("groupName"));
        g.setPerms(permissionsJDBC.getPermission(g.getUgroup()));
        g.setPermission(permissionsJDBC.getPermissionsListOfString(g.getUgroup()));
    }
}
