package org.potrohit.springboot.GetPasteBinService.domain.vao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasteBinVao {
    private String pasteBinId;
    private String userId;
    private String itemStatus;
    private Long expiryTime;
    private String contentType;
}
