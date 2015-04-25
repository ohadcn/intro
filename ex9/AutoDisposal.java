package intro.ex9;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A class that is used to automatically dispose of the frame if the main
 * thread of the program has died.
 * @author oop
 */
class AutoDisposal implements Runnable, UncaughtExceptionHandler {
        /** The main thread*/
        private Thread mainThread;

        /** The frame to recycle if the thread dies*/
        private JFrame frame;

        /** The exception handler for the thread.*/
        private UncaughtExceptionHandler uncaughtHandler;

        /** A flag that indicates if the thread is still running.*/
        private boolean running;

        /**
         * creates a new auto disposal runnable
         *
         * @param mainThread The thread this will wait for.
         * @param frame The frame that will be disposed of once the thread dies.
         */
        public AutoDisposal(Thread mainThread, JFrame frame) {
                this.running = false;
                this.frame = frame;
                this.mainThread = mainThread;
                this.uncaughtHandler = this.mainThread.getUncaughtExceptionHandler();
                this.mainThread.setUncaughtExceptionHandler(this);
        }

        /**
         * Waits for the main thread to die and then disposes of the frame. Wakes up
         * at least once a second to check.
         */
        public void run() {
                this.running = true;
                while (this.mainThread.isAlive() && this.running) {
                        try {
                                this.mainThread.join(1000);
                        } catch (InterruptedException e) {
                        }
                }
                this.running = false;
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            AutoDisposal.this.frame.dispose();
                        }
                });
        }

        /**
         * Handle an uncaught exception by the main thread by calling the default
         * handler and disposing of the frame.
         */
        public void uncaughtException(Thread thread, Throwable exception) {
                if (this.uncaughtHandler != null) {
                        this.uncaughtHandler.uncaughtException(thread, exception);
                }
                this.running = false; // makes sure the frame is disposed of soon.
        }
}
