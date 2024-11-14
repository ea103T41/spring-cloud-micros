package com.euphy.learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Department {

    private Long id;
    private String name;
    // 來自那個資料庫，因為微服務架構可以一個服務對應一個資料庫，同一個信息被儲存到不同資料庫
    private String db_source;
}
