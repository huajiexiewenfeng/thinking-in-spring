package com.huajie.thinking.in.spring.event;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observable} 示例
 *
 * @Author xwf
 * @Date 2020\6\11 0011 22:00
 */
public class ObservableDemo {
    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 添加观察者（监听者）
        observable.addObserver(new EventObserver());
        // 手动设置发布消息（事件）
        observable.notifyObservers("Hello,World");
    }

    static class EventObservable extends Observable {
        public void setChanged() {
            super.setChanged();// 必须通过手动设置的方式修改 changed 状态位
        }

        public void notifyObservers(Object obj) {
            setChanged();
            super.notifyObservers(new EventObject(obj));
            clearChanged();
        }
    }

    static class EventObserver implements Observer {
        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件：" + eventObject);
        }
    }
}
