package eu.faircode.email;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
    tableName = "folder",
    foreignKeys = {
        @ForeignKey(entity = EntityAccount.class, parentColumns = "id", childColumns = "account", onDelete = CASCADE)
    },
    indices = {@Index(value = {"account", "name"}, unique = true)}
)
public class EntityFolder {
    static final String INBOX = "Inbox";
    static final String SENT = "Sent";
    static final String DRAFTS = "Drafts";
    static final String TRASH = "Trash";
    static final String ARCHIVE = "Archive";

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long account;
    public String name;
    public String type;
    public Integer level;
    public Boolean synchronize;
    public Integer sync_days;
    public Integer total;
    public Integer unseen;
}
