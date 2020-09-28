package cn.edu.jmu.dao;

import cn.edu.jmu.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingDao {
    List<Meeting> getAllMeeting();
    Meeting getMeeting(String id);
    void addMeeting(Meeting meeting);
}
