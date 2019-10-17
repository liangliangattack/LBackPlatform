package com.liang.pro.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * 反射
 * @param <T>
 */
public class SonUtil<T , C> {

    /**
     * 必须是继承关系！
     * 将父亲的属性转换到儿子身上
     * 注:父亲的方法是私有的
     * @param father
     * @param child
     * @throws Exception
     */
    public void fatherToSon(T father,C child) throws  Exception {
        if(child.getClass().getSuperclass() != father.getClass()){
            throw new Exception("没有父子血缘关系");
        }
        Class<?> fatherClass = father.getClass();
        Field[] fields = fatherClass.getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            Field field = fields[i];
            //拿到父类的所有的对应的get方法
            Method method = fatherClass.getDeclaredMethod("get"+upperHeadChar(field.getName()));
            //返回方法的返回值 没有就是null
            Object obj = method.invoke(father);
            //设置访问权限
            field.setAccessible(true);
            //由于父类的field和子类field都属于父类的T."fieldName" 如果不是继承关系 那就无法正确赋值
            //set 设置的对象使用Object
            field.set(child ,obj);
        }

    }

    /**
     * 不需要继承关系
     * 将相同属性名转换到其他类对应的属性之中
     * @param father
     * @param child
     * @throws Exception
     */
    public void classToClass(T father,C child) throws Exception {
        Class<?> fatherClass = father.getClass();
        Field[] fields = fatherClass.getDeclaredFields();
        Class<?> childClass = child.getClass();
        Field[] childFields = childClass.getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            Field field = fields[i];
            Field childField = childFields[i];
            //拿到提供属性的get方法
            Method method = fatherClass.getDeclaredMethod("get"+upperHeadChar(field.getName()));
            //返回方法的返回值 没有就是null
            Object obj = method.invoke(father);
            //设置访问权限
            childField.setAccessible(true);
            //set 设置的对象使用Object
            //按照顺序对非继承关系的类赋值
            childField.set(child ,obj);
        }

    }

    /**
     * 注意getFields（）和getMethod（）
     * @param father
     * @param child
     * @throws Exception
     */
    public void classToClass2(T father,C child) throws Exception {
        Class<?> fatherClass = father.getClass();
        Field[] fields = fatherClass.getFields();
        Class<?> childClass = child.getClass();
        Field[] childFields = childClass.getFields();
        for (int i=0;i<fields.length;i++){
            Field field = fields[i];
            Field childField = childFields[i];
            //拿到提供属性的get方法
            Method method = fatherClass.getMethod("get"+upperHeadChar(field.getName()));
            //返回方法的返回值 没有就是null
            Object obj = method.invoke(father);
            //设置访问权限
            childField.setAccessible(true);
            //set 设置的对象使用Object
            //按照顺序对非继承关系的类赋值
            //需要判断当前父类属性是否是String ，如果不是就要转化
//            System.out.println(field.getType());
            //需要用到反射来遍历  但是需要考虑到MacAlarmDetail之中存在父类属性
            // 所以反射使用getField()并且保证要用的属性是public的
            //所以将MacAlarmDetail的父类MacAlarmInfoHis属性都改成public
            //还有就是 MacAlarmDetail 的map属性也是public
            if(fieldIsWhat(field,"String")) {
                if (obj != null) {
                    childField.set(child, obj);
                    continue;
                }
            }
            else if(fieldIsWhat(field,"Date")){
                //如果是日期就特别
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //java.lang.IllegalArgumentException: Cannot format given Object as a Date 突然杀出的错误 我们判一下null
                if(obj != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    childField.set(child, simpleDateFormat.format(obj));
                }
            }
            else if(fieldIsWhat(field,"Map")){
                //这里关注了一下属性的顺序问题 比如 alarmNameCountMap，在MacAlarmDetail中是子类属性遍历他的field的时候
                //alarmNameCountMap就是第一位，而childField中如果alarmNameCountMap不是第一位就会出现问题
                if(obj != null) {
                    childField.set(child, obj);
                }
            }
            else
                if(obj != null) {
                    //否则就转化为String
                    childField.set(child, String.valueOf(obj));
                }
        }

    }

    /**
     * 判断这个field是否是String类型
     * @param field
     * @return
     */
    public boolean fieldIsWhat(Field field,String what){
        String type = field.getType().getTypeName();
        //这里正则表达式“.”表示任意字符 ，所以需要转义\\.
        String[] split = type.split("\\.");
        type = split[split.length-1];
        return type.equals(what);
    }

    /**
     * 首字母大写转换
     * @param name
     * @return
     */
    private String upperHeadChar(String name) {
        String head = name.substring(0, 1);
        String out = head.toUpperCase() + name.substring(1);
        return out;
    }
}
