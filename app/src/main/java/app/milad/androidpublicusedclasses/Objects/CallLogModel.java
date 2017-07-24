package app.milad.androidpublicusedclasses.Objects;

/**
 * Created by milad on 7/23/2017.
 */

public class CallLogModel
{
    private String _id;
    private String _name;
    private String _number;
    private String _type; //"0" for have not read sms and "1" for have read sms
    private String _callDuration;
    private String _callDayTime;


    public String get_id()
    {
        return _id;
    }

    public void set_id(String _id)
    {
        this._id = _id;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public String get_number()
    {
        return _number;
    }

    public void set_number(String _number)
    {
        this._number = _number;
    }

    public String get_type()
    {
        return _type;
    }

    public void set_type(String _type)
    {
        this._type = _type;
    }

    public String get_callDuration()
    {
        return _callDuration;
    }

    public void set_callDuration(String _callDuration)
    {
        this._callDuration = _callDuration;
    }

    public String get_callDayTime()
    {
        return _callDayTime;
    }

    public void set_callDayTime(String _callDayTime)
    {
        this._callDayTime = _callDayTime;
    }



}
