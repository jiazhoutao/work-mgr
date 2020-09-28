package cn.edu.jmu.controller;

import cn.edu.jmu.pojo.NetcaseUnit;
import cn.edu.jmu.pojo.NeteaseAccount;
import cn.edu.jmu.service.NeteaseService;
import cn.edu.jmu.util.RSASignatureToQiye;
import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;
import com.wiscom.is.impl.BaseGroup;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

@RestController
public class Qyyx163 {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NeteaseService neteaseService;

    private static String QYYX_URL="https://apihz.qiye.163.com/qiyeservice/api";


    private static final String PRI_KEY = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100a182a3b66cfc0f2284869b1cf9ffd8f4c943ac50213c4d9a8bdff78f99b98915e7c9e685cfb821b4a70874adc644ac9662e60b74cba1239ff35ed22d89aa995069f0df4384b1e254c43cac4f816b0719402c03464dc6b99631d8d3b4a00a295e2147e4edb8a157a0a8e0cfa348daa8a2177a02efbf77a1468c4fceccb12e22ef0203010001028180609f4e1fa9cd95a459d24963a9705534046016eb0d270d6a22ba07598266cbcb2e12cc754b12661fb439cbcbd62c696fc294cb4fc12e92a873294be5f050fe4c77065cf2fb2b6d38cb5aad0bf296b5ea8e6579fba735c0ff140a49d87303fc2ffee2628367d48d5bf51d05b838147e392ee9bff239ee77876fbccf12a398b729024100e6bfb89e1cc30d27a8cfabf4274e2437ea9f625b79b30a89f022f63e502ba5d12246e6f895dcfca24f4347178b2e7e1fe24ddd0adcbf49123732d1c729a5fefd024100b32f3e99b4567ddceda50f839f1942551b1246ffdbc76b22de2faa87149c9404a5d27c98f86e2c0b235e6d686ca83360b29c2e61ece1c5584178975942382b5b02400887f814686dd1b3b0ab14e96ef695a2379b72f07cdc60bc5ed37c152bb3a16acd00a0137abef3dc30600565da707a9b452ba9cb7803de78bcefb50fcefb632502400b0c69b6be88eabfd9a368de7bcbe9ec277c2c0690bdae1c7b48c3220b22507a2ce35aad6ffc131928b824d4e0864a8cb6d6ac817b42bc91619016fcf8f94fab0241008ae4eec1664dff563c8719cbfbd1c5fe5244d033cd725d82078fc3c44f7ee05fce3b5f05b93cac7737fb9a0143b939f32bbdb99031456be52322efa25f9eec0f";

    @GetMapping(value = "/jdyx/account")
    public String getAccount(HttpServletRequest request) {
        JSONObject jsonObject = getUserNameAndGroup(request);
        String curUser= null;
        try {
            curUser = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(curUser==null||curUser.length()==0){
           return "{'con': null, 'suc': false, 'ver': null, error: 'curUser is null'}";
        }
        String domain = getDomain(jsonObject);
        String currentTimeStamps = System.currentTimeMillis()+"";
        String sign = "account_name="+curUser+"&domain="+domain+"&product=test_jmu_edu_cn&time="+currentTimeStamps;
        String urlParams = "account_name="+curUser+"&domain="+domain+"&product=test_jmu_edu_cn&time="+currentTimeStamps;
        System.out.println(sign);
        sign = RSASignatureToQiye.generateSigature(PRI_KEY, sign);
        System.out.println(sign);
        urlParams+="&sign="+sign;
        ResponseEntity<String> obj= restTemplate.postForEntity(QYYX_URL+"/account/getAccount?"+urlParams, null, String.class);
        System.out.println(obj);
        return obj.getBody();
    }

    @GetMapping(value = "/jdyx/account/user-info")
    public String getUserInfo(HttpServletRequest request) {
        JSONObject jsonObject = getUserNameAndGroup(request);
        String curUser= null;
        try {
            curUser = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(curUser==null||curUser.length()==0){
            return "";
        }
        return curUser;
    }

    private JSONObject getUserNameAndGroup(HttpServletRequest request) {

        String curUser = "";
        JSONObject jsonObject=new JSONObject();
        try {
            String is_config = request.getServletContext().getRealPath("/client.properties");
            File configFile = ResourceUtils.getFile("classpath:client.properties");
            is_config=configFile.getAbsolutePath();
            Cookie all_cookies[] = request.getCookies();
            Cookie myCookie;
            String decodedCookieValue = null;
            if (all_cookies != null) {
                for(int i=0; i< all_cookies.length; i++) {
                    myCookie = all_cookies[i];
                    if( myCookie.getName().equals("iPlanetDirectoryPro") ) {
                        decodedCookieValue = URLDecoder.decode(myCookie.getValue(),"GB2312");
                    }
                }
            }
            IdentityFactory factory = IdentityFactory.createFactory( is_config );
            IdentityManager im = factory.getIdentityManager();
            if (decodedCookieValue != null ) {
                curUser = im.getCurrentUser( decodedCookieValue );
            }
            if(curUser.length()==0){
                String gotoURL = HttpUtils.getRequestURL(request).toString();
                String loginURL = im.getLoginURL() +"?goto=" + java.net.URLEncoder.encode(gotoURL);
            }
            List<BaseGroup> list=im.getUserGroup(curUser);
            im.getOrgAllGroups(curUser);
            String[] groups=new String[list.size()];
            for(int i=0;i<list.size();i++){
                BaseGroup baseGroup=list.get(i);
                System.out.println("g:"+baseGroup.toString());
                groups[i]=baseGroup.toString();
            }
            jsonObject.put("userName",curUser);
            jsonObject.put("group",groups);
            System.out.println(jsonObject);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return jsonObject;
    }


    @GetMapping(value = "/jdyx/account/mobile")
    public String getMobile(HttpServletRequest request) {
        JSONObject jsonObject = getUserNameAndGroup(request);
        String curUser= null;
        try {
            curUser = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(curUser==null||curUser.length()==0){
            return "{'con': null, 'suc': false, 'ver': null, error: 'curUser is null'}";
        }
        String domain = getDomain(jsonObject);
        String currentTimeStamps = System.currentTimeMillis()+"";
        String sign = "account_name="+curUser+"&domain="+domain+"&product=test_jmu_edu_cn&time="+currentTimeStamps;
        String urlParams = "account_name="+curUser+"&domain="+domain+"&product=test_jmu_edu_cn&time="+currentTimeStamps;
        System.out.println(sign);
        sign = RSASignatureToQiye.generateSigature(PRI_KEY, sign);
        System.out.println(sign);
        urlParams+="&sign="+sign;
        ResponseEntity<String> obj= restTemplate.postForEntity(QYYX_URL+"/mobile/getMobile?"+urlParams, null, String.class);
        System.out.println(obj);
        return obj.getBody();
    }

    @PostMapping(value = "/jdyx/account")
    public String createAccount(HttpServletRequest request,String mobile,String password) throws UnsupportedEncodingException {
        JSONObject jsonObject = getUserNameAndGroup(request);
        String curUser= null;
        try {
            curUser = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(curUser==null||curUser.length()==0){
            return "{'con': null, 'suc': false, 'ver': null, error: 'curUser is null'}";
        }
        String domain = getDomain(jsonObject);
        NeteaseAccount neteaseAccount= neteaseService.getNetcaseAccount(curUser);
        String unitId = neteaseAccount.getUnitId();
        String currentTimeStamps = System.currentTimeMillis()+"";

        List<NetcaseUnit>  list= neteaseService.getNetcaseUnitListByLeafId(unitId);

        for(NetcaseUnit netcaseUnit:list){
            String neteaseId=netcaseUnit.getNeteaseId();
            System.out.println("neteaseId："+neteaseId);
        }








//        String urlParams =
//                 "account_name="+curUser
//                +"&domain=" +domain
//                +"&mobile="+mobile+"" +
//                "&nickname="+curUser+"" +
//                "&pass_type=1" +
//                "&passchange_req=1" +
//                "&password="+password+"" +
//                "&product=test_jmu_edu_cn" +
//                "&time="+currentTimeStamps+"" +
//                "&unit_id=default";
//        String sign = RSASignatureToQiye.generateSigature(PRI_KEY, urlParams);
//        System.out.println(sign);
//        urlParams+="&sign="+sign;
//        ResponseEntity<String> obj= restTemplate.postForEntity(QYYX_URL+"/account/createAccount?"+urlParams, null, String.class);
//        System.out.println(obj);
        return "obj.getBody()";
    }

    private String getDomain(JSONObject jsonObject) {
        String domain="jmu.edu.cn";
        try {
            String[] groups=(String[]) jsonObject.get("group");
            if (-1!=Arrays.binarySearch(groups,"BZKS")){
                domain="jmu.edu.cn";
            };
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return domain;
    }


    @PutMapping(value = "/jdyx/account/mobile")
    public String updateAccount(HttpServletRequest request,String mobile) throws UnsupportedEncodingException {
        String currentTimeStamps = System.currentTimeMillis()+"";
        JSONObject jsonObject = getUserNameAndGroup(request);
        String curUser= null;
        try {
            curUser = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(curUser==null||curUser.length()==0){
            return "{'con': null, 'suc': false, 'ver': null, error: 'curUser is null'}";
        }
        String domain = getDomain(jsonObject);

        String urlParams ="account_name="+curUser
                        +"&domain="+domain +
                        "&mobile="+mobile+"" +
                        "&product=test_jmu_edu_cn" +
                        "&time="+currentTimeStamps+"" +
                        "&unit_id=default";
        String sign = RSASignatureToQiye.generateSigature(PRI_KEY, urlParams);
        System.out.println(sign);
        urlParams+="&sign="+sign;
        ResponseEntity<String> obj= restTemplate.postForEntity(QYYX_URL+"/mobile/addMobile?"+urlParams, null, String.class);
        System.out.println(obj);
        return obj.getBody();
    }

    @PutMapping(value = "/jdyx/account/password")
    public String updatePassWord(HttpServletRequest request,String password) throws UnsupportedEncodingException {
        String currentTimeStamps = System.currentTimeMillis()+"";
        JSONObject jsonObject = getUserNameAndGroup(request);
        String curUser= null;
        try {
            curUser = jsonObject.getString("userName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(curUser==null||curUser.length()==0){
            return "{'con': null, 'suc': false, 'ver': null, error: 'curUser is null'}";
        }
        String domain = getDomain(jsonObject);

        String urlParams ="account_name="+curUser
                +"&domain="+domain +
                "&pass_type=1" +
                "&passchange_req=0" +
                "&password="+password+"" +
                "&product=test_jmu_edu_cn" +
                "&time="+currentTimeStamps;
        String sign = RSASignatureToQiye.generateSigature(PRI_KEY, urlParams);
        System.out.println(sign);
        urlParams+="&sign="+sign;
        ResponseEntity<String> obj= restTemplate.postForEntity(QYYX_URL+"/account/updatePassword?"+urlParams, null, String.class);
        System.out.println(obj);
        return obj.getBody();
    }
}
