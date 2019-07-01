package com.hm.shop.service.impl;

import com.hm.shop.bean.ArticleType;
import com.hm.shop.bean.User;
import com.hm.shop.mapper.ArticleTypeMapper;
import com.hm.shop.mapper.UserMapper;
import com.hm.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/7/1/001 22:59
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ArticleType> getArticleTypes() {
        return articleTypeMapper.getArticleTypes();
    }

    @Override
    public Map<String, Object> login(String loginName, String passWord) {

        Map<String, Object> map = new HashMap<String, Object>();
        User user = userMapper.findUserByName(loginName);

        //用户不存在
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(passWord)) {
            map.put("code", 1);
            map.put("msg", "请输入账户或密码");
        } else {
            if (user == null) {
                map.put("code", 2);
                map.put("msg", "该用户不存在");
            } else {
                if (!user.getPassword().equals(passWord)) {
                    map.put("code", 3);
                    map.put("msg", "密码错误");
                } else {
                    map.put("code", 0);
                    map.put("msg", user);
                }
            }

        }
        return map;
    }
}
