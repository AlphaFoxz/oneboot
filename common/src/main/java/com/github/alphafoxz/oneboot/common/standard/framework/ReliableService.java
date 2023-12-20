package com.github.alphafoxz.oneboot.common.standard.framework;

public interface ReliableService {
    public void startTransaction();

    public void rollbackTransaction();

    public void commitTransaction();
}
