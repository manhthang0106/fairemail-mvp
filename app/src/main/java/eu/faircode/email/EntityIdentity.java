package eu.faircode.email;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
    tableName = "identity",
    foreignKeys = {
        @ForeignKey(entity = EntityAccount.class, parentColumns = "id", childColumns = "account", onDelete = CASCADE)
    },
    indices = {@Index(value = {"account"})}
)
public class EntityIdentity {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long account;
    public String name;
    public String email;
    public String host;
    public Integer port;
    public Boolean starttls;
    public String user;
    public String password;
    public String auth_type;
    public Boolean synchronize;
    public Boolean primary;
    public String signature;
}
