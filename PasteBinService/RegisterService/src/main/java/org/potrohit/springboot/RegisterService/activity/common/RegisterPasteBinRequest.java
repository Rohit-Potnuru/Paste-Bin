package org.potrohit.springboot.RegisterService.activity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPasteBinRequest {
    public String user_id;
    public String file_name;
}
