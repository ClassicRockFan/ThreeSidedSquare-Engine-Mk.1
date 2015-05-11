package com.threeSidedSquareStudios.engine.core.administrative;


public class Naming {

//    public static String getReccomendedName(Entity entity) {
//        String className = entity.getClass().toString();
//        String packageName = entity.getClass().getPackage().toString();
//
//        String reccomendedName = className.substring(packageName.length() - 1);
//        return reccomendedName;
//    }

    public static String getReccomendedName(Class clazz) {
        String className = clazz.toString();
        String packageName = clazz.getPackage().toString();

        String reccomendedName = className.substring(packageName.length() - 1);
        return reccomendedName;
    }
}
