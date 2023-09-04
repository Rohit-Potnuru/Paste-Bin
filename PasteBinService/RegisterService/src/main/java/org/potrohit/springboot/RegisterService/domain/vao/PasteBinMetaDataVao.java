package org.potrohit.springboot.RegisterService.domain.vao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasteBinMetaDataVao {
    public String user_id;
    public String file_name;
    public String pasteBin_id;
    public String pasteBin_S3_url;
    public enum Status {PENDING, CONFIRM}
    public Status status;
}
