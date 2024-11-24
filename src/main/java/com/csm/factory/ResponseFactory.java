package com.csm.factory;

import com.csm.model.response.GeneralResponse;
import com.csm.model.ResponseStatus;
import com.csm.model.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

    public static ResponseEntity<Object> success() {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatusEnum.SUCCESS.code());
        responseStatus.setMessage(ResponseStatusEnum.SUCCESS.message());
        responseObject.setStatus(responseStatus);
        return ResponseEntity.ok(responseObject);
    }

    public static ResponseEntity<Object> success(Object data, Class clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatusEnum.SUCCESS.code());
        responseStatus.setMessage(ResponseStatusEnum.SUCCESS.message());
        responseObject.setStatus(responseStatus);
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, String errorCode, String errorMessage) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(errorCode);
        responseStatus.setMessage(errorMessage);
        responseObject.setStatus(responseStatus);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }

    public static ResponseEntity<Object> error(HttpStatus httpStatus, ResponseStatusEnum status, Object ...messageParams) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(status.code());
        responseStatus.setMessage(String.format(status.message(), messageParams));
        responseObject.setStatus(responseStatus);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(responseObject, httpStatus);
        return responseEntity;
    }

    public static GeneralResponse<Object> errorObject(ResponseStatusEnum status, Object ...messageParams) {
        GeneralResponse<Object> responseObject = new GeneralResponse<>();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(status.code());
        responseStatus.setMessage(String.format(status.message(), messageParams));
        responseObject.setStatus(responseStatus);
        return responseObject;
    }
}
