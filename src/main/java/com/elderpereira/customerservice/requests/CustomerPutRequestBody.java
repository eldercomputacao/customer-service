package com.elderpereira.customerservice.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Schema(description = "The scheme (CustomerPutRequestBody) used in PUT REQUESTS")
public class CustomerPutRequestBody extends CustomerRequestBody {

    @Schema(example = "1")
    @NotNull(message = "The id cannot be empty")
    @Positive(message = "The id must be greater than zero")
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
