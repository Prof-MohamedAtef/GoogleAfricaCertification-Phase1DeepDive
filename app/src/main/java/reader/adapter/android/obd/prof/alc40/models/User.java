package reader.adapter.android.obd.prof.alc40.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prof-Mohamed Atef on 19/06/2019.
 */

public class User implements Parcelable{

    private String PROFILE_IMAGE;
    private String NAME;
    private String GENDER;
    private String INTERESTED_IN;
    private String STATUS;

    public User() {
    }

    public User(String PROFILE_IMAGE, String NAME, String GENDER, String INTERESTED_IN, String STATUS) {
        this.PROFILE_IMAGE = PROFILE_IMAGE;
        this.NAME = NAME;
        this.GENDER = GENDER;
        this.INTERESTED_IN = INTERESTED_IN;
        this.STATUS = STATUS;
    }


    protected User(Parcel in) {
        PROFILE_IMAGE = in.readString();
        NAME = in.readString();
        GENDER = in.readString();
        INTERESTED_IN = in.readString();
        STATUS = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getPROFILE_IMAGE() {
        return PROFILE_IMAGE;
    }

    public void setPROFILE_IMAGE(String PROFILE_IMAGE) {
        this.PROFILE_IMAGE = PROFILE_IMAGE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getINTERESTED_IN() {
        return INTERESTED_IN;
    }

    public void setINTERESTED_IN(String INTERESTED_IN) {
        this.INTERESTED_IN = INTERESTED_IN;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PROFILE_IMAGE);
        dest.writeString(NAME);
        dest.writeString(GENDER);
        dest.writeString(INTERESTED_IN);
        dest.writeString(STATUS);
    }
}
