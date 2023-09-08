package org.potrohit.springboot.RegisterService.domain.vao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasteBinVao {
    private String pasteBinId;
    private String userId;
    private String itemStatus;
    private Long expiryTime;
    private String contentType;
}
