package com.renyija.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author : zhouwenya
 * @version : 1.0
 * @date : 2019-07-23
 * @email : zhou_wenya@163.com
 * <p>
 * 线程池管理的工具类，封装类
 * 线程池的管理 ，通过java 中的api实现管理
 * 采用conncurrent框架： 非常成熟的并发框架 ，特别在匿名线程管理非常优秀的
 */
public class ThreadManager {

    /**
     * 通过ThreadPoolExecutor的代理类来对线程池的管理
     */
    private static ThreadPollProxy mThreadPollProxy;

    public static ThreadPollProxy getThreadPollProxy() {
        synchronized (ThreadPollProxy.class) {
            if (mThreadPollProxy == null) {
                mThreadPollProxy = new ThreadPollProxy(3, 6, 1000);
            }
        }
        return mThreadPollProxy;
    }

    public static ThreadPollProxy getThreadPollProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        synchronized (ThreadPollProxy.class) {
            if (mThreadPollProxy == null) {
                mThreadPollProxy = new ThreadPollProxy(corePoolSize, maximumPoolSize, keepAliveTime);
            }
        }
        return mThreadPollProxy;
    }

    /**
     * 通过ThreadPoolExecutor的代理类来对线程池的管理
     */
    public static class ThreadPollProxy {
        /**
         * 线程池执行者 ，java内部通过该api实现对线程池管理
         */
        private ThreadFactory namedThreadFactory;
        private ExecutorService singleThreadPool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        ThreadPollProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 对外提供一个执行任务的方法
         */
        public void execute(Runnable r) {

            if (singleThreadPool == null || singleThreadPool.isShutdown()) {
                namedThreadFactory = new ThreadFactoryBuilder()
                        .setNameFormat("demo-pool-%d").build();
                singleThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                        keepAliveTime, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
            }
            singleThreadPool.execute(r);

        }


    }

}
