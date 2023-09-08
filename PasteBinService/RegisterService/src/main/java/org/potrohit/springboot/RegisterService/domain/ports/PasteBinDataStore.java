package org.potrohit.springboot.RegisterService.domain.ports;

import org.potrohit.springboot.RegisterService.domain.vao.PasteBinVao;

public interface PasteBinDataStore {
    String registerPasteBin(final PasteBinVao pasteBinVao);
}
