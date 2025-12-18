package eu.faircode.email;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAttachment {
    @Query("SELECT * FROM attachment WHERE message = :message")
    LiveData<List<EntityAttachment>> liveAttachments(long message);

    @Query("SELECT * FROM attachment WHERE id = :id")
    EntityAttachment getAttachment(long id);

    @Insert
    long insertAttachment(EntityAttachment attachment);

    @Update
    void updateAttachment(EntityAttachment attachment);

    @Query("DELETE FROM attachment WHERE id = :id")
    void deleteAttachment(long id);
}
