package eu.faircode.email;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
    tableName = "operation",
    foreignKeys = {
        @ForeignKey(entity = EntityMessage.class, parentColumns = "id", childColumns = "message", onDelete = CASCADE),
        @ForeignKey(entity = EntityFolder.class, parentColumns = "id", childColumns = "folder", onDelete = CASCADE)
    },
    indices = {@Index(value = {"message"}), @Index(value = {"folder"})}
)
public class EntityOperation {
    static final String SYNC = "sync";
    static final String SEND = "send";
    static final String DELETE = "delete";
    static final String MOVE = "move";
    static final String SEEN = "seen";
    static final String FLAG = "flag";

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long folder;
    public Long message;
    public String name;
    public String args;
    public Long created;
    public String error;
}
