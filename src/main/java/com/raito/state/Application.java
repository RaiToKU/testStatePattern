package com.raito.state;

import com.raito.state.statemachine.OrderEvents;
import com.raito.state.statemachine.OrderStates;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

import javax.annotation.Resource;

/**
 * @author raito
 * @date 2023/6/30
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        createStateMachine();
    }

    @Resource
    StateMachine<OrderStates, OrderEvents> stateMachine;
    public void createStateMachine(){
        stateMachine.start();
        stateMachine.sendEvent(OrderEvents.PAY);
        stateMachine.sendEvent(OrderEvents.DELIVER);
        stateMachine.sendEvent(OrderEvents.RECEIVE);
    }
}
