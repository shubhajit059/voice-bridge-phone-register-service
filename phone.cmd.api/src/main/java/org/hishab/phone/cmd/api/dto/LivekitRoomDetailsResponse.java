package org.hishab.phone.cmd.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hishab.phone.core.dto.BaseResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"status", "message", "body"})
public class LivekitRoomDetailsResponse extends BaseResponse {
    private int status;
    private String message;
    private Room body;

    public LivekitRoomDetailsResponse(int status, String message, Room room) {
        super(message);
        this.status = status;
        this.message = message;
        this.body = room;
    }
}
