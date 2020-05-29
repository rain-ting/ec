package com.newer.deal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.deal.entiry.Currency;
import com.newer.deal.entiry.Legalcurrency;
import com.newer.deal.entiry.User;
import com.newer.deal.repository.LegalcurrencyMapper;
import com.newer.deal.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	LegalcurrencyMapper legalMapper;
	
	

	/**
	 * 完善用户信息
	 * 并在法币库创建库存
	 */
	@Override
	public User perfect(int id, User user) {
		user.setUser_id(id);
		boolean b = userMapper.save(user);
		// 完善成功，则创建法币库
		if (b) {
			initLegal(id);
		}
		
		return userMapper.findById(id);
	}
	
	/**
	 * 初始化用户的法币库
	 * @param id
	 * @return
	 */
	public boolean initLegal(int id) {
		
		List<Legalcurrency> ls = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Legalcurrency le = new Legalcurrency();
			le.setUser_id(id);
			
			Currency c = new Currency();
			c.setId(i);
			le.setCurrency(c);
			
			ls.add(le);
		}
		boolean b = false;
		for (Legalcurrency l : ls) {
			b = legalMapper.create(l);
		}
		
		return b;
	}


	@Override
	public User loadById(int id) {
		return userMapper.findById(id);
	}


	// 登录
	@Override
	public User login(User user) {
		return userMapper.register(user);
	}


	// 注册
	@Override
	public boolean add(User user) {
		return userMapper.create(user);
	}

	// 充值
	@Override
	public boolean topup(int id, User user) {
		return userMapper.topup(id, new BigDecimal(user.getIdcardno()));
	}

	// 验证资金密码
	@Override
	public Integer checkMoneyPwd(User user) {
		return userMapper.checkMoneypwd(user);
	}

}
