package com.saurav.projectpagination.response;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    int recordCount;
    T response;
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;


    // explicit constructor (optional but helps compilers)
    public APIResponse(int recordCount, T response) {
        this.recordCount = recordCount;
        this.response = response;
        this.status = HttpStatus.OK;
        this.message = "Success";
        this.timestamp = LocalDateTime.now();
    }

    public APIResponse(int recordCount, T response,String message){
        this.recordCount = recordCount;
        this.response = response;
        this.status = HttpStatus.OK;
        this.message = message;
        this.timestamp = LocalDateTime.now();
}

}
