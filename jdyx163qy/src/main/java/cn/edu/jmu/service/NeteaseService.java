package cn.edu.jmu.service;

import cn.edu.jmu.pojo.NetcaseUnit;
import cn.edu.jmu.pojo.NeteaseAccount;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NeteaseService {
    NeteaseAccount getNetcaseAccount(String id);

    List<NetcaseUnit> getNetcaseUnitListByLeafId(String unitId);
}
