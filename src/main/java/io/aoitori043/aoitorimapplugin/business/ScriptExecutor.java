package io.aoitori043.aoitorimapplugin.business;

import io.aoitori043.aoitorimapplugin.AoitoriMapPlugin;
import org.jetbrains.annotations.Nullable;

import javax.script.*;
import java.util.Map;
import java.util.Set;

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
//            checkRegisteredFunctions();
//            e.printStackTrace();
        }
        return null;
    }

    public void checkRegisteredFunctions() {
        if (engine instanceof Invocable) {
            Invocable invocable = (Invocable) engine;
            ScriptContext context = engine.getContext();
            Set<String> strings = context.getBindings(ScriptContext.ENGINE_SCOPE).keySet();
            System.out.println("Registered functions:");
            for (String key : strings) {
                System.out.println(key);
            }
        }
    }


}
