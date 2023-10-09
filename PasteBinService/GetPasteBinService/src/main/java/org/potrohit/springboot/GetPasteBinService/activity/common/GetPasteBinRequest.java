package org.potrohit.springboot.GetPasteBinService.activity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPasteBinRequest {
    public String userId;
    public String pasteBinId;

    public PasteBinVao toPasteBinVao() {
        return PasteBinVao.builder()
                .userId(userId)
                .pasteBinId(pasteBinId)
                .build();
    }
}
