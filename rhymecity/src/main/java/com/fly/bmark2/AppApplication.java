package com.fly.bmark2;

import android.content.Context;

import com.fly.bmark2.api.ApiRequestHandler;
import com.fly.bmark2.api.ApiService;
import com.squareup.otto.Bus;
import com.trnql.zen.core.AppData;

import javax.inject.Inject;

import dagger.ObjectGraph;
import me.mattlogan.rhymecity.Modules;

public class AppApplication extends AppData {

    private ObjectGraph objectGraph;

    @Inject Bus bus;
    @Inject
    ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
        createApiRequestHandler();
    }

    private void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list("ASJ3wq8YnBmshFGszZZFHEntCFOUp1xhB2Sjsn4QZMpC3KV6kk"));
        objectGraph.inject(this);
    }

    private void createApiRequestHandler() {
        bus.register(new ApiRequestHandler(bus, apiService));
    }

    public ObjectGraph createScopedGraph(Object module) {
        return objectGraph.plus(module);
    }

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }
}
