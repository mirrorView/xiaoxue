/**
 * Copyright (C), 2019,
 * FileName: DirtyRead
 * Author:   gc
 * Date:     2019/6/15 17:15
 * Description: 业务整体需要使用完整的synchronized,保持业务的原子性.cuid
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.g.gc.xiaoxue.day1;

/**
 * 〈一句话功能简述〉<br> 
 * 〈业务整体需要使用完整的synchronized,保持业务的原子性.cuid〉
 *
 * @author gc
 * @create 2019/6/15
 * @since 1.0.0
 */
public class DirtyRead {
    private String username = "bjsxt";
    private String password = "123";

    public synchronized void setValue(String username, String password){
        this.username = username;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;

        System.out.println("setValue最终结果：username = " + username + " , password = " + password);
    }

    //synchronized
    public   void getValue(){
        System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
    }


    public static void main(String[] args) throws Exception{

        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);

        dr.getValue();
    }


}