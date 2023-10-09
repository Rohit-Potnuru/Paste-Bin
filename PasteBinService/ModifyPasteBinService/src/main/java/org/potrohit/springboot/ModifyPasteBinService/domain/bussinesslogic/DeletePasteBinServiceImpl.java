package org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.ModifyPasteBinService.domain.bussinesslogic.common.DefaultPasteBinVao;
import org.potrohit.springboot.ModifyPasteBinService.domain.ports.PasteBinDataStore;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.ModifyPasteBinService.domain.vao.PasteBinVao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DeletePasteBinServiceImpl implements DeletePasteBinService{
    private final PasteBinDataStore pasteBinDataStore;

    private final DefaultPasteBinVao defaultPasteBinVao;

    @Autowired
    public DeletePasteBinServiceImpl(PasteBinDataStore pasteBinDataStore, DefaultPasteBinVao defaultPasteBinVao) {
        this.pasteBinDataStore = pasteBinDataStore;
        this.defaultPasteBinVao = defaultPasteBinVao;
    }

    @Override
    public PasteBinAccessVao deletePasteBin(PasteBinVao pasteBinVao) {

        //Register the pasteBinVao to DataStore;
        log.info("DeletePasteBinService/deletePasteBin: Calling deletePasteBin from pasteBinDataStore class for deleting pasteBin");
        pasteBinDataStore.deletePasteBin(pasteBinVao);
        PasteBinAccessVao pasteBinAccessVao = PasteBinAccessVao.builder()
                .pasteBinId(pasteBinVao.getPasteBinId())
                .accessURL("PasteBin Delete")
                .build();
        log.info("DeletePasteBinService/deletePasteBin: Deleted the PasteBinId: {}", pasteBinVao.getPasteBinId());

        return pasteBinAccessVao;
    }
}
