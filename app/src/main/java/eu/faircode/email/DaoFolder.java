package eu.faircode.email;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoFolder {
    @Query("SELECT * FROM folder WHERE account = :account ORDER BY name")
    LiveData<List<EntityFolder>> liveFolders(long account);

    @Query("SELECT * FROM folder WHERE id = :id")
    EntityFolder getFolder(long id);

    @Query("SELECT * FROM folder WHERE account = :account AND type = :type")
    EntityFolder getFolderByType(long account, String type);

    @Insert
    long insertFolder(EntityFolder folder);

    @Update
    void updateFolder(EntityFolder folder);

    @Query("DELETE FROM folder WHERE id = :id")
    void deleteFolder(long id);
}
