/**
 * Copyright (C), 2019,
 * FileName: SyncException
 * Author:   gc
 * Date:     2019/6/15 17:22
 * Description: synchroized 异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈synchroized 异常〉
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class SyncException {
    private int i = 0;
    public synchronized void operation(){
        while(true){
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " , i = " + i);
                if(i == 20){
                    //Integer.parseInt("a");
                    throw new RuntimeException();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final SyncException se = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        },"t1");
        t1.start();
    }


}