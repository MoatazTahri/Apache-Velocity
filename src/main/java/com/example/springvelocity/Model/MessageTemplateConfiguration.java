package com.example.springvelocity.Model;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageTemplateConfiguration {
    private String header;
    private String body;
    private String footer;

    public MessageTemplateConfiguration(@Nullable String header,@Nullable String body,@Nullable String footer) {
        this.header = header;
        this.body =  body;
        this.footer = footer;
    }
}
