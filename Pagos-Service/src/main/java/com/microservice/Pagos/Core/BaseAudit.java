package com.microservice.Pagos.Core;


import java.time.LocalDateTime;

public class BaseAudit {

    public LocalDateTime createdAt = LocalDateTime.now();

    public LocalDateTime deletedAd = null;

    public LocalDateTime updatedAt = null;

}
