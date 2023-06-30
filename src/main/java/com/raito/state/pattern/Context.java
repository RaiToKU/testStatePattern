package com.raito.state.pattern;

/**
 * @author raito
 * @date 2023/6/30
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void a2b() {
        state.a2b(this);
    }

    public void b2c() {
        state.b2c(this);
    }

    public void a2c() {
        state.a2c(this);
    }

}
