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
public class RegisterPasteBinServiceImpl implements RegisterPasteBinService{

    private final DefaultPasteBinVao defaultPasteBinVao;
    private final UniqueIdentifierService uniqueIdentifierService;
    private final PasteBinDataStore pasteBinDataStore;

    @Autowired
    public RegisterPasteBinServiceImpl(DefaultPasteBinVao defaultPasteBinVao, UniqueIdentifierService uniqueIdentifierService, PasteBinDataStore pasteBinDataStore) {
        this.defaultPasteBinVao = defaultPasteBinVao;
        this.uniqueIdentifierService = uniqueIdentifierService;
        this.pasteBinDataStore = pasteBinDataStore;
    }

    @Override
    public PasteBinAccessVao registerPasteBin(PasteBinVao pasteBinVao) {

        try {

            //Setting empty field in pasteBinVao to default values
            log.info("RegisterPasteBinService/registerPasteBin: Filling out empty filled for pasteBinVao object");
            pasteBinVao = defaultPasteBinVao.fillDefaultPasteBinVao(pasteBinVao);

            //Generating UUID as pasteBinId
            log.info("RegisterPasteBinService/registerPasteBin: Calling for generating pasteBinId from uniqueIdentifierService");
            String pasteBinId = uniqueIdentifierService.getUniqueIdentifier(pasteBinVao);
            pasteBinVao.setPasteBinId(pasteBinId);
            log.info("RegisterPasteBinService/registerPasteBin: Generated pasteBinId from uniqueIdentifierService");

            //Setting itemStatus to Pending_UPLOAD
            pasteBinVao.setItemStatus("PENDING_REGISTER_UPLOAD");

            //Register the pasteBinVao to DataStore;
            log.info("RegisterPasteBinService/registerPasteBin: Calling registerPasteBin from pasteBinDataStore class for generating uploadURL");
            String accessURL = pasteBinDataStore.registerPasteBin(pasteBinVao);
            PasteBinAccessVao pasteBinAccessVao = PasteBinAccessVao.builder()
                                                                .pasteBinId(pasteBinId)
                                                                .accessURL(accessURL)
                                                                .build();
            log.info("RegisterPasteBinService/registerPasteBin: Generated uploadURL");

            return pasteBinAccessVao;

        } catch (Exception e) {
            // Log the error message
            log.error("RegisterPasteBinService/registerPasteBin: Error registering PasteBin for pasteBinId {}: {}", pasteBinVao.getPasteBinId(), e.getMessage());

            // You can choose to re-throw the exception or handle it as needed
            throw new RuntimeException("RegisterPasteBinService/registerPasteBin: An error occurred while registering PasteBin for pasteBinId " + pasteBinVao.getPasteBinId(), e);
        }

        //create pasteBinMetaDataVao as pmdVao and Set user_id, file_name to PasteBinMetaDataVao, status as PENDING
        //Generate unique pasteBin_id
        //Set pasteBin_id to pmdVao from the generated one
        //Generate S3 link and presigned URL link
        //Set pasteBin_S3_url as S3 link to pmdVao
        //Store pmdVao to dynamoDB
        //Generate PasteBinResponse and set pasteBin_id: [by extracting pasteBin_id from pmdVao], pasteBin_S3_url: presigned S3 URL

    }

    @Override
    public PasteBinAccessVao confirmRegisterPasteBin(PasteBinVao pasteBinVao) {

        //Setting itemStatus to UPLOADED
        pasteBinVao.setItemStatus("UPLOADED");

        //Register the pasteBinVao to DataStore;
        log.info("RegisterPasteBinService/confirmRegisterPasteBin: Calling confirmRegisterPasteBin from pasteBinDataStore class for confirming registration and generating downloadURL");
        String accessURL = pasteBinDataStore.confirmRegisterPasteBin(pasteBinVao);
        PasteBinAccessVao pasteBinAccessVao = PasteBinAccessVao.builder()
                .pasteBinId(pasteBinVao.getPasteBinId())
                .accessURL(accessURL)
                .build();
        log.info("RegisterPasteBinService/confirmRegisterPasteBin: Generated downloadURL");

        return pasteBinAccessVao;

        //Set status as UPLOADED
        //Generate S3 link and presigned URL link
        //Set pasteBin_S3_url as S3 link to pmdVao
        //Store pmdVao to dynamoDB
        //Generate PasteBinResponse and set pasteBin_id: [by extracting pasteBin_id from pmdVao], pasteBin_S3_url: presigned S3 URL
    }
}
