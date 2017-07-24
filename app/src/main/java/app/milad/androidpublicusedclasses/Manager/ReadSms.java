package app.milad.androidpublicusedclasses.Manager;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.milad.androidpublicusedclasses.Objects.SmsModel;


/**
 * Created by milad on 7/23/2017.
 */

public class ReadSms
{
    //after api 19 you can get sms like this
    public static void getAllSms(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
        {
            ContentResolver cr = context.getContentResolver();
            Cursor c = null;

            c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
            int totalSMS = 0;
            if (c != null)
            {
                totalSMS = c.getCount();
                if (c.moveToFirst())
                {
                    for (int j = 0; j < totalSMS; j++)
                    {
                        String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                        String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                        String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                        Date dateFormat = new Date(Long.valueOf(smsDate));
                        String type;
                        switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE))))
                        {
                            case Telephony.Sms.MESSAGE_TYPE_INBOX:
                                type = "inbox";
                                break;
                            case Telephony.Sms.MESSAGE_TYPE_SENT:
                                type = "sent";
                                break;
                            case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                                type = "outbox";
                                break;
                            default:
                                break;
                        }


                        c.moveToNext();
                    }
                }
            }
            else
            {
                Toast.makeText(context, "No message to show!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static List<SmsModel> getAllSms(Activity activity)
    {
        List<SmsModel> lstSms = new ArrayList<SmsModel>();
        SmsModel objSms = new SmsModel();
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr = activity.getContentResolver();

        Cursor cursor = cr.query(message, null, null, null, null);
        activity.startManagingCursor(cursor);
        int totalSMS = cursor.getCount();

        if (cursor.moveToFirst())
        {
            for (int i = 0; i < totalSMS; i++)
            {

                objSms = new SmsModel();
                String formattedDate = "";
                String date = cursor.getString(cursor.getColumnIndex("date"));
                Long timestamp = Long.parseLong(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = format.format(calendar.getTime());

                objSms.setId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
                objSms.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
                objSms.setMsg(cursor.getString(cursor.getColumnIndexOrThrow("body")));
                objSms.setReadState(cursor.getString(cursor.getColumnIndex("read")));
                objSms.setDate(formattedDate);
                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1"))
                {
                    objSms.setFolderName("inbox");
                }
                else if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("4"))
                {
                    objSms.setFolderName("outbox");
                }
                else
                {
                    objSms.setFolderName("sent");
                }
                lstSms.add(objSms);
                cursor.moveToNext();
            }
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        cursor.close();

        return lstSms;
    }
}
