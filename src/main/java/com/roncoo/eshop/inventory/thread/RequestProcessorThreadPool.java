package com.roncoo.eshop.inventory.thread;

import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Component: 请求处理线程池：单例
 * Description:
 * Date: 17/7/2
 *
 * @author yue.zhang
 */
public class RequestProcessorThreadPool {

    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private RequestProcessorThreadPool(){
        RequestQueue requestQueue = RequestQueue.getInstance();
        for(int i = 0 ; i < 10 ; i++){
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    /**
     * 静态内部类的方式去初始化单例
     */
    private static class Singlethon{

        private static RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance(){
            return instance;
        }
    }

    /**
     * jvm的机制去保证多线程并发安全
     * 内部类的初始化，一定只会发生一次，不管多少个线程并发去初始化
     * @return
     */
    public static RequestProcessorThreadPool getInstance(){
        return Singlethon.getInstance();
    }


    public static void init(){
        getInstance();
    }
}
