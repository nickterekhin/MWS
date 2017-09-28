package session;

/**
 * Created by Nick on 06.03.2015.
 */
public class SUser {
    private static SUser sUser = new SUser();
    private boolean isLogin =false;

    public static SUser getInstancee()
    {
        return sUser;
    }

    private SUser()
    {

    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
