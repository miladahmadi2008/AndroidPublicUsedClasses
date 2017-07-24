package app.milad.androidpublicusedclasses.Dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import app.milad.androidpublicusedclasses.Manager.ReadCallLog;
import app.milad.androidpublicusedclasses.Manager.ReadContact;
import app.milad.androidpublicusedclasses.Manager.ReadSms;
import app.milad.androidpublicusedclasses.Objects.CallLogModel;
import app.milad.androidpublicusedclasses.Objects.ContactModel;
import app.milad.androidpublicusedclasses.Objects.SmsModel;


/**
 * Created by milad on 7/23/2017.
 */


public class Loading extends AsyncTask<String, String, String>
{
    Activity activity;
    ProgressDialog progDailog = null;
    String title = null;
    String message = null;

    public Loading(Activity activity_,String title_,String message_)
    {
        this.activity = activity_;
        title= title_;
        message= message_;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progDailog = new ProgressDialog(activity);
        progDailog.setTitle(title);
        progDailog.setMessage(message);
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();
    }

    @Override
    protected String doInBackground(String... aurl)
    {
        List<ContactModel> contactList= ReadContact.getContacts(activity);
        List<SmsModel>smsList= ReadSms.getAllSms(activity);
        List<CallLogModel>callLogList= ReadCallLog.getCallDetails(activity,contactList);

        return null;
    }

    @Override
    protected void onPostExecute(String unused)
    {
        super.onPostExecute(unused);
        progDailog.dismiss();
    }
}


