package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import org.jetbrains.annotations.Nullable;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @Author: natsumi
 * @CreateTime: 2024-10-03  21:47
 * @Description: ?
 */
public class ScriptExecutor {



    private final ScriptEngine engine;

    public ScriptExecutor() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName("nashorn");
        if (engine == null) {
            throw new IllegalArgumentException("Script engine not found for: " + "nashorn");
        }
    }


    public Object executeScript(String script) {
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

    public void addFunction(String functionName, @Nullable String functionBody){
        if (functionBody == null || functionBody.isEmpty()) return;
        try {
            String script = "function " + functionName + "(map) { " + functionBody + " }";
            engine.eval(script);
        }catch (Exception e){
            AoitoriMapPlugin.plugin.getLogger().warning("Unable to compile JS function: "+functionBody);
            e.printStackTrace();
        }
    }

    public Object invokeFunction(String functionName, Object... args) {
        try {
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                return invocable.invokeFunction(functionName, args);
            } else {
                throw new UnsupportedOperationException("ScriptEngine does not support invoking functions");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
