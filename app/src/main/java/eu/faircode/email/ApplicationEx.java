package eu.faircode.email;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class ApplicationEx extends Application {
    public static final String CHANNEL_NOTIFICATION = "notification";
    public static final String CHANNEL_SERVICE = "service";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = getSystemService(NotificationManager.class);
            
            NotificationChannel notification = new NotificationChannel(
                CHANNEL_NOTIFICATION,
                "Notifications",
                NotificationManager.IMPORTANCE_HIGH
            );
            notification.setDescription("Email notifications");
            nm.createNotificationChannel(notification);
            
            NotificationChannel service = new NotificationChannel(
                CHANNEL_SERVICE,
                "Service",
                NotificationManager.IMPORTANCE_LOW
            );
            service.setDescription("Service notifications");
            nm.createNotificationChannel(service);
        }
    }
}
