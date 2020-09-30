package cn.edu.jmu.dao;

import cn.edu.jmu.pojo.NetcaseUnit;
import cn.edu.jmu.pojo.NetcaseUnitLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NeteaseUnitDao {
    List<NetcaseUnit> getNetcaseUnitListByLeafId(String unitId);

    void updateUnitIdById(String unit_id,String id);

    void insertNetcaseUnitLog(NetcaseUnitLog netcaseUnitLog);
}
