package eu.faircode.email;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
    tableName = "attachment",
    foreignKeys = {
        @ForeignKey(entity = EntityMessage.class, parentColumns = "id", childColumns = "message", onDelete = CASCADE)
    },
    indices = {@Index(value = {"message"})}
)
public class EntityAttachment {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long message;
    public Integer sequence;
    public String name;
    public String type;
    public String disposition;
    public String cid;
    public Long size;
    public Integer progress;
    public Boolean available;
    public String error;
}
