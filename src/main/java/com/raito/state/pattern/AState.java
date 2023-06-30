package com.raito.state.pattern;

/**
 * @author raito
 * @date 2023/6/30
 */
public class AState extends State {

    @Override
    protected void a2b(Context context) {
        System.out.println("状态流转：a State ->b State");
        context.setState(new BState());
    }

    @Override
    protected void a2c(Context context) {
        System.out.println("状态流转：a State ->c State");
        context.setState(new BState());
    }


}
