package cn.edu.jmu.dao;

import cn.edu.jmu.pojo.NetcaseUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NeteaseUnitDao {
    List<NetcaseUnit> getNetcaseUnitListByLeafId(String unitId);
}
