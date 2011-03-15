package org.berlin.pino.dev.analy.metric.antlr;

public interface ResourceFilter {

    boolean match(String packageName, String resourceName);

  }
