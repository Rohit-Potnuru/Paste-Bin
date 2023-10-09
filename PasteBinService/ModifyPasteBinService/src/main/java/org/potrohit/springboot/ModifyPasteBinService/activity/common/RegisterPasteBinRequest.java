package org.potrohit.springboot.ModifyPasteBinService.activity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPasteBinRequest {
    public String userId;
    public String contentType;
    public Long expiryTime;

    public PasteBinVao toPasteBinVao() {
        return PasteBinVao.builder()
                .userId(userId)
                .contentType(contentType)
                .expiryTime(expiryTime)
                .build();
    }
}
