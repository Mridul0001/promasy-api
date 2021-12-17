package com.promasy.api.helpers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("sequence")
public class SequenceModel {
    @Id
    String sequenceName;
    int value;
}
