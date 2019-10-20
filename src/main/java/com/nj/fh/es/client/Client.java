package com.nj.fh.es.client;

import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.action.Action;
import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

/**
 * ES java连接, 该连接为Jest6.3.1中的连接源码
 * @author 赵元路 18358572500 2019/9/18 18:30
 * @version 1.0
 */
public interface Client extends Closeable {
    <T extends JestResult> T execute(Action<T> var1) throws IOException;

    <T extends JestResult> void executeAsync(Action<T> var1, JestResultHandler<? super T> var2);

    /** @deprecated */
    @Deprecated
    void shutdownClient();

    void setServers(Set<String> var1);
}
