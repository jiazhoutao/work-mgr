package cn.edu.jmu.service.impl;

import cn.edu.jmu.dao.MeetingDao;
import cn.edu.jmu.dao.TreeNodeDao;
import cn.edu.jmu.pojo.Meeting;
import cn.edu.jmu.pojo.TreeNode;
import cn.edu.jmu.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private TreeNodeDao treeNodeDao;

    @Autowired
    private MeetingDao meetingDao;

    @Override
    public List<TreeNode> list() {
        List<TreeNode> list= treeNodeDao.findAll();
        List<TreeNode> list2=new LinkedList<>();
        for(int i=0;i<list.size();i++){
            TreeNode treeNode=list.get(i);
            if(null==treeNode.getPid()){
                loadChildren(treeNode,list);
                list2.add(treeNode);
            }
        }
        return list2;
    }

    @Override
    public List<Meeting> getAllMeeting() {
        return meetingDao.getAllMeeting();
    }

    @Override
    public Meeting getMeeting(String id) {
        return meetingDao.getMeeting(id);
    }

    @Override
    public Meeting addMeeting(Meeting meeting) {
        try{
            meetingDao.addMeeting(meeting);
            return meeting;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void loadChildren(TreeNode treeNode,List<TreeNode> list){
        List<TreeNode> children= treeNode.getChildren();
        if(children==null){
            children=new LinkedList<>();
        }
        for(int i=0;i<list.size();i++){
            TreeNode child=list.get(i);
            String pid=child.getPid();
            if(pid!=null&&pid.equals(treeNode.getKey())){
                list.remove(child);
                i--;
                children.add(child);
                Collections.sort(children);
                loadChildren(child,list);
            }
        }
        treeNode.setChildren(children);
        if(children.size()==0){
            treeNode.setIsLeaf(true);
        }else{
            treeNode.setIsLeaf(false);
        }
    }
}
