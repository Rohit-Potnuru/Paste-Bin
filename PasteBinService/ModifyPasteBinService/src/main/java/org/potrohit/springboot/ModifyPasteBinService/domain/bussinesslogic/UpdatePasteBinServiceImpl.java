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
public class UpdatePasteBinServiceImpl implements UpdatePasteBinService{
    private final PasteBinDataStore pasteBinDataStore;

    private final DefaultPasteBinVao defaultPasteBinVao;

    @Autowired
    public UpdatePasteBinServiceImpl(PasteBinDataStore pasteBinDataStore, DefaultPasteBinVao defaultPasteBinVao) {
        this.pasteBinDataStore = pasteBinDataStore;
        this.defaultPasteBinVao = defaultPasteBinVao;
    }

    @Override
    public PasteBinAccessVao updatePasteBin(PasteBinVao pasteBinVao) {

        try {

            //Setting empty field in pasteBinVao to default values
            log.info("UpdatePasteBinService/updatePasteBin: Filling out empty filled for pasteBinVao object");
            pasteBinVao = defaultPasteBinVao.fillDefaultPasteBinVao(pasteBinVao);

            //Setting itemStatus to Pending_UPLOAD
            pasteBinVao.setItemStatus("PENDING_UPDATE_UPLOAD");

            //Register the pasteBinVao to DataStore;
            log.info("UpdatePasteBinService/updatePasteBin: Calling updatePasteBin from pasteBinDataStore class for generating uploadURL");
            String accessURL = pasteBinDataStore.updatePasteBin(pasteBinVao);
            PasteBinAccessVao pasteBinAccessVao = PasteBinAccessVao.builder()
                    .pasteBinId(pasteBinVao.getPasteBinId())
                    .accessURL(accessURL)
                    .build();
            log.info("UpdatePasteBinService/updatePasteBin: Generated uploadURL");

            return pasteBinAccessVao;

        } catch (Exception e) {
            // Log the error message
            log.error("UpdatePasteBinService/updatePasteBin: Error registering PasteBin for pasteBinId {}: {}", pasteBinVao.getPasteBinId(), e.getMessage());

            // You can choose to re-throw the exception or handle it as needed
            throw new RuntimeException("UpdatePasteBinService/updatePasteBin: An error occurred while registering PasteBin for pasteBinId " + pasteBinVao.getPasteBinId(), e);
        }
    }

    @Override
    public PasteBinAccessVao confirmUpdatePasteBin(PasteBinVao pasteBinVao) {

        //Setting itemStatus to Pending_UPLOAD
        pasteBinVao.setItemStatus("UPLOADED");

        //Register the pasteBinVao to DataStore;
        log.info("UpdatePasteBinService/confirmRegisterPasteBin: Calling confirmUpdatePasteBin from pasteBinDataStore class for confirming registration and generating downloadURL");
        String accessURL = pasteBinDataStore.confirmUpdatePasteBin(pasteBinVao);
        PasteBinAccessVao pasteBinAccessVao = PasteBinAccessVao.builder()
                .pasteBinId(pasteBinVao.getPasteBinId())
                .accessURL(accessURL)
                .build();
        log.info("UpdatePasteBinService/confirmUpdatePasteBin: Generated downloadURL");

        return pasteBinAccessVao;

        //Set status as UPLOADED
        //Generate S3 link and presigned URL link
        //Set pasteBin_S3_url as S3 link to pmdVao
        //Store pmdVao to dynamoDB
        //Generate PasteBinResponse and set pasteBin_id: [by extracting pasteBin_id from pmdVao], pasteBin_S3_url: presigned S3 URL
    }
}
