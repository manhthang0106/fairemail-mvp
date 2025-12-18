package eu.faircode.email;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoIdentity {
    @Query("SELECT * FROM identity WHERE account = :account")
    LiveData<List<EntityIdentity>> liveIdentities(long account);

    @Query("SELECT * FROM identity WHERE id = :id")
    EntityIdentity getIdentity(long id);

    @Insert
    long insertIdentity(EntityIdentity identity);

    @Update
    void updateIdentity(EntityIdentity identity);

    @Query("DELETE FROM identity WHERE id = :id")
    void deleteIdentity(long id);
}
