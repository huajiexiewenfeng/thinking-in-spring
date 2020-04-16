package com.huajie.thinking.in.spring.bean.scope;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static com.huajie.thinking.in.spring.bean.scope.ThreadLocalScope.SCOPE_NAME;

public class ThreadLocalScopeDemo {

    @Bean
    @Scope(SCOPE_NAME)
    public User user() {
        return createUser(System.nanoTime());
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();

        scopeBeanByLookup(applicationContext);

//        applicationContext.close();
    }

    private static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            new Thread(new MyThread(applicationContext)).start();
        }
    }

    static class MyThread implements Runnable {

        private ApplicationContext context;

        public MyThread(ApplicationContext context) {
            this.context = context;
        }

        @Override
        public void run() {
            for (int i = 0; i <3 ; i++) {
                User user = context.getBean(User.class);
                System.out.println(Thread.currentThread().getName()+"===scope-user===" + user);
            }
        }
    }
}
