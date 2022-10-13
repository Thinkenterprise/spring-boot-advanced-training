/*
 * Copyright (C) 2018 Thinkenterprise
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * @author Michael Schaefer
 */
package com.thinkenterprise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thinkenterprise.domain.tracking.FlightStatus;
import com.thinkenterprise.domain.tracking.Tracking;
import com.thinkenterprise.message.amqp.AmqpSender;

@Component
public class AmqProducerCallerScheduling implements BeanFactoryAware {

	
	public static Long counter = new Long(0);

	private static BeanFactory context;

	@Scheduled(initialDelay = 5000, fixedDelay = 5000)
	public void sendTracking() {
		Tracking tracking = new Tracking();
		tracking.setRouteId(counter++);
		tracking.setFlightNumber("LH7902");
		tracking.setStatus(FlightStatus.DELAYED);

		AmqpSender sender = context.getBean(AmqpSender.class);
		sender.sendMessage(tracking);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.context = beanFactory;

	}

}
