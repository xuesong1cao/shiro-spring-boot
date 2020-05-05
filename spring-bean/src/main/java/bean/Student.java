package bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author qsk
 */
public class Student implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {

    private String clas;
    private String className;
    private int teacher;

    private BeanFactory beanFactory;
    private String beanName;

    public Student() {
        System.out.println("【构造器】调用Person的构造器实例化");
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        System.out.println("【注入属性】注入属性clas");
        this.clas = clas;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        System.out.println("【注入属性】注入属性className");
        this.className = className;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        System.out.println("【注入属性】注入属性teacher");
        this.teacher = teacher;
        System.out.println("【注入属性】注入属性teacher结束");
    }

    @Override
    public String toString() {
        return "Student [clas=" + clas + ", className=" + className + ", teacher="
                + teacher + "]";
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out
                .println("【Student-------BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("【Student-------BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out
                .println("【Student-------InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("【Student-------DiposibleBean接口】调用DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法

    public void myInit() {
         clas = "一班";
        className="一班";
        teacher = 3333;
        System.out.println("【Student-------init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("【Student-------destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }
}
