package com.example.coursedesign.controller;

import cn.hutool.core.map.MapUtil;
import com.example.coursedesign.DTO.ChoseDto;
import com.example.coursedesign.DTO.LoginDto;
import com.example.coursedesign.DTO.RegisterDto;
import com.example.coursedesign.Untils.JwtUtil;
import com.example.coursedesign.Untils.ShiroUntil;
import com.example.coursedesign.entity.Mydate;
import com.example.coursedesign.entity.Predete;
import com.example.coursedesign.entity.Result;
import com.example.coursedesign.entity.User;
import com.example.coursedesign.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @RequiresAuthentication
    @GetMapping("index")
    public Result index(){
        User user = service.getUserbyId(1);
        return Result.succ(user);
    }

    /**注册**/
    @PostMapping("register")
    @ResponseBody
    public Result register(@Validated @RequestBody RegisterDto registerDto,HttpServletResponse response){
        User user = new User();
        user.setId(registerDto.getId());
        user.setPassword(registerDto.getPassword());
        user.setName(registerDto.getName());
        user.setCompany(registerDto.getCompany());

        String message = service.insertUser(user);

        if(message!=null) {
            return Result.fail(message);
        }

        String token = jwtUtil.generateToken(user.getId());
        response.setHeader("Authorization",token);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.succ(null);
    }

    /**登录**/
    @PostMapping("login")
    @ResponseBody
    public Result login(@Validated @RequestBody LoginDto loginDto,HttpServletResponse response){
        User user = service.getUserbyId(loginDto.getId());
        Assert.notNull(user,"用户不存在");
        if (loginDto.getUsertype() != user.getType())
            return Result.fail("请检查用户类型");

        if(!user.getPassword().equals(loginDto.getPassword()))
            return Result.fail("密码不正确");

        String token = jwtUtil.generateToken(user.getId());
        response.setHeader("Authorization",token);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.succ(MapUtil.builder()
                            .put("userid",user.getId())
                            .put("usercompany",user.getCompany())
                            .map());
    }

    /**注销**/
    @RequiresAuthentication //登录后有权限
    @GetMapping("logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    /**获取所有实验室ID**/
    @GetMapping("getlabs")
    @RequiresAuthentication
    public Result getlabs(){
        return Result.succ(service.getlabs());
    }

    /**查预约情况 返回List集合**/
    @GetMapping("getuserlabs")
    @RequiresAuthentication
    public Result getuserlabs(long userid){
        if (userid != ShiroUntil.getprofile().getId())
            return Result.fail("你不是该用户，不能查看");
        Assert.isTrue(userid == ShiroUntil.getprofile().getId(),"你不是该用户，不能查看"+ShiroUntil.getprofile().getId());
        List<Predete> predetes = service.selectPredeteByUserid(userid);
        return Result.succ(predetes);
    }

    /**取消预约 返回用户预约信息**/
    @PostMapping("cancelpre")
    @RequiresAuthentication
    public Result cancelpre(@Validated @RequestBody Predete predete){

        predete.setUserid(ShiroUntil.getprofile().getId());

        // 查询是否已预约
        int flag = 0;
        List<Predete> p1 = service.selectPredeteByTwoId(predete.getUserid(), predete.getLabid());
        for (Predete pre1 : p1) {
            if (predete.getWeek()==pre1.getWeek() && predete.getDay().equals(pre1.getDay()) && predete.getTime()==pre1.getTime())
                flag = 1;
        }
        if (flag == 0)
            return Result.fail("您未预约当天的该实验室");
        service.cancelpre(predete);
        return Result.succ(service.selectPredeteByUserid(predete.getUserid()));
    }


    /**查实验室*预约情况**/
    @GetMapping("selectlab")
    public Result selectlab(int labid){
        int flag =0;
        List<Integer> labs = service.getlabs();
        for (Integer lab : labs) {
            if (labid == lab)
                flag = 1;
        }
        if (flag == 0)
            return Result.fail("实验室不存在");
        return Result.succ(service.selectPretimeByLabid(labid));
    }

    /**预约实验室**/
    @PostMapping("choselab")
    @RequiresAuthentication
    public Result choselab(@Validated @RequestBody ChoseDto choseDto){

        choseDto.setUserid(ShiroUntil.getprofile().getId());
        List<Mydate> selectdates = service.selectPretimeByLabid(choseDto.getLabid());
        List<Mydate> dates = choseDto.getDates();
        for (Mydate date : dates) {
            if (selectdates.stream().filter(o -> o.getDay().equals(date.getDay()))
                    .filter(o->o.getWeek()==date.getWeek())
                    .filter(o->o.getTime()==date.getTime())
                    .findAny().isPresent())  //如果已被选择
//                throw new Exception("实验室"+choseDto.getLabid()+"在"+date+"已被选择");
                return Result.fail("实验室"+choseDto.getLabid()+"在"+date+"已被选择");
            Predete predete = new Predete();
            predete.setLabid(choseDto.getLabid());
            predete.setUserid(choseDto.getUserid());
            predete.setUsername(choseDto.getUsername());
            predete.setWeek(date.getWeek());
            predete.setDay(date.getDay());
            predete.setTime(date.getTime());
            service.choseLab(predete);
        }
        return  Result.succ(service.selectPredeteByUserid(choseDto.getUserid()));
    }

    /**根据星期查询预约**/
    @PostMapping("weeklab")
    @RequiresAuthentication
    public Result sellabbyweek(int week){
        long userid = ShiroUntil.getprofile().getId();
        List<Mydate> mydates =service.selectLabByWeek(week,userid);
        return Result.succ(mydates);
    }
}
