# 工程简介
请求的所有返回结果{
    code: 返回码
    message: 错误信息/操作信息
    data: 返回请求结果
}

    接口:
        登录: user/login   			
            请求体: username,password,type	
            返回值的data:成功{
                            userid: int ,(用户id)
                            usercompany: string
                            }
        失败data:null message里有失败信息
	
	
        注册: user/register  			
        请求体: id,name,password,type	
        返回值里的data:成功null  失败同上
        
        获取所有实验室信息: user/getlabs  	
        请求体:  无			
        返回值里的data:成功List<int>
        
        
        查询用户预约情况: user/getuserlabs	
        请求体: userid			
        返回值里的data:成功List<predete>
            predete结构:{userid:  用户id,
                        labid: 实验室id,
                        week: 第几周,
                        day: 星期几,
                        time: 第几节课
                        }
        
        
        取消预约: user/cancelpre		
        请求体: labid,userid,{week,day,time}(预约时间)	week:int   day:'星期一'~'星期日' time: 1~5
        返回值里的data: 成功 取消后的用户预约情况(结构同上)
        
        
        预约实验室: user/choselab		
        请求体: labid,week,day,time		
        返回值里的data: 成功 返回取消后的用户预约情况
        
        
        根据周查询:user/weeklab		
        请求体: week(int 第几周)		
        返回值data   List<predete>

token 信息存在 header 的 Authorization 里

# 延伸阅读

