package edu.gdkm.weixin.processors;

import edu.gdkm.weixin.domain.event.EventInMessage;

public interface EventMessageProcessor {

	void onMessage(EventInMessage msg);
}
