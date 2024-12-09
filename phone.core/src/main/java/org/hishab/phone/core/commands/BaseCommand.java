package org.hishab.phone.core.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hishab.phone.core.messages.Message;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
