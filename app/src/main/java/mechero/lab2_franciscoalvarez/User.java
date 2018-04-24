package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int userId;
    private String email;
    private String password;

    public User(){}

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail (String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String pass) { this.password = pass; }
}
