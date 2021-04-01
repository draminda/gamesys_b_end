package com.gamesys.news.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author  Raminda
 * @apiNote audit since using rss no modified date is created
 */
public class AuditField {
    @Getter @Setter
    private Instant createdAt = Instant.now();
}
