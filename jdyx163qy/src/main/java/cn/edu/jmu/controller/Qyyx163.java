package cn.edu.jmu.controller;

import cn.edu.jmu.service.NeteaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class Qyyx163 {

    @Autowired
    private NeteaseService neteaseService;


    @GetMapping(value = "/jdyx/account")
    public String getAccount(HttpServletRequest request) {
        return neteaseService.getAccount(request);
    }

    @GetMapping(value = "/jdyx/account/user-info")
    public String getUserInfo(HttpServletRequest request) {
       return neteaseService.getUserInfo(request);
    }




    @GetMapping(value = "/jdyx/account/mobile")
    public String getMobile(HttpServletRequest request) {
        return neteaseService.getMobile(request);
    }

    @PostMapping(value = "/jdyx/account")
    public String createAccount(HttpServletRequest request,String mobile,String password) throws UnsupportedEncodingException {
        return neteaseService.createAccount(request,mobile,password);
    }




    @PutMapping(value = "/jdyx/account/mobile")
    public String updateAccount(HttpServletRequest request,String mobile) throws UnsupportedEncodingException {
        return neteaseService.updateAccount(request,mobile);
    }

    @PutMapping(value = "/jdyx/account/password")
    public String updatePassWord(HttpServletRequest request, String password) throws UnsupportedEncodingException {
        return neteaseService.updatePassWord(request,password);
    }
}
