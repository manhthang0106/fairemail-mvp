package eu.faircode.email;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class ServiceSynchronize extends Service {
    private static final int NOTIFICATION_ID = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, getNotification());
        synchronize();
        return START_STICKY;
    }

    private Notification getNotification() {
        return new NotificationCompat.Builder(this, ApplicationEx.CHANNEL_SERVICE)
            .setContentTitle("FairEmail")
            .setContentText(getString(R.string.msg_syncing))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build();
    }

    private void synchronize() {
        new Thread(() -> {
            try {
                DB db = DB.getInstance(this);
                List<EntityAccount> accounts = db.account().getSynchronizingAccounts();
                
                for (EntityAccount account : accounts) {
                    synchronizeAccount(account);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void synchronizeAccount(EntityAccount account) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            
            Session session = Session.getInstance(props);
            Store store = session.getStore("imaps");
            store.connect(account.host, account.user, account.password);
            
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            
            Message[] messages = inbox.getMessages();
            DB db = DB.getInstance(this);
            
            for (Message msg : messages) {
                EntityMessage message = new EntityMessage();
                message.account = account.id;
                message.subject = msg.getSubject();
                message.received = msg.getReceivedDate();
                message.sent = msg.getSentDate();
                message.seen = false;
                message.ui_seen = false;
                
                db.message().insertMessage(message);
            }
            
            inbox.close(false);
            store.close();
            
            account.last_connected = System.currentTimeMillis();
            db.account().updateAccount(account);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
