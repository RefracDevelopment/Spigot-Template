package me.refracdevelopment.example.utilities;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import lombok.experimental.UtilityClass;
import me.refracdevelopment.example.ExamplePlugin;

import java.util.function.Consumer;

@UtilityClass
public class Tasks {

    public void run(Consumer<WrappedTask> callable) {
        ExamplePlugin.getInstance().getFoliaLib().getImpl().runNextTick(callable);
    }

    public void runAsync(Consumer<WrappedTask> callable) {
        ExamplePlugin.getInstance().getFoliaLib().getImpl().runAsync(callable);
    }

    public void runLater(Consumer<WrappedTask> callable, long delay) {
        ExamplePlugin.getInstance().getFoliaLib().getImpl().runLater(callable, delay);
    }

    public void runAsyncLater(Consumer<WrappedTask> callable, long delay) {
        ExamplePlugin.getInstance().getFoliaLib().getImpl().runLaterAsync(callable, delay);
    }

    public void runTimer(Consumer<WrappedTask> callable, long delay, long interval) {
        ExamplePlugin.getInstance().getFoliaLib().getImpl().runTimer(callable, delay, interval);
    }

    public void runAsyncTimer(Consumer<WrappedTask> callable, long delay, long interval) {
        ExamplePlugin.getInstance().getFoliaLib().getImpl().runTimerAsync(callable, delay, interval);
    }
}