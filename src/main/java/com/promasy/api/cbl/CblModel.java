package com.promasy.api.cbl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Document("cbl")
public class CblModel {
    @Id
    String id;
    String name;
    String desc;
    List<String> colors;
    List<HashMap<String,Integer>> sizes;
}
