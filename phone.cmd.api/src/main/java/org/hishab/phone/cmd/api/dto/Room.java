package org.hishab.phone.cmd.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room implements Serializable {
    @JsonProperty("sid")
    private String sid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("emptyTimeout")
    private int emptyTimeout;
    @JsonProperty("departureTimeout")
    private int departureTimeout;
    @JsonProperty("maxParticipants")
    private int maxParticipants;
    @JsonProperty("creationTime")
    private long creationTime;
    @JsonProperty("turnPassword")
    private String turnPassword;
//    @JsonProperty("enabledCodecs")
//    private List<LivekitModels.Codec> enabledCodecs;
    @JsonProperty("metadata")
    private String metadata;
    @JsonProperty("numParticipants")
    private int numParticipants;
    @JsonProperty("numPublishers")
    private int numPublishers;
    @JsonProperty("activeRecording")
    private boolean activeRecording;
//    @JsonProperty("version")
//    private LivekitModels.TimedVersion version;
}
