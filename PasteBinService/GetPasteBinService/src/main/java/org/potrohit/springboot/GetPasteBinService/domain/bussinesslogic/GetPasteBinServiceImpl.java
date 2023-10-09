package org.potrohit.springboot.GetPasteBinService.domain.bussinesslogic;

import lombok.extern.log4j.Log4j2;
import org.potrohit.springboot.GetPasteBinService.domain.bussinesslogic.common.DefaultPasteBinVao;
import org.potrohit.springboot.GetPasteBinService.domain.exceptions.common.PasteBinURLNotfoundExpection;
import org.potrohit.springboot.GetPasteBinService.domain.ports.PasteBinDataStore;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinAccessVao;
import org.potrohit.springboot.GetPasteBinService.domain.vao.PasteBinVao;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GetPasteBinServiceImpl implements GetPasteBinService {

    private final DefaultPasteBinVao defaultPasteBinVao;
    private final PasteBinDataStore pasteBinDataStore;

    public GetPasteBinServiceImpl(DefaultPasteBinVao defaultPasteBinVao, PasteBinDataStore pasteBinDataStore) {
        this.defaultPasteBinVao = defaultPasteBinVao;
        this.pasteBinDataStore = pasteBinDataStore;
    }

    @Override
    public PasteBinAccessVao getPasteBin(PasteBinVao pasteBinVao) {
        //Filling empty pasteBin data
        pasteBinVao = defaultPasteBinVao.fillDefaultPasteBinVao(pasteBinVao);

        //Calling PasteBinDataStore
        log.info("GetPasteBinServiceImpl/getPasteBin: Calling PasteBinDataStore for retrieving data for given pasteBinId");
        PasteBinAccessVao pasteBinAccessVao = pasteBinDataStore.getPasteBin(pasteBinVao);
        log.info("GetPasteBinServiceImpl/getPasteBin: Retrieved data from PasteBinDataStore for given pasteBinId");

//        if(pasteBinVao.getPasteBinId().equals("1")) {
//            throw new PasteBinURLNotfoundExpection("GetPasteBinServiceImpl/getPasteBin");
//        }
        return pasteBinAccessVao;
    }

    @Override
    public List<PasteBinAccessVao> getUserPasteBin(PasteBinVao pasteBinVao) {
        //Filling empty pasteBin data
        pasteBinVao = defaultPasteBinVao.fillDefaultPasteBinVao(pasteBinVao);

        //Calling PasteBinDataStore
        log.info("GetPasteBinServiceImpl/getUserPasteBin: Calling PasteBinDataStore for retrieving data for given pasteBinId");
        List<PasteBinAccessVao> pasteBinAccessVaos = pasteBinDataStore.getUserPasteBin(pasteBinVao);
        log.info("GetPasteBinServiceImpl/getUserPasteBin: Retrieved data from PasteBinDataStore for given pasteBinId");

        return pasteBinAccessVaos;
    }
}
