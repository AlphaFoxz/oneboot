package com.github.alphafoxz.oneboot.common.interfaces.common;

public interface ReliableService {
    public void startTransaction();

    public void rollbackTransaction();

    public void commitTransaction();
}
