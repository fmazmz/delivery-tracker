package org.example.deliverytracker.util;

import java.time.Instant;
import java.util.UUID;

public record ApiResponse<T>(
        T data,
        UUID requestId,
        Long timestamp
)
{
    public ApiResponse(T data) {
        this(data, UUID.randomUUID(), Instant.now().toEpochMilli());
    }
}