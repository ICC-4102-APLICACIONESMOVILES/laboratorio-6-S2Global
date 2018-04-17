package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Form {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int pollId;
    private String userName;
    private String date;
    private String category;
    private String comment;

    public Form() {
    }

    public int getPollId() { return pollId; }
    public void setPollId(int pollId) { this.pollId = pollId; }
    public String getUserName() { return userName; }
    public void setUserName (String userName) { this.userName = userName; }
    public String getDate() { return date; }
    public void setDate (String date) { this.date = date; }
    public String getCategory() { return category; }
    public void setCategory (String category) { this.category = category; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
