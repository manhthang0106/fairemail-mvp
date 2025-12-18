package eu.faircode.email;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAccount {
    @Query("SELECT * FROM account ORDER BY primary DESC, name")
    LiveData<List<EntityAccount>> liveAccounts();

    @Query("SELECT * FROM account WHERE id = :id")
    EntityAccount getAccount(long id);

    @Query("SELECT * FROM account WHERE synchronize = 1")
    List<EntityAccount> getSynchronizingAccounts();

    @Insert
    long insertAccount(EntityAccount account);

    @Update
    void updateAccount(EntityAccount account);

    @Query("DELETE FROM account WHERE id = :id")
    void deleteAccount(long id);
}
