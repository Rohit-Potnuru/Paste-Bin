package org.potrohit.springboot.RegisterService.infrastructure.PasteBinMetaDataStore;

import lombok.RequiredArgsConstructor;
import org.potrohit.springboot.RegisterService.domain.ports.PasteBinMetaDataStore;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class PasteBinMetaDataStoreImpl implements PasteBinMetaDataStore {

    @Autowired
    private final DynamoDbImpl dynamoDb;



}
