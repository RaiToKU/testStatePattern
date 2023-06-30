package com.raito.state.pattern;

/**
 * @author raito
 * @date 2023/6/30
 */
public class Main {

    public static void main(String[] args) {
        Context context = new Context(StateFactory.getState(AState.class.getSimpleName()));
        context.a2b();
        context.a2c();

    }

}
