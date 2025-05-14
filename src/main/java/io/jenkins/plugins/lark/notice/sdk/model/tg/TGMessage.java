package io.jenkins.plugins.lark.notice.sdk.model.tg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TGMessage implements Serializable {
    @JsonProperty("text")
    private String text;
    @JsonProperty("chat_id")
    private String chat_id;
    @JsonProperty("parse_mode")
    private String parse_mode;


}
