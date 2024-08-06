package com.example.side.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 상수 집합 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MoaolioConstants {

    public static final Long ACCESS_TOKEN_EXPIRED_MS = 600000L;
    public static final Long REFRESH_TOKEN_EXPIRED_MS = 86400000L;


}
