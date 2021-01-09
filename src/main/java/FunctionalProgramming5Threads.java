public class FunctionalProgramming5Threads {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(
                            Thread.currentThread().getId()
                                    + ":"
                                    + i
                    );
                }
            }
        };

        // Functional programming way
        Runnable runnable2 = () -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(
                        Thread.currentThread().getId()
                                + ":"
                                + i
                );
            }
            /*
            IntStream.range(0,10000).forEach(
                i -> System.out.println(...)
            );

            * */

        };

        Thread thread = new Thread(runnable2);
        thread.start();

        Thread thread1 = new Thread(runnable2);
        thread1.start();

        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }
}
