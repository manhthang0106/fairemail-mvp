package eu.faircode.email;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceSend extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        processSendOperations();
        return START_NOT_STICKY;
    }

    private void processSendOperations() {
        new Thread(() -> {
            try {
                DB db = DB.getInstance(this);
                List<EntityOperation> operations = db.operation().getOperations();
                
                for (EntityOperation operation : operations) {
                    if (EntityOperation.SEND.equals(operation.name)) {
                        sendMessage(operation);
                        db.operation().deleteOperation(operation.id);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stopSelf();
            }
        }).start();
    }

    private void sendMessage(EntityOperation operation) {
        try {
            DB db = DB.getInstance(this);
            EntityMessage message = db.message().getMessage(operation.message);
            
            // Get identity (SMTP settings)
            List<EntityAccount> accounts = db.account().getSynchronizingAccounts();
            if (accounts.isEmpty()) return;
            
            EntityAccount account = accounts.get(0);
            // TODO: Get proper identity
            
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.example.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            
            Session session = Session.getInstance(props);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(account.email));
            mimeMessage.setSubject(message.subject);
            mimeMessage.setText(message.preview);
            
            Transport.send(mimeMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
