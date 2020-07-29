package edu.sharif.surveyBackend.api.rest.upload.error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    @NonNull private int code;
    @NonNull private String message;
    @NonNull private String description;
}
