package com.chenbaolu.qflt.RxBus;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * 描述 :
 * 创建时间 : 2022/9/13 17:16
 * 作者 : 23128
 */
public class RxBus {
    private final Subject<Object> subject;
    private static volatile RxBus rxBus;

    public RxBus() {
        subject = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance(){
        if(rxBus==null){
            synchronized (RxBus.class){
                if (rxBus==null){
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }

    public void post(Object o){
        subject.onNext(o);
    }
    public <T> Observable<T> toObservable(Class<T> ev){
        return subject.ofType(ev).observeOn(AndroidSchedulers.mainThread());
    }
}
