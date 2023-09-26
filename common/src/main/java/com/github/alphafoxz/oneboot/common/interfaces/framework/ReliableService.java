package com.github.alphafoxz.oneboot.common.interfaces.framework;

public interface ReliableService {
    public void startTransaction();

    public void rollbackTransaction();

    public void commitTransaction();
}
