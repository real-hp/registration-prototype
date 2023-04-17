package test.elfin.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;


@Data
@Builder
@Document(indexName = "dictionary")
public class Email {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;
}
