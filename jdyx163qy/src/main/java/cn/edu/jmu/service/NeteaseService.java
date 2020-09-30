package cn.edu.jmu.service;

import cn.edu.jmu.pojo.NetcaseUnit;
import cn.edu.jmu.pojo.NeteaseAccount;
import cn.edu.jmu.pojo.ResultValue;
import org.json.JSONException;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Transactional
public interface NeteaseService {
    NeteaseAccount getNetcaseAccount(String id);
    List<NetcaseUnit> getNetcaseUnitListByLeafId(String unitId);
    String getAccount(HttpServletRequest request);
    String getUserInfo(HttpServletRequest request);
    String getMobile(HttpServletRequest request);
    String updateAccount(HttpServletRequest request,String mobile);
    String updatePassWord(HttpServletRequest request,String password);
    String createAccount(HttpServletRequest request,String mobile,String password);
}
