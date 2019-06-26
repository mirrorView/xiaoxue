/**
 * Copyright (C), 2019,
 * FileName: SyncDubbo2
 * Author:   gc
 * Date:     2019/6/15 17:19
 * Description: synchronized 的重入
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈synchronized 的重入〉  父子安全的线程， 也是安全的类。
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class SyncDubbo2 {
    static class Main {
        public int i = 10;
        public synchronized void operationSup(){
            try {
                i--;
                System.out.println("Main print i = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main {
        public synchronized void operationSub(){
            try {
                while(i > 0) {
                    i--;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    this.operationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.operationSub();
            }
        });

        t1.start();
    }

}