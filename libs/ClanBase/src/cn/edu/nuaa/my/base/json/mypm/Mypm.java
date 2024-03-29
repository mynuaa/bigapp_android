package cn.edu.nuaa.my.base.json.mypm;

import com.kit.utils.StringUtils;
import com.youzu.android.framework.json.annotation.JSONField;

import java.io.Serializable;

public class Mypm implements Serializable, Comparable<Mypm> {
    private static final long serialVersionUID = 6971716995427850348L;
    private String plid;
    private String isnew;
    private String pmnum;
    private String lastupdate;
    private String lastdateline;
    private String authorid;
    private String pmtype;
    private String subject;
    private String members;
    private String dateline;
    private String touid;
    private String pmid;
    private String lastauthorid;
    private String lastauthor;
    private String lastsummary;
    private String msgfromid;
    private String msgfrom;
    private String message;
    private String msgtoid;
    private String tousername;
    private String msgtoidAvatar;
    private String msgfromidAvatar;


    private String status;

    private String localID;

    /**
     * 标记消息
     */
    private String tagMessage;


    public Mypm() {
    }

//    public Mypm(Mypm mypm) {
//        plid = mypm.getPlid();
//        isnew = mypm.getIsnew();
//        pmnum = mypm.getPmnum();
//        lastupdate = mypm.getLastupdate();
//        lastdateline = mypm.getLastdateline();
//        authorid = mypm.getAuthorid();
//        pmtype = mypm.getPmtype();
//        subject = mypm.getSubject();
//        members = mypm.getMembers();
//        dateline = mypm.getDateline();
//        touid = mypm.getTouid();
//        pmid = mypm.getPmid();
//        lastauthorid = mypm.getLastauthorid();
//        lastauthor = mypm.getLastauthor();
//        lastsummary = mypm.getLastsummary();
//        msgfromid = mypm.getMsgfromid();
//        msgfrom = mypm.getMsgfrom();
//        message = mypm.getMessage();
//        msgtoid = mypm.getMsgtoid();
//        tousername = mypm.getTousername();
//        msgtoidAvatar = mypm.getMsgtoidAvatar();
//        msgfromidAvatar = mypm.getMsgfromidAvatar();
//    }

    public String getPlid() {
        return plid;
    }

    public void setPlid(String plid) {
        this.plid = plid;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public String getPmnum() {
        return pmnum;
    }

    public void setPmnum(String pmnum) {
        this.pmnum = pmnum;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getLastdateline() {
        return lastdateline;
    }

    public void setLastdateline(String lastdateline) {
        this.lastdateline = lastdateline;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getPmtype() {
        return pmtype;
    }

    public void setPmtype(String pmtype) {
        this.pmtype = pmtype;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getTouid() {
        return touid;
    }

    public void setTouid(String touid) {
        this.touid = touid;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getLastauthorid() {
        return lastauthorid;
    }

    public void setLastauthorid(String lastauthorid) {
        this.lastauthorid = lastauthorid;
    }

    public String getLastauthor() {
        return lastauthor;
    }

    public void setLastauthor(String lastauthor) {
        this.lastauthor = lastauthor;
    }

    public String getLastsummary() {
        return lastsummary;
    }

    public void setLastsummary(String lastsummary) {
        this.lastsummary = lastsummary;
    }

    public String getMsgfromid() {
        return msgfromid;
    }

    public void setMsgfromid(String msgfromid) {
        this.msgfromid = msgfromid;
    }

    public String getMsgfrom() {
        return msgfrom;
    }

    public void setMsgfrom(String msgfrom) {
        this.msgfrom = msgfrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgtoid() {
        return msgtoid;
    }

    public void setMsgtoid(String msgtoid) {
        this.msgtoid = msgtoid;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public String getMsgtoidAvatar() {
        return msgtoidAvatar;
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

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTagMessage() {
        return tagMessage;
    }

    public void setTagMessage(String tagMessage) {
        this.tagMessage = tagMessage;
    }

    @Override
    public int compareTo(Mypm another) {
        return (int) (
                Long.valueOf(StringUtils.isEmptyOrNullOrNullStr(dateline) ? "0" : dateline)
                        - Long.valueOf(StringUtils.isEmptyOrNullOrNullStr(another.getDateline()) ? "0" : dateline)
        );
    }


}
