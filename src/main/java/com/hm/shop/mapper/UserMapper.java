package com.hm.shop.mapper;

import com.hm.shop.bean.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/2/002 0:45
 */
public interface UserMapper {

    /**
     * 查询用户通过name
     *
     * @param userName
     * @return
     */
    @Select("select * from ec_user where LOGIN_NAME=#{XX}")
    User findUserByName(String userName);
}
