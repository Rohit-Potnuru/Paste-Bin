package org.potrohit.springboot.ModifyPasteBinService.domain.ports;

import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Component;

@Component
public interface PasteBinDataStore {
    String registerPasteBin(final PasteBinVao pasteBinVao);
    String confirmRegisterPasteBin(final PasteBinVao pasteBinVao);
    String updatePasteBin(final PasteBinVao pasteBinVao);
    String confirmUpdatePasteBin(PasteBinVao pasteBinVao);
    void deletePasteBin(final PasteBinVao pasteBinVao);

}
