package org.potrohit.springboot.ModifyPasteBinService.domain.vao;

import lombok.*;

@Data
@Builder
public class PasteBinVao {
    private String pasteBinId;
    private String userId;
    private String itemStatus;
    private Long expiryTime;
    private String contentType;
}
