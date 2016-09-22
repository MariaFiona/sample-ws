package com.pkmn.service.impl;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;

/**
 * Created by james
 */
@Default
public class CdiWorker {

    public <T> void doWork(Class<T> clazz, Consumer<T> consumer) {
        Instance<T> instance = CDI.current().select(clazz);
        T bean = instance.get();
        try {
            consumer.accept(bean);
        } finally {
            try {
                instance.destroy(bean);
            } catch (UnsupportedOperationException ignoreThisException) {
                // ignore this exception.
            }
        }
    }

    public <T, R> R doReturningWork(Class<T> clazz, Function<T, R> function) {
        Instance<T> instance = CDI.current().select(clazz);
        T bean = instance.get();
        try {
            return function.apply(bean);
        } finally {
            try {
                instance.destroy(bean);
            } catch (UnsupportedOperationException ignoreThisException) {
                // ignore this exception.
            }

        }
    }

}
