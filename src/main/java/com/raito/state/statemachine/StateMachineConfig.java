package com.raito.state.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateBuilder;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.configurers.ConfigurationConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author raito
 * @date 2023/6/30
 */
@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    //配置初始状态
    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states)
            throws Exception {
        states
                .withStates()
                .initial(OrderStates.UNPAID)
                .states(EnumSet.allOf(OrderStates.class));
    }

    //配置状态转换的事件关系（多个）
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(OrderStates.UNPAID).target(OrderStates.WAITING_FOR_DELIVER).event(OrderEvents.PAY)
                .and()
                .withExternal()
                .source(OrderStates.WAITING_FOR_DELIVER).target(OrderStates.WAITING_FOR_RECEIVE)
                .event(OrderEvents.DELIVER)
                .and()
                .withExternal()
                .source(OrderStates.WAITING_FOR_RECEIVE).target(OrderStates.DONE).event(OrderEvents.RECEIVE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(listener());
    }

    @Bean
    public StateMachineListener<OrderStates, OrderEvents> listener() {
        return new StateMachineListenerAdapter<OrderStates, OrderEvents>() {

            @Override
            public void transition(Transition<OrderStates, OrderEvents> transition) {
                if(transition.getTarget().getId() == OrderStates.UNPAID) {
                    log.info("订单创建，待支付");
                    return;
                }

                if(transition.getSource().getId() == OrderStates.UNPAID
                        && transition.getTarget().getId() == OrderStates.WAITING_FOR_DELIVER) {
                     log.info("用户完成支付，待发货");
                    return;
                }

                if(transition.getSource().getId() == OrderStates.WAITING_FOR_DELIVER
                        && transition.getTarget().getId() == OrderStates.WAITING_FOR_RECEIVE) {
                     log.info("订单已发货，待收货");
                    return;
                }

                if(transition.getSource().getId() == OrderStates.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == OrderStates.DONE) {
                     log.info("用户已收货，订单完成");
                    return;
                }
            }
        };
    }


}
