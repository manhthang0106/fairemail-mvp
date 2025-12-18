package eu.faircode.email;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
    entities = {
        EntityAccount.class,
        EntityIdentity.class,
        EntityFolder.class,
        EntityMessage.class,
        EntityAttachment.class,
        EntityOperation.class
    },
    version = 1
)
@TypeConverters({Converters.class})
public abstract class DB extends RoomDatabase {
    private static DB instance;

    public abstract DaoAccount account();
    public abstract DaoIdentity identity();
    public abstract DaoFolder folder();
    public abstract DaoMessage message();
    public abstract DaoAttachment attachment();
    public abstract DaoOperation operation();

    public static synchronized DB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                DB.class,
                "fairemail"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
