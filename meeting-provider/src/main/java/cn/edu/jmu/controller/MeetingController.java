package cn.edu.jmu.controller;

import cn.edu.jmu.pojo.Meeting;
import cn.edu.jmu.pojo.TreeNode;
import cn.edu.jmu.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @RequestMapping(value="/meeting-mgr/list",method = RequestMethod.GET)
    public List<TreeNode> list() {
       return meetingService.list();
    }

    @RequestMapping(value="/meeting/{id}",method = RequestMethod.GET)
    public Meeting meeting(@PathVariable("id") String id) {
        return meetingService.getMeeting(id);
    }


    @RequestMapping(value="/meeting",method = RequestMethod.GET)
    public List<Meeting> getAllMeeting() {
        return meetingService.getAllMeeting();
    }


    @RequestMapping(value = "/meeting/add", method = RequestMethod.POST)
    public Meeting add(Meeting meeting, HttpServletRequest request)
    {
        Enumeration<String> parameterNames=request.getParameterNames();
        return meetingService.addMeeting(meeting);
    }

    @RequestMapping(value = "/meeting", method = RequestMethod.PUT)
    public Meeting update(Meeting meeting)
    {
        return meetingService.addMeeting(meeting);
    }

    @ModelAttribute
    private void findById(@PathVariable(value = "id", required = false) String id, Map<String, Object> map) {
        if (id != null && !"".equals(id)) {
            Meeting meeting = meetingService.getMeeting(id);
            if (meeting != null) {
                map.put("meeting", meeting);
            }
        }
    }
}
