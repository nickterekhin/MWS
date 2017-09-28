package db.impl;

import db.PermissionsJDBC;
import db.jdbcadapter.JDBCAdapter;
import domain.Permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 21.03.2015.
 */
public class PermissionsJDBCImpl extends JDBCAdapter implements PermissionsJDBC {
    private Connection connection = null;

    @Override
    public boolean createPermission(long groupId) {
        return false;
    }

    @Override
    public boolean addpermission(long groupId, String permision) {
        return false;
    }

    @Override
    public boolean updatePermission(long groupId, String permissionList) {
        return false;
    }

    @Override
    public boolean update(long groupId, List<String> permission) {
        return false;
    }

    @Override
    public boolean resetPermission(long groupId, String groupName) {
        return false;
    }

    @Override
    public boolean deletePermission(long groupId) {
        return false;
    }

    @Override
    public Permission getPermission(long groupId) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM permissions WHERE ugroup=?");
            preparedStatement.setLong(1,groupId);
            ResultSet res = preparedStatement.executeQuery();
            Permission perms=null;
            if(res.next())
            {
                perms = new Permission();
                this.initPermission(perms,res);

            }
            return perms;
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
    public Set<String> getPermissionsListOfString(long groupId) {
            Set<String> perms=null;
                Permission permObj = getPermission(groupId);
        if(permObj!=null) {
            perms = new HashSet<>();
            String[] permissions = permObj.getPermString().split("\\|");
            for(int i=0; i<permissions.length; i++)
            {
                perms.add(permissions[i]);
            }
        }
        return perms;
    }

    private void initPermission(Permission p, ResultSet r) throws SQLException {
            p.setUgroup(r.getLong("ugroup"));
            p.setPermString(r.getString("perms"));
    }
}
