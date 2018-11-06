package com.yangbingdong.schedule.job;

import com.yangbingdong.schedule.fms.AbstractActivityStateMachine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @author ybd
 * @date 18-11-2
 * @contact yangbingdong1994@gmail.com
 */
@Component
public class ActivityStateMachineMapHolder implements InitializingBean {

	@Resource
	private List<AbstractActivityStateMachine> baseActivityStateMachines = new ArrayList<>(16);


	private Map<Byte, AbstractActivityStateMachine> byteBaseActivityStateMachineMap;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (baseActivityStateMachines == null || baseActivityStateMachines.size() == 0) {
			throw new IllegalStateException("Error to init ActivityScheduleJob!");
		}
		byteBaseActivityStateMachineMap = baseActivityStateMachines.stream()
																   .collect(toMap(AbstractActivityStateMachine::getActType, identity()));
	}

	public AbstractActivityStateMachine getStateMachineByCode(Byte code) {
		return byteBaseActivityStateMachineMap.get(code);
	}
}
