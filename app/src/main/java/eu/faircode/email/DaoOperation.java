package eu.faircode.email;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoOperation {
    @Query("SELECT * FROM operation ORDER BY created")
    List<EntityOperation> getOperations();

    @Query("SELECT * FROM operation WHERE folder = :folder")
    LiveData<List<EntityOperation>> liveOperations(long folder);

    @Insert
    long insertOperation(EntityOperation operation);

    @Update
    void updateOperation(EntityOperation operation);

    @Query("DELETE FROM operation WHERE id = :id")
    void deleteOperation(long id);
}
