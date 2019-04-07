package com.example.project.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.project.model.InfoMsg;
import com.example.project.notyficationmsg.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification() != null)
        {
            Log.i("Notification title :",remoteMessage.getNotification().getTitle());
            showNotyfication(remoteMessage.getNotification().getTitle());
            pareJson(remoteMessage.getNotification().getBody());
        }
        showNotyfication(remoteMessage.getData().get("body"), remoteMessage.getData().get("message"));

    }


    private void showNotyfication(String body) {
        showNotyfication(body,"google");
    }

    private void showNotyfication(String body, String title) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void pareJson(String body) {
        if (body != null) {
            try {
                JSONObject jsonObj = new JSONObject(body);

                String nameProfessor = jsonObj.getString("professor");
                String nameUniversity = jsonObj.getString("university");
                String laboratory = jsonObj.getString("laboratory");
                String salary = jsonObj.getString("salary");
                String major = jsonObj.getString("major");
                String languge = jsonObj.getString("languge");
                String programmingSkills = jsonObj.getString("programming skills");
                String dateline = jsonObj.getString("Dateline");

                JSONObject contactObj = new JSONObject("contact");
                String phone = contactObj.getString("phone");
                String email = contactObj.getString("email");

                InfoMsg infoMsg = new InfoMsg();

                infoMsg.setNameProfessor(nameProfessor);
                infoMsg.setNameUniversity(nameUniversity);
                infoMsg.setLaboratory(laboratory);
                infoMsg.setSalary(salary);
                infoMsg.setMajor(major);
                infoMsg.setLanguge(languge);
                infoMsg.setProgrammingSkills(programmingSkills);
                infoMsg.setPhone(Integer.parseInt(phone));
                infoMsg.setEmail(email);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                try {

                    Date date = formatter.parse(dateline);
                    infoMsg.setDateline(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
