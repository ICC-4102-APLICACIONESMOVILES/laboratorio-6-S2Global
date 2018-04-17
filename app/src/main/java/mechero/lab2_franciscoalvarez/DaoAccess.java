package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySinglePoll (Form polls);
    @Insert
    void insertMultiplePolls (List<Form> formList);
    @Query("SELECT * FROM Form WHERE pollId = :pollId")
    Form fetchOnePollbyPollId (int pollId);
    @Update
    void updatePoll (Form movies);
    @Delete
    void deletePoll (Form movies);
    @Query("DELETE FROM Form")
    void nukeForms();

}





