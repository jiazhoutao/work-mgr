package cn.edu.jmu.dao;

import cn.edu.jmu.pojo.TreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TreeNodeDao {
    public List<TreeNode> findAll();
}
