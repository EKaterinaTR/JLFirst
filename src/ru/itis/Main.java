package ru.itis;

import ru.itis.pool.ThreadPool;

import static java.lang.Thread.sleep;

/**
 * 07.09.2020
 * 01. Threads
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        /*
        Сделать проверки:

        1) Одна задача - один поток
        2) Две задачи - один поток (поочередно выполнить каждую)
        3) Три задачи - три потока (каждый поток выполняет свою задачу)
        4) Четыре задачи - три потока (три потока выполняют три задачи, четвертая задача выполняется первым свободным)
         */


        Runnable runnable1 = () -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("First" + i);

            }

        };
        Runnable runnable2 = () -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("Second" + i);

            }

        };
        Runnable runnable3 = () -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("Third" + i);

            }

        };
        Runnable runnable4 = () -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("Fourth" + i);

            }

        };

        //1

      /*  ThreadPool threadPool = new ThreadPool(1);
        threadPool.submit(runnable1);*/

        //2
/*
        ThreadPool threadPool = new ThreadPool(1);
        threadPool.submit(runnable1);
        threadPool.submit(runnable2);

*/
        //3
       /* ThreadPool threadPool = new ThreadPool(3);
        threadPool.submit(runnable1);
        threadPool.submit(runnable2);
        threadPool.submit(runnable3);*/
        //4
        ThreadPool threadPool = new ThreadPool(3);
        threadPool.submit(runnable1);
        threadPool.submit(runnable2);
        threadPool.submit(runnable3);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }
        threadPool.submit(runnable4);



    }
}

