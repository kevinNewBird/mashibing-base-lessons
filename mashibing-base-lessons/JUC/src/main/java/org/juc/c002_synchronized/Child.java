package org.juc.c002_synchronized;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/***********************
 * Description: TODO <BR>
 * @author: zhao.song
 * @date: 2020/9/15 22:40
 * @version: 1.0
 ***********************/
public class Child extends Parent {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger("Child.class");

    /**
     * 正则表达式：验证用户名 1）由汉字、英文字母（不区分大小写）、数字、点或下划线组成 2）长度为2-20个字符
     * 3）中间不可以出现空格符、单引号、双引号、减号、逗号等非法字符 4）system、admin是系统的保留账号
     */
    public static final String REGEX_USERNAME = "^[\u4e00-\u9fa5A-Za-z0-9_\\.·]{2,20}$";// "^\\w{2,20}$";

    private static Object obj = new Object();

    public Object getObj() {
        return this.obj;
    }

    public Object getLogger() {
        return this.logger;
    }

    @Override
    synchronized void m2() {
        System.out.println("子类:" + this.getClass());
        super.m2();
    }

    public static void main(String[] args) {
        //synchronized是可重入锁:
        // 1.同一个线程中,不同的方法的锁对象是同一个,那么一个方法可以调用另一个方法
//        new Child().m2();

//        System.out.println(new Child().getObj() == new Child().getObj());
//        System.out.println(new Child().getLogger() == new Child().getLogger());

        String tmpStr = "ssss";
        final String newTemp = tmpStr.substring(tmpStr.lastIndexOf("-"));
        System.out.println(newTemp);

        System.out.println(isUsername("赵·松"));
    }


    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
}
