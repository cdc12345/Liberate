package org.cdc.liberate.transfer;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class AgentClassTransfer implements ClassFileTransformer {

    private final String[] classVisitors = new String[]{
            "org.cdc.liberate.visitors.D8WebAPIClassVisitor",
            "org.cdc.liberate.visitors.WorkspaceGeneratorSetupClassVisitor"
    };

    private int taskOrder;

    private final List<ClassVisitor> visitors;
    private final ClassPool pool;

    public AgentClassTransfer(){
        visitors = new ArrayList<>();
        pool = ClassPool.getDefault();

        loadAllClassVisitors();
    }

    @Override
    public byte[] transform(Module module,ClassLoader loader, String classPath, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        var className = toReadableClassName(classPath);
        if (className.startsWith("net.mcreator") && taskOrder != classVisitors.length){
            try {
                var ctClass = pool.get(className);
                for (ClassVisitor visitor:visitors){
                    if (visitor.isFinished()){
                        continue;
                    }
                    visitor.classBeingRedefined = classBeingRedefined;
                    visitor.classLoader = loader;
                    visitor.protectionDomain = protectionDomain;
                    visitor.visitClass(ctClass,module,className,classfileBuffer);
                    if (visitor.isFinished()){
                        taskOrder++;
                    }
                }
                return ctClass.toBytecode();
            } catch (NotFoundException | IOException | CannotCompileException e) {
                throw new RuntimeException(e);
            }
        }
        return new byte[0];
    }

    private void loadAllClassVisitors(){
        for (String clasS:classVisitors){
            try {
                var clas = Class.forName(clasS);
                visitors.add((ClassVisitor)  clas.getConstructor().newInstance());
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                     InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String toReadableClassName(String classPath){
        return classPath.replace('/','.');
    }
}
