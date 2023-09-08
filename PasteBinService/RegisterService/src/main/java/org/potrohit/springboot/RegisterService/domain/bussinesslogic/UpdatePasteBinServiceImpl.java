package org.potrohit.springboot.RegisterService.domain.bussinesslogic;

import org.potrohit.springboot.RegisterService.activity.common.ConfirmPasteBinRequest;
import org.potrohit.springboot.RegisterService.activity.common.RegisterPasteBinRequest;
import org.potrohit.springboot.RegisterService.activity.common.ModifyPasteBinRequest;
import org.potrohit.springboot.RegisterService.activity.common.PasteBinResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasteBinServiceImpl implements UpdatePasteBinService{
    @Override
    public PasteBinResponse registerPasteBin(RegisterPasteBinRequest registerPasteBinRequest) {
        //create pasteBinMetaDataVao as pmdVao and Set user_id, file_name to PasteBinMetaDataVao, status as PENDING
        //Generate unique pasteBin_id
        //Set pasteBin_id to pmdVao from the generated one
        //Generate S3 link and presigned URL link
        //Set pasteBin_S3_url as S3 link to pmdVao
        //Store pmdVao to dynamoDB
        //Generate PasteBinResponse and set pasteBin_id: [by extracting pasteBin_id from pmdVao], pasteBin_S3_url: presigned S3 URL
        return null;
    }

    @Override
    public PasteBinResponse confirmPasteBin(ConfirmPasteBinRequest confirmPasteBinRequest) {
        //create pasteBinMetaDataVao as pmdVao and Set user_id, file_name to PasteBinMetaDataVao
        //Call DynamoDB with this record and set status variable to CONFIRM
        //Gather S3_URL from dynamoDB
        //Generate Get data S3_URL by calling S3
        //Generate PasteBinResponse and set pasteBin_id: [by extracting pasteBin_id from pmdVao], pasteBin_S3_url: Get data S3_URL
        return null;
    }

    @Override
    public PasteBinResponse modifyPasteBin(ModifyPasteBinRequest modifyPasteBinRequest) {
        return null;
    }

    @Override
    public PasteBinResponse deletePasteBin(ModifyPasteBinRequest deletePasteBinRequest) {
        return null;
    }
}
