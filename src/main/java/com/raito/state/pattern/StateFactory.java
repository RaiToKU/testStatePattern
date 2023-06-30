package com.raito.state.pattern;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author raito
 * @date 2023/6/30
 */
public class StateFactory {

    private static final Map<String, State> stateMap = new LinkedHashMap<>();

    static  {
        stateMap.put(AState.class.getSimpleName(), new AState());
        stateMap.put(BState.class.getSimpleName(), new BState());
        stateMap.put(CState.class.getSimpleName(), new CState());
    }

    public static State getState(String state) {
        return stateMap.get(state);
    }
}
