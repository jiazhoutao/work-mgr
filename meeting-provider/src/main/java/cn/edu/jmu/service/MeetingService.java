package cn.edu.jmu.service;

import cn.edu.jmu.pojo.Meeting;
import cn.edu.jmu.pojo.TreeNode;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface MeetingService {
    List<TreeNode> list();

    List<Meeting> getAllMeeting();

    Meeting getMeeting(String id);

    Meeting addMeeting(Meeting meeting);
}
