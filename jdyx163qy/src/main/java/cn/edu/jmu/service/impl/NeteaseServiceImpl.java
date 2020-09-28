package cn.edu.jmu.service.impl;

import cn.edu.jmu.dao.NeteaseAccountDao;
import cn.edu.jmu.dao.NeteaseUnitDao;
import cn.edu.jmu.pojo.NetcaseUnit;
import cn.edu.jmu.pojo.NeteaseAccount;
import cn.edu.jmu.service.NeteaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeteaseServiceImpl implements NeteaseService {
    @Autowired
    private NeteaseUnitDao netcaseUnitDao;
    @Autowired
    private NeteaseAccountDao netcaseAccountDao;
    @Override
    public NeteaseAccount getNetcaseAccount(String curUser) {
        return netcaseAccountDao.getNetcaseAccount(curUser);
    }

    @Override
    public List<NetcaseUnit> getNetcaseUnitListByLeafId(String unitId) {
        return netcaseUnitDao.getNetcaseUnitListByLeafId(unitId);
    }


}
