public class ThreadProductTest {

    /**
     * 面试题3:自己编写代码,实现生产者-消费者模型功能.内容自由发挥,只需要表达思想.
     * 代码中,自定义一个学生类,有name和age属性,属于共享对象,
     * 生产者负责为studnet对象赋值,消费者负责打印出student对象的name和age的值,
     * 当生产者赋值完以后通知消费者来打印,消费者打印完以后,通知生产者重新设置.
     */
    public static void main(String[] args) {

        final ThreadStudent s = new ThreadStudent();

        // 模拟生产者线程类
        Thread inputThread = new Thread(new Runnable(){
            public void run(){
                int num = 10;
                while(num>0){
                    if(num % 2 == 0){
                        s.set("刘德华", 56);
                    }else{
                        s.set("仓木麻衣", 36);
                    }
                    num--;
                }
            }
        },"生产者");

        // 模拟消费者线程类
        Thread outputThread = new Thread(new Runnable(){
            public void run(){
                int num = 10;
                while(num>0){
                    s.get();
                    num--;
                }
            }
        },"消费者");

        inputThread.start();
        outputThread.start();
    }


}

//学生实体类作为共享资源
 class ThreadStudent {

    public String name;  // 姓名
    public int age;  // 年龄
    public boolean flag = false;  // 标记变量,判断当前学生对象是否已创建赋值好

    //生产者的功能  ,为studnet对象赋值
    public synchronized void set(String name,int age){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name;
        this.age = age;
        System.out.println(Thread.currentThread().getName()+"  student:name="+name+",age="+age+" -- flag="+flag);
        this.flag = true;
        this.notify(); // 唤醒 消费者线程

    }

    // 消费者的功能,打印sutdent对象的内容
    public synchronized void get(){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"  student:name="+name+",age="+age+" -- flag="+flag);

        this.flag = false;
        this.notify(); // 唤醒 生产者线程

    }

}