package cn.edu.jmu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)
public class Meeting implements Serializable {
    private String id;
    private String name;
    private String detail;
    private Date beginTime;
    private Date endTime;
    private int type;
    private String holder;
    private Meeting perMeeting;
    private List<Staff> staffList;
    private String creator;
    private int overFuncSetting;
    private int execTimeBeforeOver;
    private Date overTime;


    public Meeting(String id){
        super();
        this.id=id;
    }

    public Meeting(String id, String name, String detail, Date beginTime, Date endTime, String holder, Meeting perMeeting , int type) {
        this.id=id;
        this.name=name;
        this.detail=detail;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.holder=holder;
        this.perMeeting=perMeeting;
        this.type=type;
    }

}
