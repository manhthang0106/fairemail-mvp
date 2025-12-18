package eu.faircode.email;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoMessage {
    @Query("SELECT * FROM message WHERE folder = :folder ORDER BY received DESC")
    DataSource.Factory<Integer, EntityMessage> pagedMessages(long folder);

    @Query("SELECT * FROM message WHERE folder = :folder AND ui_seen = 0")
    LiveData<List<EntityMessage>> liveUnseenMessages(long folder);

    @Query("SELECT * FROM message WHERE id = :id")
    EntityMessage getMessage(long id);

    @Query("SELECT * FROM message WHERE msgid = :msgid")
    EntityMessage getMessageByMsgId(String msgid);

    @Query("SELECT COUNT(*) FROM message WHERE ui_seen = 0")
    LiveData<Integer> liveUnseenCount();

    @Insert
    long insertMessage(EntityMessage message);

    @Update
    void updateMessage(EntityMessage message);

    @Query("DELETE FROM message WHERE id = :id")
    void deleteMessage(long id);

    @Query("SELECT * FROM message WHERE subject LIKE :query OR preview LIKE :query")
    List<EntityMessage> searchMessages(String query);
}
