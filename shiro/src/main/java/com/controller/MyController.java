package com.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController  {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","succeed");

        return "index";
    }

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("msg","succeed");

        return "add";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/unauthu")
    public String unauthu(Model model){
        model.addAttribute("msg","兄弟，你没权限");
        return "unauthu";
    }

    @RequestMapping("/update")
    public String update(Model model){
        model.addAttribute("msg","succeed");

        return "update";
    }



    @PostMapping("/login")
    public String canLogin(String username, String password, Model model){

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登陆数据,生成token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //利用令牌进行登录
        try {
            //登录成功，返回首页
            subject.login(token);  //调用该方法，即到realm中进行认证
            model.addAttribute("msg", "success");
            return "index";
            //登陆失败，返回登录页
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "用户名不正确");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (LockedAccountException lae) {
            model.addAttribute("msg", "账号被锁定");
            return "login";
        }

    }

    @RequestMapping("/logout")
    public String logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "/index";
    }

}
