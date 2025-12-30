package siakad;

public abstract class User implements KelolaUser {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    public abstract void tampilMenu();
}
