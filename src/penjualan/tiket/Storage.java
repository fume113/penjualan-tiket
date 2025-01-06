package penjualan.tiket;

public class Storage {
    private String nama, role, username;
    private int user_id;
    private static Storage instance = null;

    public String getNama() {
        return nama;
    }

    public String getRole() {
        return role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}
