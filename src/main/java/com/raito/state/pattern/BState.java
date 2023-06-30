package com.raito.state.pattern;

/**
 * @author raito
 * @date 2023/6/30
 */
public class BState extends State {

    @Override
    protected void b2c(Context context) {
        System.out.println("状态流转：b State ->c State");
        context.setState(new CState());
    }


}
