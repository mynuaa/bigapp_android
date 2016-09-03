package cn.edu.nuaa.my.base.json.mypm;

import com.youzu.android.framework.json.annotation.JSONField;

import java.io.Serializable;

public class Notify implements Serializable, Comparable<Notify> {
    private static final long serialVersionUID = 6971716995427850348L;
    private String author;
    private String authorid;
    private String from_id;
    private String from_idtype;
    private String from_num;
    private String id;
    private String isnew;
    private String note;
    private String type;
    private String uid;
    private String msgtoidAvatar;
    private String msgfromidAvatar;


    private String status;

    private String localID;

    /**
     * 标记消息
     */
    private String tagMessage;


    public Notify() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNew() {
        return this.isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }
    @JSONField(name = "msgtoid_avatar")
    public void setMsgtoidAvatar(String msgtoidAvatar) {
        this.msgtoidAvatar = msgtoidAvatar;
    }

    public String getMsgfromidAvatar() {
        return msgfromidAvatar;
    }

    @JSONField(name = "msgfromid_avatar")
    public void setMsgfromidAvatar(String msgfromidAvatar) {
        this.msgfromidAvatar = msgfromidAvatar;
    }

    @Override
    public int compareTo(Notify another) {
        return 0;
    }
}
