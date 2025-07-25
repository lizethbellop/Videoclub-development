package org.thevault.videoclub_desktop.session;

import org.thevault.videoclub_desktop.model.UserDTO;

public class SessionManager {
    private static SessionManager instance;
    private UserDTO loggedUser;

    public SessionManager() {
    }

    public static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public void setLoggedUser(UserDTO loggedUser) {
        this.loggedUser = loggedUser;
    }

    public UserDTO getLoggedUser(){
        return loggedUser;
    }
}
