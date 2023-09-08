package org.potrohit.springboot.RegisterService.infrastructure.PasteBinDataStore;

import lombok.RequiredArgsConstructor;
import org.potrohit.springboot.RegisterService.domain.ports.PasteBinDataStore;
import org.potrohit.springboot.RegisterService.domain.vao.PasteBinVao;
import org.potrohit.springboot.RegisterService.infrastructure.dynamodb.DynamoDbImpl;
import org.potrohit.springboot.RegisterService.infrastructure.s3.S3AdapterImpl;
import org.potrohit.springboot.RegisterService.infrastructure.s3.S3Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasteBinDataStoreAdapterImpl implements PasteBinDataStore {
    @Autowired
    private final DynamoDbImpl dynamoDb = null;
    @Autowired
    private final S3AdapterImpl s3AdapterImpl = null;

    @Override
    public String registerPasteBin(final PasteBinVao pasteBinVao) {
        final S3Details details = s3AdapterImpl.getS3Details(pasteBinVao);



        // call s3Impl and get S3 details
        // create PasteBinMetaData object.
        // dynamoDb.putItem(pasteBinMetaData).
        // S3.get url for the given bucket and key.
        return null;
    }
}
