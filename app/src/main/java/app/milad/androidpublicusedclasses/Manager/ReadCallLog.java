package app.milad.androidpublicusedclasses.Manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.provider.CallLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.milad.androidpublicusedclasses.Objects.CallLogModel;
import app.milad.androidpublicusedclasses.Objects.ContactModel;


/**
 * Created by milad on 7/23/2017.
 */

public class ReadCallLog
{
    public static List<CallLogModel> getCallDetails(Activity activity, List<ContactModel> contactList) {
        StringBuffer stringBuffer = new StringBuffer();
        List<CallLogModel> list = new ArrayList<>();
        @SuppressLint("MissingPermission")
        Cursor cursor = activity.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int id = 0;
        String formattedDate = "";
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        if (cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                String idNumber = id+"";
                String phNumber = cursor.getString(number);
                String callType = cursor.getString(type);
                String callDate = cursor.getString(date);
                Date callDayTime = new Date();


                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.valueOf(callDate));
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                formattedDate = format.format(calendar.getTime());


                String callDuration = cursor.getString(duration);
                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode)
                {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }

                String nameContact=ReadContact.findNameFromNumber(contactList,phNumber);

                CallLogModel callLog = new CallLogModel();
                callLog.set_id(idNumber);
                callLog.set_number(phNumber);
                callLog.set_name(nameContact);
                callLog.set_type(dir);
                callLog.set_callDayTime(formattedDate);
                callLog.set_callDuration(callDuration);
                list.add(callLog);
                id++;
            }
        }
        cursor.close();
        return list;
    }
}
