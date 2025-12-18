package eu.faircode.email;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class EntityAccount {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String name;
    public String email;
    public String host;
    public Integer port;
    public Boolean starttls;
    public String user;
    public String password;
    public String auth_type; // OAuth2, password, etc.
    public Boolean synchronize;
    public Boolean primary;
    public Long created;
    public Long last_connected;
    public String error;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityAccount) {
            EntityAccount other = (EntityAccount) obj;
            return this.id.equals(other.id);
        }
        return false;
    }
}
