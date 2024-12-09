package org.hishab.phone.core.dto;

public interface Response<T> {
    T getData();

    String getMessage();

    String getStatus();
}
