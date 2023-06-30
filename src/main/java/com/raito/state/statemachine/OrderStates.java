package com.raito.state.statemachine;

/**
 * @author raito
 * @date 2023/6/30
 */
public enum OrderStates {

    UNPAID,                 // 待支付
    WAITING_FOR_DELIVER,    // 待发货
    WAITING_FOR_RECEIVE,    // 待收货
    DONE                    // 结束

}
