package com.github.alphafoxz.oneboot.core.standard.framework;

public interface ReliableService {
    public void startTransaction();

    public void rollbackTransaction();

    public void commitTransaction();
}
