package com.newer.deal.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newer.deal.entiry.Entrust;
import com.newer.deal.entiry.Freeze;
import com.newer.deal.entiry.Legalcurrency;
import com.newer.deal.entiry.Order;
import com.newer.deal.entiry.Record;
import com.newer.deal.entiry.User;
import com.newer.deal.exception.NoCurrencyException;
import com.newer.deal.repository.EntrustMapper;
import com.newer.deal.repository.FreezeMapper;
import com.newer.deal.repository.LegalcurrencyMapper;
import com.newer.deal.repository.OrderMapper;
import com.newer.deal.repository.RecordMapper;
import com.newer.deal.repository.UserMapper;

@Service
public class TransactionServiceImp implements TransactionService {
	
	
	// 用户
	@Autowired
	UserMapper userMapper;
	
	// 交易记录
	@Autowired
	RecordMapper recordMapper;
	
	// 订单
	@Autowired
	OrderMapper orderMapper;
	
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
	

	// RMB 交易
	@Transactional(
			isolation = Isolation.SERIALIZABLE,
			rollbackFor = NoCurrencyException.class
			)
	@Override
	public boolean exchangeRMB(int order_id) throws NoCurrencyException {
		
		boolean b = false;
		
		// 获得订单
		Order order = orderMapper.findById(order_id);
		
		// 订单RMB总价
		BigDecimal totalprice = order.getTotalprice();
		
		// 订单创建者
		User user = userMapper.findById(order.getUser().getUser_id());
		
		// 订单被动者
		User trading = userMapper.findById(order.getTrading().getUser_id());
		
		
		
		// 获得订单类型， 且订单支付，是没有交易过的   -- 购买或出售
		String saletype = order.getSaletype();
		// 订单必须是为支付，且订单状态是进行中
		if ("购买".equals(saletype) && "0".equals(order.getPayment_status()) && "0".equals(order.getStatus()) ) {
			
			// 判断用户RMB是否充足
			if (totalprice.doubleValue() < user.getRMB().doubleValue()) {
				
				// RMB 账户支出 （-)
				userMapper.transfer(user.getUser_id(), totalprice.negate());
				
				// RMB 账户收入 （+)
				userMapper.transfer(trading.getUser_id(), totalprice);
				
				// 修改付款状态
				b = orderMapper.updatePayment_status("1", order_id);
				
			} else {
				throw new  NoCurrencyException("用户RMB不足");
			}
		} else if ("出售".equals(saletype) && "0".equals(order.getPayment_status()) && "0".equals(order.getStatus())) {
			
			// 判断用户 RNM 是否充足
			if (totalprice.doubleValue() < trading.getRMB().doubleValue()) {
				
				// 支出 (-)
				userMapper.transfer(trading.getUser_id(), totalprice.negate());
				
				// 收入 （+）
				userMapper.transfer(user.getUser_id(), totalprice);
				
				// 修改付款状态
				b = orderMapper.updatePayment_status("1", order_id);
				
			} else {
				throw new  NoCurrencyException("用户RMB不足");
			}
			
			
		}
		
		
		
		return b;
	}

	// 法币交易
	@Transactional(
			isolation = Isolation.SERIALIZABLE,
			rollbackFor = NoCurrencyException.class
			)
	@Override
	public boolean exchangeDeal(int order_id) throws NoCurrencyException {
		
		
		boolean b = false;
		
		// 获得订单
		Order order = orderMapper.findById(order_id);
		
		
		
		// 获取付款状态  
		String payment_status = order.getPayment_status();
		
		if (!"1".equals(payment_status)) {
			throw new NoCurrencyException("未付款");
		}
		
		// 获取订单中的委托
		Entrust entrust = entrustMapper.loadByEntrustId(order.getEntrust_id());
		
		// 获取委托中  冻结法币的id
		Freeze freeze = freezeMapper.load(entrust.getFreeze_id());
		
		// 订单中货币总数
		BigDecimal dealnumber = order.getDealnumber();
		
		
		
		// 订单必须是进行中的
		if (!"0".equals(order.getStatus())) {
			throw new NoCurrencyException("订单已失效");
		}
		
		// -- 只有付款后，才能放币
		if (!"1".equals(payment_status)) {
			throw new NoCurrencyException("未付款");
		}
		
		// 创建一条交易记录
		Record record = new Record();
		// 交易币种
		record.setCurrency_id(order.getCurrency().getId());
		// 交易总RMB
		record.setAmount(order.getTotalprice());
		// 货币数量
		record.setQuantity(dealnumber);
		// 订单Id
		record.setOrder_id(order_id);
		// 委托id
		record.setEntrust_id(entrust.getEntrust_id());
		
		
		
		
		
		
		// 判断订单创建者
		if ("购买".equals(order.getSaletype())) {
			
			// 转出用户
			record.setUser_from(order.getTrading().getUser_id());
			// 转入用户
			record.setUser_to(order.getUser().getUser_id());
			
			// 事务的传播行为 Propagation.REQUIRES_NEW
			// 该方法在新事务中执行 (嵌套事务)
			TransactionService transactionService = context.getBean(TransactionService.class);
			// Spring 容器创建，开启新事务，基于代理 AOP 执行，必须从容器（上下文）获得新组件实例再执行（才创建新的内部事务）
			transactionService.createExchange(record);
			
			// 冻结库中货币不足时，交易失败
			if (freeze.getCount().doubleValue() < order.getDealnumber().doubleValue()) {
				throw new NoCurrencyException("上架的委托，货币不足");
			}
			
			// 冻结账户放币（-）
			freezeMapper.updateAmount(freeze.getFreeze_id(), dealnumber.negate());
			
			// 买家 入币  （+）
			legalMapper.updateCount(order.getUser().getUser_id(), order.getCurrency().getId(), dealnumber);
			
		}else if("出售".equals(order.getSaletype())) {
			
			// 转出用户
			record.setUser_from(order.getUser().getUser_id());
			// 转入用户
			record.setUser_to(order.getTrading().getUser_id());
			
			// 出售时需要   -- 获取订单创建者法币
			Legalcurrency legalcurrency = legalMapper.findByUserAndCurrencyId(order.getTrading().getUser_id(), order.getCurrency().getId());
			
			
			// 事务的传播行为 Propagation.REQUIRES_NEW
			// 该方法在新事务中执行 (嵌套事务)
			TransactionService transactionService = context.getBean(TransactionService.class);
			// Spring 容器创建，开启新事务，基于代理 AOP 执行，必须从容器（上下文）获得新组件实例再执行（才创建新的内部事务）
			transactionService.createExchange(record);
			
			if (legalcurrency.getCount().doubleValue() < order.getDealnumber().doubleValue()) {
				throw new NoCurrencyException("卖家法币不足");
			}
			
			// 冻结账户放币（+）
			freezeMapper.updateAmount(freeze.getFreeze_id(), dealnumber);
			
			// 买家 入币  （-）
			legalMapper.updateCount(order.getTrading().getUser_id(), order.getCurrency().getId(), dealnumber.negate());
			
			
		}
		
		// 修改订单最终状态
		b = orderMapper.updateStatus("1", order_id);
		
		// 修改交易记录的最终状态
		recordMapper.updateStatus(record.getDeal_id());
		
		
		return b;
	}

	
	// 交易记录
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean createExchange(Record record) {
		
		// 写入交易记录（写日志优先
		boolean b = recordMapper.createRecord(record);
		
		return b;
	}

}
