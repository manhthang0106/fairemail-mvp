package eu.faircode.email;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
    tableName = "message",
    foreignKeys = {
        @ForeignKey(entity = EntityAccount.class, parentColumns = "id", childColumns = "account", onDelete = CASCADE),
        @ForeignKey(entity = EntityFolder.class, parentColumns = "id", childColumns = "folder", onDelete = CASCADE),
        @ForeignKey(entity = EntityIdentity.class, parentColumns = "id", childColumns = "identity", onDelete = CASCADE)
    },
    indices = {
        @Index(value = {"account"}),
        @Index(value = {"folder"}),
        @Index(value = {"identity"}),
        @Index(value = {"msgid"}),
        @Index(value = {"uid"})
    }
)
public class EntityMessage {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long account;
    public Long folder;
    public Long identity;
    public String msgid;
    public Long uid;
    public String references;
    public String inreplyto;
    public String thread;
    public Date received;
    public Date sent;
    public String subject;
    public String preview;
    public Boolean seen;
    public Boolean ui_seen;
    public Boolean flagged;
    public Boolean ui_flagged;
    public Boolean ui_hide;
    public Boolean ui_found;
    public Boolean ui_browsed;
    public Integer attachments;
    public Boolean encrypted;
    public String error;
}
