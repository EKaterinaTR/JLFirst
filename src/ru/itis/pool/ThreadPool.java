package ru.itis.pool;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 07.09.2020
 * 01. Threads
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

// wait, notify, synchronized
public class ThreadPool {
    // очередь задач
    private Deque<Runnable> tasks;

    // пул потоков
    private PoolWorker threads[];

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.threads = new PoolWorker[threadsCount];

        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
    }

    public synchronized void submit(Runnable task) {
        synchronized (tasks){
            tasks.add(task);
            System.out.println(task.hashCode());
            tasks.notify();
        }

        // TODO: реализовать
    }

    // класс - рабочий поток
    private class PoolWorker extends Thread {
        Runnable r;
        boolean flag = false;
        @Override
        public void run() {
            // TODO: реализовать
            while(true) {
                synchronized (tasks) {
                    if(!tasks.isEmpty()) {
                        r = tasks.poll();
                        flag = true;
                    }
                }
                if (flag){
                    r.run();
                }
                else {
                    try {
                        System.out.println("I am waiting");
                        synchronized (tasks) {
                            tasks.wait();
                        }
                        System.out.println("I am waking up");
                    } catch (InterruptedException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                flag = false;
            }
        }
    }
}

