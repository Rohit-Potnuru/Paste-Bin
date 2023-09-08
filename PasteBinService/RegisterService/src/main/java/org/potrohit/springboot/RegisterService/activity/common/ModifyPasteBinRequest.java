package org.potrohit.springboot.RegisterService.activity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasteBinRequest {
    public String user_id;
    public String file_name;
    public String pasteBin_id;
}
