package io.aoitori043.aoitorimapplugin.business;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:47
 * @Description: ?
 */
public class JavaScriptManager {

    private static final ScriptEngine engine;

    static{
        ScriptEngineManager engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName("nashorn");
        if (engine == null) {
            throw new IllegalArgumentException("Script engine not found for: " + "nashorn");
        }
    }

    public static Object executeScript(String script) {
        try {
            if (script == null) {
                return null;
            }
            return engine.eval(script);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Unable to parse expression： "+script);
        }
    }

}
