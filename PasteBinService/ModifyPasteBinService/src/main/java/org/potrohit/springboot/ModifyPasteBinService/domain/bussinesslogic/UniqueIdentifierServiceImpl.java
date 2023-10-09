package org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic;

import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UniqueIdentifierServiceImpl implements UniqueIdentifierService{

    @Override
    public String getUniqueIdentifier(PasteBinVao pasteBinVao) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
