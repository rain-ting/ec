package com.newer.deal.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.newer.deal.entiry.Entrust;
import com.newer.deal.entiry.Freeze;
import com.newer.deal.entiry.Legalcurrency;
import com.newer.deal.exception.NoCurrencyException;
import com.newer.deal.repository.EntrustMapper;
import com.newer.deal.repository.FreezeMapper;
import com.newer.deal.repository.LegalcurrencyMapper;

@Service
public class EntrustServiceImpl implements EntrustService {

	// 委托
	@Autowired
	EntrustMapper entrustMapper;
	
	// 法币
	@Autowired
	LegalcurrencyMapper legalMapper;
	
	// 冻结
	@Autowired
	FreezeMapper freezeMapper;
	
	// 上下文
	@Autowired
	ApplicationContext context;
	
	// 事务处理  -- 上架
	@Transactional(
			isolation = Isolation.SERIALIZABLE,
			rollbackFor = NoCurrencyException.class
			)
	@Override
	public boolean issue(int entrust_id) throws NoCurrencyException {
		
		// 业务逻辑
		boolean b = false;
		
		// 通过委托编号获取委托信息
		Entrust entrust = entrustMapper.loadByEntrustId(entrust_id);
		
		if (entrust.getFreeze_id()>0) {
			// 检测此委托有没有关联的冻结记录
			Freeze fres = freezeMapper.load(entrust.getFreeze_id());
			if (fres != null) {
				// 回滚
				throw new NoCurrencyException("此委托正上架中");
			}
		}
		
		// 查看上架的委托是购买，还是出售
		String s = entrust.getSaletype();
		
		// 如果是出售，则将一部分资产冻结 -- 转入 冻结货币库
		if (s.equals("出售")) {
			// 检查用户的法币库资产是否充足
			Legalcurrency legalcurrency = legalMapper.findByUserAndCurrencyId(entrust.getUser().getUser_id(), entrust.getCurrency().getId());
		
			// 判断 若 出售资产大于 已有资产
			if (entrust.getDeal_max().doubleValue() > legalcurrency.getCount().doubleValue()) {
				// 资产不足 
				// 回滚
				throw new NoCurrencyException();
			} 
				
			// 用户的法币库资产 （-）
			legalMapper.updateCount(entrust.getUser().getUser_id(), entrust.getCurrency().getId(), entrust.getDeal_max().negate());
			
			// 冻结账户创建记录
			Freeze freeze = new Freeze();
			freeze.setUser_id(entrust.getUser().getUser_id());
			freeze.setCurrency_id(entrust.getCurrency().getId());
			freeze.setCount(entrust.getDeal_max());
			// 插入数据库
			freezeMapper.create(freeze);
			
			// 设置冻结编号
			entrust.setFreeze_id(freeze.getFreeze_id());
			
			// 数据库修改冻结编号
			entrustMapper.updateFreeze(freeze.getFreeze_id(), entrust_id);
			
			b = entrustMapper.updateIssue(entrust_id);
					
				
			
		
		} else {
			// 购买
			
			// 冻结账户创建记录
			Freeze freeze = new Freeze();
			freeze.setUser_id(entrust.getUser().getUser_id());
			freeze.setCurrency_id(entrust.getCurrency().getId());
			freeze.setCount(new BigDecimal("0"));
			
			// 插入数据库
			freezeMapper.create(freeze);
			
			// 设置冻结编号
			entrust.setFreeze_id(freeze.getFreeze_id());
			
			// 数据库修改冻结编号
			entrustMapper.updateFreeze(freeze.getFreeze_id(), entrust_id);
		
			// 上架完成
			b = entrustMapper.updateIssue(entrust_id);
				
			
		}
		
		
		return b;
	}

	// 事务处理  -- 下架
	@Transactional(
			isolation = Isolation.SERIALIZABLE,
			rollbackFor = NoCurrencyException.class
			)
	@Override
	public boolean soldOut(int entrust_id) throws NoCurrencyException {
		
		// 业务逻辑
		boolean b = false;
		
		// 通过委托编号获取委托信息
		Entrust entrust = entrustMapper.loadByEntrustId(entrust_id);
		

		// 获取指定冻结资产
		Freeze freeze = freezeMapper.load(entrust.getFreeze_id());
		
		
		// 判断冻结记录是否存在
		if (freeze == null) {
			// 回滚
			throw new NoCurrencyException("冻结记录不存在");
		} else if(freeze.getCount().doubleValue() < 0) {
			// 回滚
			throw new NoCurrencyException("冻结记录负数");
		}
		
		// 将冻结资产返回用户自己的法币库存 (+)
		legalMapper.updateCount(freeze.getUser_id(), freeze.getCurrency_id(), freeze.getCount());
	
		// 删除冻结记录
		freezeMapper.removeFreeze(entrust.getFreeze_id());
		
		// 数据库修改冻结编号
		entrustMapper.updateFreeze(0, entrust_id);
		
		
		// 下架成功
		b = entrustMapper.updateSoldOut(entrust_id);
			

		
		return b;
	}

	@Override
	public List<Entrust> findAll() {
		return entrustMapper.findByStatusOnIssue();
	}

	@Override
	public List<Entrust> lookAll(int user_id) {
		return entrustMapper.loadByUserId(user_id);
	}

	@Override
	public void found(Entrust entrust) {
		
		entrustMapper.create(entrust);
	}

}
