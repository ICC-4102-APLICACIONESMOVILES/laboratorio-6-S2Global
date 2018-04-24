package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Question {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int questionId;
    private String text;

    public Question() {}

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    @NonNull
    public int getQuestionId() { return questionId; }
    public void setQuestionId(@NonNull int questionId) { this.questionId = questionId; }
}
