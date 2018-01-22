package com.DBUtile;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StrTransformObject{

    public static Object StrTransformObject(Class clazz, HttpServletRequest request) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Field[] fields = clazz.getDeclaredFields();
        Object object = clazz.newInstance();
        for (Field field : fields) {
            String tempFieldName = field.getName();
            String methodName = "set" + tempFieldName.substring(0, 1).toUpperCase() + tempFieldName.substring(1);
            String fieldVal = request.getParameter(tempFieldName);
            if (fieldVal != null && !fieldVal.equals("")&&!fieldVal.equals("undefined")) {
                Method method = clazz.getDeclaredMethod(methodName, field.getType());
                if (field.getType().getSimpleName().equals("String")) {
                    method.invoke(object, fieldVal);
                } else if (field.getType().getSimpleName().equals("int")) {
                    method.invoke(object, Integer.parseInt(fieldVal));
                }else if (field.getType().getSimpleName().equals("Integer")) {
                    method.invoke(object, Integer.parseInt(fieldVal));
                }
            }
        }
        return object;
    }

}
