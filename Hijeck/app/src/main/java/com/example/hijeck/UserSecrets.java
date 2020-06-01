package com.example.hijeck;

public class UserSecrets {
    private String applicationame;
    private String Username;
    private String password;

    public UserSecrets(String applicationame,String Username,String password)
    {
        this.applicationame=applicationame;
        this.Username=Username;
        this.password=password;
    }

    public String getApplicationame() {
        return applicationame;
    }

    public void setApplicationame(String applicationame) {
        this.applicationame = applicationame;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
