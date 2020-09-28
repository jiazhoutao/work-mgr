package cn.edu.jmu.dao;

import cn.edu.jmu.pojo.NeteaseAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NeteaseAccountDao {
    NeteaseAccount getNetcaseAccount(String id);
}
